package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkVp8lLLM_Test {

    @Test
    public void testValidVP8LChunk() throws ImagingException {
        byte[] validBytes = {0x2f, 0x10, 0x20, 0x30, 0x40};
        WebPChunkVp8l chunk = new WebPChunkVp8l(1, 5, validBytes);

        assertEquals(33, chunk.getImageWidth());
        assertEquals(769, chunk.getImageHeight());
        assertFalse(chunk.hasAlpha());
        assertEquals(2, chunk.getVersionNumber());
    }

    @Test
    public void testInvalidVP8LChunkHeader() {
        byte[] invalidBytes = {0x00, 0x10, 0x20, 0x30, 0x40};
        assertThrows(ImagingException.class, () -> new WebPChunkVp8l(1, 5, invalidBytes));
    }

    @Test
    public void testInvalidVP8LChunkSize() {
        byte[] invalidBytes = {0x2f, 0x10, 0x20, 0x30};
        assertThrows(ImagingException.class, () -> new WebPChunkVp8l(1, 4, invalidBytes));
    }

    @Test
    public void testInvalidVP8LVersion() {
        byte[] invalidBytes = {0x2f, 0x10, 0x20, 0x30, (byte) 0xE0};
        assertThrows(ImagingException.class, () -> new WebPChunkVp8l(1, 5, invalidBytes));
    }

    @Test
    public void testDump() throws ImagingException {
        byte[] validBytes = {0x2f, 0x10, 0x20, 0x30, 0x40};
        WebPChunkVp8l chunk = new WebPChunkVp8l(1, 5, validBytes);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        chunk.dump(pw, 0);
        String dumpOutput = sw.toString();

        assertTrue(dumpOutput.contains("Version Number: 2"));
        assertTrue(dumpOutput.contains("Image Width: 33"));
        assertTrue(dumpOutput.contains("Image Height: 769"));
        assertTrue(dumpOutput.contains("Alpha: false"));
    }
}