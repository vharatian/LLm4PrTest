package org.apache.commons.imaging.formats.bmp;

import org.apache.commons.imaging.ImagingParameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BmpImagingParametersLLM_Test {

    @Test
    public void testBmpImagingParametersInstance() {
        BmpImagingParameters parameters = new BmpImagingParameters();
        assertTrue(parameters instanceof ImagingParameters, "BmpImagingParameters should be an instance of ImagingParameters");
    }
}