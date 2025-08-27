package org.apache.commons.compress.archivers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.ByteUtils;
import org.junit.jupiter.api.Test;

public class ArchiveStreamFactoryLLM_Test extends AbstractTestCase {

    /**
     * Test to ensure that the try-with-resources block correctly handles the 
     * closing of TarArchiveInputStream when detecting TAR archives.
     */
    @Test
    public void testDetectTarWithTryWithResources() throws Exception {
        try (final InputStream fis = newInputStream("bla.tar");
             final InputStream bis = new BufferedInputStream(fis)) {
            assertEquals(ArchiveStreamFactory.TAR, ArchiveStreamFactory.detect(bis));
        }
    }

    /**
     * Test to ensure that the try-with-resources block correctly handles the 
     * closing of TarArchiveInputStream when the TAR header checksum is invalid.
     */
    @Test
    public void testDetectInvalidTarChecksum() throws Exception {
        byte[] invalidTarHeader = new byte[512];
        try (InputStream in = new BufferedInputStream(new ByteArrayInputStream(invalidTarHeader))) {
            ArchiveException ae = assertThrows(ArchiveException.class, () -> ArchiveStreamFactory.detect(in));
            assertTrue(ae.getMessage().startsWith("No Archiver found"));
        }
    }

    /**
     * Test to ensure that the try-with-resources block correctly handles the 
     * closing of TarArchiveInputStream when the input stream is not a TAR archive.
     */
    @Test
    public void testDetectNonTarStream() throws Exception {
        byte[] nonTarHeader = "This is not a tar header".getBytes();
        try (InputStream in = new BufferedInputStream(new ByteArrayInputStream(nonTarHeader))) {
            ArchiveException ae = assertThrows(ArchiveException.class, () -> ArchiveStreamFactory.detect(in));
            assertTrue(ae.getMessage().startsWith("No Archiver found"));
        }
    }
}