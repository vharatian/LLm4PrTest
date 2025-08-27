package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class WebPChunkLLM_Test {

    @Test
    void testConstructorValid() throws ImagingException {
        int type = 0x52494646; // "RIFF" in ASCII
        int size = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};

        WebPChunk chunk = new WebPChunk(type, size, bytes) {};

        assertEquals(type, chunk.getType());
        assertEquals(size, chunk.getPayloadSize());
        assertArrayEquals(bytes, chunk.getBytes());
        assertEquals(12, chunk.getChunkSize()); // 4 (FourCC) + 4 (Size) + 4 (Payload)
    }

    @Test
    void testConstructorInvalidSize() {
        int type = 0x52494646; // "RIFF" in ASCII
        int size = 3;
        byte[] bytes = new byte[]{1, 2, 3, 4};

        assertThrows(ImagingException.class, () -> new WebPChunk(type, size, bytes) {});
    }

    @Test
    void testGetTypeDescription() throws ImagingException {
        int type = 0x52494646; // "RIFF" in ASCII
        int size = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};

        WebPChunk chunk = new WebPChunk(type, size, bytes) {};

        assertEquals("RIFF", chunk.getTypeDescription());
    }

    @Test
    void testGetBytes() throws ImagingException {
        int type = 0x52494646; // "RIFF" in ASCII
        int size = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};

        WebPChunk chunk = new WebPChunk(type, size, bytes) {};

        byte[] retrievedBytes = chunk.getBytes();
        assertArrayEquals(bytes, retrievedBytes);

        // Ensure the returned array is a copy
        retrievedBytes[0] = 0;
        assertNotEquals(retrievedBytes[0], chunk.getBytes()[0]);
    }

    @Test
    void testDump() throws ImagingException {
        int type = 0x52494646; // "RIFF" in ASCII
        int size = 4;
        byte[] bytes = new byte[]{1, 2, 3, 4};

        WebPChunk chunk = new WebPChunk(type, size, bytes) {};

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(baos);

        try {
            chunk.dump(pw, 10);
            pw.flush();
            String output = baos.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Chunk RIFF at offset 10, length 12"));
            assertTrue(output.contains("payload size 4"));
        } catch (IOException e) {
            fail("IOException should not occur");
        }
    }
}