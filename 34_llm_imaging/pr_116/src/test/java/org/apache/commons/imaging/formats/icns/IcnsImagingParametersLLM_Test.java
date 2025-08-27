package org.apache.commons.imaging.formats.icns;

import org.apache.commons.imaging.ImagingParameters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IcnsImagingParametersLLM_Test {

    @Test
    public void testIcnsImagingParametersInstance() {
        IcnsImagingParameters params = new IcnsImagingParameters();
        assertTrue(params instanceof ImagingParameters, "IcnsImagingParameters should be an instance of ImagingParameters");
    }
}