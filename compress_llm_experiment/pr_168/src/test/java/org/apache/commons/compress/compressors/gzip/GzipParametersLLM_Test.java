package org.apache.commons.compress.compressors.gzip;

import org.junit.Test;
import static org.junit.Assert.*;

public class GzipParametersLLM_Test {

    @Test
    public void testGetBufferSizeDefault() {
        GzipParameters params = new GzipParameters();
        assertEquals(512, params.getBufferSize());
    }

    @Test
    public void testSetAndGetBufferSize() {
        GzipParameters params = new GzipParameters();
        params.setBufferSize(1024);
        assertEquals(1024, params.getBufferSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBufferSizeInvalid() {
        GzipParameters params = new GzipParameters();
        params.setBufferSize(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBufferSizeNegative() {
        GzipParameters params = new GzipParameters();
        params.setBufferSize(-1);
    }
}