package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkAnmfLLM_Test {

    @Test
    public void testWebPChunkAnmfCreation() throws ImagingException {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size];
        
        WebPChunkAnmf chunk = new WebPChunkAnmf(type, size, bytes);
        
        assertNotNull(chunk);
        assertEquals(type, chunk.type);
        assertEquals(size, chunk.size);
        assertArrayEquals(bytes, chunk.bytes);
    }

    @Test
    public void testWebPChunkAnmfCreationWithInvalidSize() {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size - 1]; // Invalid size
        
        assertThrows(ImagingException.class, () -> {
            new WebPChunkAnmf(type, size, bytes);
        });
    }
}