package org.apache.commons.imaging.formats.tiff.constants;

import org.junit.Test;
import static org.junit.Assert.*;

public class TiffTagConstantsLLM_Test {

    @Test
    public void testSampleFormatValueIEEEComplexFloat() {
        assertEquals(6, TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_COMPLEX_FLOAT);
    }
}