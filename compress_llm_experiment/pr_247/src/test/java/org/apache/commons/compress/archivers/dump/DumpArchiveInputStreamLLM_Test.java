package org.apache.commons.compress.archivers.dump;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.nio.file.Files;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class DumpArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testReadBufferSizeAdjustment() throws Exception {
        try (InputStream in = Files.newInputStream(getFile("test.dump").toPath());
             DumpArchiveInputStream archive = new DumpArchiveInputStream(in)) {
            final ArchiveEntry e = archive.getNextEntry();
            byte[] buffer = new byte[1024];
            int bytesRead = archive.read(buffer, 0, buffer.length);
            assertTrue("Bytes read should be greater than 0", bytesRead > 0);
        }
    }

    @Test
    public void testReadBufferSizeAdjustmentAtEOF() throws Exception {
        try (InputStream in = Files.newInputStream(getFile("test.dump").toPath());
             DumpArchiveInputStream archive = new DumpArchiveInputStream(in)) {
            final ArchiveEntry e = archive.getNextEntry();
            IOUtils.toByteArray(archive);
            byte[] buffer = new byte[1024];
            assertEquals("Should return -1 at EOF", -1, archive.read(buffer, 0, buffer.length));
        }
    }
}