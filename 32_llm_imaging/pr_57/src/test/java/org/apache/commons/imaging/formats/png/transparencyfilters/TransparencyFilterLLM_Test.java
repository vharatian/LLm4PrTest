package org.apache.commons.imaging.formats.png.transparencyfilters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransparencyFilterLLM_Test {

    @Test
    public void testConstructorClonesByteArray() {
        byte[] originalBytes = {1, 2, 3, 4};
        TransparencyFilter filter = new TransparencyFilter(originalBytes) {
            @Override
            public int filter(int rgb, int index) {
                return 0;
            }
        };
        
        byte[] clonedBytes = filter.getBytes();
        
        assertNotSame(originalBytes, clonedBytes, "The byte array should be cloned, not referenced directly.");
        assertArrayEquals(originalBytes, clonedBytes, "The cloned byte array should be equal to the original byte array.");
    }
}