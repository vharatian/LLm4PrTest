package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkAnimLLM_Test {

    @Test
    public void testWebPChunkAnimCreation() {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size];
        
        assertDoesNotThrow(() -> {
            WebPChunkAnim chunk = new WebPChunkAnim(type, size, bytes);
            assertNotNull(chunk);
        });
    }

    @Test
    public void testWebPChunkAnimCreationWithInvalidSize() {
        int type = 1;
        int size = 10;
        byte[] bytes = new byte[size - 1]; // Invalid size
        
        assertThrows(ImagingException.class, () -> {
            new WebPChunkAnim(type, size, bytes);
        });
    }
}