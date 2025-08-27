package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.After;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.zip.ZipEntry;
import static org.apache.commons.compress.AbstractTestCase.tryHardToDelete;
import static org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest.createZipArchiveEntryRequest;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ScatterZipOutputStreamLLM_Test {
    private File scatterFile = null;
    private File target = null;

    @After
    public void cleanup() {
        tryHardToDelete(scatterFile);
        tryHardToDelete(target);
    }

    @Test
    public void testZipEntryWriterInitialization() throws Exception {
        scatterFile = File.createTempFile("scattertest", ".notzip");
        final ScatterZipOutputStream scatterZipOutputStream = ScatterZipOutputStream.fileBased(scatterFile);
        final byte[] B_PAYLOAD = "RBBBBBBS".getBytes();
        final ZipArchiveEntry zab = new ZipArchiveEntry("b.txt");
        zab.setMethod(ZipEntry.DEFLATED);
        final ByteArrayInputStream payload = new ByteArrayInputStream(B_PAYLOAD);
        scatterZipOutputStream.addArchiveEntry(createZipArchiveEntryRequest(zab, createPayloadSupplier(payload)));
        
        // Initialize ZipEntryWriter and verify it's not null
        ScatterZipOutputStream.ZipEntryWriter zipEntryWriter = scatterZipOutputStream.zipEntryWriter();
        assertEquals(zipEntryWriter != null, true);

        scatterZipOutputStream.close();
    }

    private InputStreamSupplier createPayloadSupplier(final ByteArrayInputStream payload) {
        return () -> payload;
    }
}