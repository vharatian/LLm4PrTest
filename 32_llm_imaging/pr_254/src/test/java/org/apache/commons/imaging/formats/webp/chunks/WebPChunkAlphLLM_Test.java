package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WebPChunkAlphLLM_Test {

    @Test
    void testWebPChunkAlphConstructorValid() {
        int type = 0x41505041; // 'ALPH' in ASCII
        int size = 10;
        byte[] bytes = new byte[size];
        assertDoesNotThrow(() -> new WebPChunkAlph(type, size, bytes));
    }

    @Test
    void testWebPChunkAlphConstructorInvalidSize() {
        int type = 0x41505041; // 'ALPH' in ASCII
        int size = 10;
        byte[] bytes = new byte[size - 1]; // Incorrect size
        assertThrows(ImagingException.class, () -> new WebPChunkAlph(type, size, bytes));
    }

    @Test
    void testWebPChunkAlphConstructorNullBytes() {
        int type = 0x41505041; // 'ALPH' in ASCII
        int size = 10;
        byte[] bytes = null;
        assertThrows(NullPointerException.class, () -> new WebPChunkAlph(type, size, bytes));
    }
}