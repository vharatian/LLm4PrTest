package org.apache.commons.imaging.formats.gif;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GifImagingParametersLLM_Test {

    @Test
    public void testGetStopReadingBeforeImageDataDefault() {
        GifImagingParameters params = new GifImagingParameters();
        assertFalse(params.getStopReadingBeforeImageData(), "Default value should be false");
    }

    @Test
    public void testSetStopReadingBeforeImageData() {
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(true);
        assertTrue(params.getStopReadingBeforeImageData(), "Value should be true after setting to true");

        params.setStopReadingBeforeImageData(false);
        assertFalse(params.getStopReadingBeforeImageData(), "Value should be false after setting to false");
    }
}