package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.common.XmpImagingParameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GifImagingParametersLLM_Test {

    @Test
    public void testGifImagingParametersInstance() {
        GifImagingParameters params = new GifImagingParameters();
        assertTrue(params instanceof XmpImagingParameters, "GifImagingParameters should be an instance of XmpImagingParameters");
    }
}