package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.junit.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testRealBlockSizeInitialization() throws Exception {
        // Test with BLOCK_SIZE_UNSPECIFIED
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             TarArchiveOutputStream tos = new TarArchiveOutputStream(bos, -511)) {
            assertEquals("Block size should be initialized to RECORD_SIZE when unspecified",
                    512, tos.getRecordSize());
        }

        // Test with a specified block size
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             TarArchiveOutputStream tos = new TarArchiveOutputStream(bos, 1024)) {
            assertEquals("Block size should be initialized to the specified value",
                    512, tos.getRecordSize());
        }

        // Test with an invalid block size
        try {
            new TarArchiveOutputStream(new ByteArrayOutputStream(), 513);
            fail("Should have thrown an IllegalArgumentException for invalid block size");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}