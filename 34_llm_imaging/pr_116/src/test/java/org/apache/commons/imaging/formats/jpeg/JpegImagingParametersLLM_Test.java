package org.apache.commons.imaging.formats.jpeg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JpegImagingParametersLLM_Test {

    @Test
    public void testJpegImagingParametersInstantiation() {
        JpegImagingParameters params = new JpegImagingParameters();
        assertNotNull(params, "JpegImagingParameters instance should not be null");
    }

    @Test
    public void testJpegImagingParametersIsInstanceOfXmpImagingParameters() {
        JpegImagingParameters params = new JpegImagingParameters();
        assertTrue(params instanceof XmpImagingParameters, "JpegImagingParameters should be an instance of XmpImagingParameters");
    }
}