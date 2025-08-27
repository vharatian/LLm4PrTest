package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebPChunkVp8LLM_Test {

    @Test
    void testValidVP8Chunk() throws ImagingException {
        byte[] validBytes = new byte[]{
            0b0001_0000, 0, 0, // Frame Header
            (byte) 0x9D, 0x01, 0x2A, // Key Frame Signature
            0x40, 0x01, // Width
            0x80, 0x02  // Height
        };
        WebPChunkVp8 chunk = new WebPChunkVp8(0, validBytes.length, validBytes);

        assertEquals(0, chunk.getVersionNumber());
        assertEquals(320, chunk.getWidth());
        assertEquals(640, chunk.getHeight());
        assertEquals(1, chunk.getHorizontalScale());
        assertEquals(2, chunk.getVerticalScale());
    }

    @Test
    void testInvalidVP8ChunkSize() {
        byte[] invalidBytes = new byte[]{0, 0, 0};
        assertThrows(ImagingException.class, () -> new WebPChunkVp8(0, invalidBytes.length, invalidBytes));
    }

    @Test
    void testInvalidVP8ChunkFrameType() {
        byte[] invalidBytes = new byte[]{
            0b0000_0001, 0, 0, // Invalid Frame Header (interframe)
            (byte) 0x9D, 0x01, 0x2A, // Key Frame Signature
            0x40, 0x01, // Width
            0x80, 0x02  // Height
        };
        assertThrows(ImagingException.class, () -> new WebPChunkVp8(0, invalidBytes.length, invalidBytes));
    }

    @Test
    void testInvalidVP8ChunkSignature() {
        byte[] invalidBytes = new byte[]{
            0b0001_0000, 0, 0, // Frame Header
            (byte) 0x9D, 0x01, 0x00, // Invalid Key Frame Signature
            0x40, 0x01, // Width
            0x80, 0x02  // Height
        };
        assertThrows(ImagingException.class, () -> new WebPChunkVp8(0, invalidBytes.length, invalidBytes));
    }

    @Test
    void testInvalidVP8ChunkDisplayFlag() {
        byte[] invalidBytes = new byte[]{
            0b0000_0000, 0, 0, // Frame Header with display flag off
            (byte) 0x9D, 0x01, 0x2A, // Key Frame Signature
            0x40, 0x01, // Width
            0x80, 0x02  // Height
        };
        assertThrows(ImagingException.class, () -> new WebPChunkVp8(0, invalidBytes.length, invalidBytes));
    }
}