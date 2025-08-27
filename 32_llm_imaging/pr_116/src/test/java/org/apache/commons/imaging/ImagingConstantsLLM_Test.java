package org.apache.commons.imaging;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImagingConstantsLLM_Test {

    @Test
    public void testEmptyByteArray() {
        assertNotNull(ImagingConstants.EMPTY_BYTE_ARRAY);
        assertEquals(0, ImagingConstants.EMPTY_BYTE_ARRAY.length);
    }
}