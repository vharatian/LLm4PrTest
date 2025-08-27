package org.apache.commons.imaging.formats.ico;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IcoImagingParametersLLM_Test {

    @Test
    public void testIcoImagingParametersInstantiation() {
        IcoImagingParameters params = new IcoImagingParameters();
        assertNotNull(params, "IcoImagingParameters instance should not be null");
    }

    @Test
    public void testIcoImagingParametersIsInstanceOfImagingParameters() {
        IcoImagingParameters params = new IcoImagingParameters();
        assertTrue(params instanceof ImagingParameters, "IcoImagingParameters should be an instance of ImagingParameters");
    }
}