package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WebPChunkXyzwLLM_Test {

    @Test
    public void testConstructorValidInput() {
        int type = 1;
        int size = 4;
        byte[] bytes = new byte[]{0, 1, 2, 3};
        assertDoesNotThrow(() -> new WebPChunkXyzw(type, size, bytes));
    }

    @Test
    public void testConstructorInvalidSize() {
        int type = 1;
        int size = 4;
        byte[] bytes = new byte[]{0, 1, 2};
        assertThrows(ImagingException.class, () -> new WebPChunkXyzw(type, size, bytes));
    }

    @Test
    public void testConstructorNullBytes() {
        int type = 1;
        int size = 4;
        byte[] bytes = null;
        assertThrows(NullPointerException.class, () -> new WebPChunkXyzw(type, size, bytes));
    }
}