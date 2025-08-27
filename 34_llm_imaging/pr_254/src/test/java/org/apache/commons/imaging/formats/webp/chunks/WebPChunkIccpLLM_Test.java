package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WebPChunkIccpLLM_Test {

    @Test
    void testWebPChunkIccpConstructorValidData() {
        int type = 0x49434350; // 'ICCP' in hex
        int size = 10;
        byte[] bytes = new byte[size];
        assertDoesNotThrow(() -> new WebPChunkIccp(type, size, bytes));
    }

    @Test
    void testWebPChunkIccpConstructorInvalidSize() {
        int type = 0x49434350; // 'ICCP' in hex
        int size = 10;
        byte[] bytes = new byte[size - 1];
        assertThrows(ImagingException.class, () -> new WebPChunkIccp(type, size, bytes));
    }

    @Test
    void testWebPChunkIccpConstructorNullBytes() {
        int type = 0x49434350; // 'ICCP' in hex
        int size = 10;
        byte[] bytes = null;
        assertThrows(ImagingException.class, () -> new WebPChunkIccp(type, size, bytes));
    }
}