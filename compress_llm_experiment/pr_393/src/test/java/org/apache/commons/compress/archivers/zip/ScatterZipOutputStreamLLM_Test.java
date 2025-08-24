package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.tryHardToDelete;
import static org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest.createZipArchiveEntryRequest;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.zip.ZipEntry;

import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ScatterZipOutputStreamLLM_Test {
    private File scatterFile = null;
    private File target = null;

    @AfterEach
    public void cleanup() {
        tryHardToDelete(scatterFile);
        tryHardToDelete(target);
    }

    private InputStreamSupplier createPayloadSupplier(final ByteArrayInputStream payload) {
        return () -> payload;
    }

    /**
     * Test to ensure that the transferToArchiveEntry method correctly updates
     * the original ZipArchiveEntry with sizes and CRC.
     */
    @Test
    public void testTransferToArchiveEntry() throws Exception {
        scatterFile = File.createTempFile("scattertest", ".notzip");
        final byte[] PAYLOAD = "TESTDATA".getBytes();
        
        try (ScatterZipOutputStream scatterZipOutputStream = ScatterZipOutputStream.fileBased(scatterFile)) {
            final ZipArchiveEntry zae = new ZipArchiveEntry("test.txt");
            zae.setMethod(ZipEntry.DEFLATED);
            final ByteArrayInputStream payload = new ByteArrayInputStream(PAYLOAD);
            scatterZipOutputStream.addArchiveEntry(createZipArchiveEntryRequest(zae, createPayloadSupplier(payload)));
            
            ScatterZipOutputStream.CompressedEntry compressedEntry = scatterZipOutputStream.items.iterator().next();
            ZipArchiveEntry updatedEntry = compressedEntry.transferToArchiveEntry();
            
            assertEquals(zae.getName(), updatedEntry.getName());
            assertEquals(zae.getMethod(), updatedEntry.getMethod());
            assertEquals(scatterZipOutputStream.streamCompressor.getBytesWrittenForLastEntry(), updatedEntry.getCompressedSize());
            assertEquals(scatterZipOutputStream.streamCompressor.getBytesRead(), updatedEntry.getSize());
            assertEquals(scatterZipOutputStream.streamCompressor.getCrc32(), updatedEntry.getCrc());
        }
    }
}