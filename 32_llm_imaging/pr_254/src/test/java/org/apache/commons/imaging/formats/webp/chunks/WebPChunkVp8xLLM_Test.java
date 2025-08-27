package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkVp8xLLM_Test {

    @Test
    public void testConstructorValidData() throws ImagingException {
        byte[] validBytes = new byte[]{0b0010_1110, 0, 0, 0, 0, 0, 1, 0, 0, 1};
        WebPChunkVp8x chunk = new WebPChunkVp8x(0, 10, validBytes);

        assertTrue(chunk.hasIcc());
        assertTrue(chunk.hasAlpha());
        assertTrue(chunk.hasExif());
        assertTrue(chunk.hasXmp());
        assertTrue(chunk.hasAnimation());
        assertEquals(2, chunk.getCanvasWidth());
        assertEquals(2, chunk.getCanvasHeight());
    }

    @Test
    public void testConstructorInvalidSize() {
        byte[] invalidSizeBytes = new byte[9];
        assertThrows(ImagingException.class, () -> new WebPChunkVp8x(0, 9, invalidSizeBytes));
    }

    @Test
    public void testConstructorIllegalCanvasSize() {
        byte[] invalidCanvasSizeBytes = new byte[]{0, 0, 0, 0, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
        assertThrows(ImagingException.class, () -> new WebPChunkVp8x(0, 10, invalidCanvasSizeBytes));
    }

    @Test
    public void testDump() throws ImagingException {
        byte[] validBytes = new byte[]{0b0010_1110, 0, 0, 0, 0, 0, 1, 0, 0, 1};
        WebPChunkVp8x chunk = new WebPChunkVp8x(0, 10, validBytes);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        chunk.dump(pw, 0);
        String dumpOutput = sw.toString();

        assertTrue(dumpOutput.contains("ICCP: true"));
        assertTrue(dumpOutput.contains("Alpha: true"));
        assertTrue(dumpOutput.contains("EXIF: true"));
        assertTrue(dumpOutput.contains("XMP: true"));
        assertTrue(dumpOutput.contains("Animation: true"));
        assertTrue(dumpOutput.contains("Canvas Width: 2"));
        assertTrue(dumpOutput.contains("Canvas Height: 2"));
    }
}