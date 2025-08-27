package org.apache.commons.imaging;

import org.apache.commons.imaging.common.BufferedImageFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImagingParametersLLM_Test {

    @Test
    public void testDefaultValues() {
        ImagingParameters params = new ImagingParameters();
        assertFalse(params.isStrict(), "Default strict value should be false");
        assertNull(params.getFileName(), "Default fileName should be null");
        assertNull(params.getBufferedImageFactory(), "Default bufferedImageFactory should be null");
        assertNull(params.getPixelDensity(), "Default pixelDensity should be null");
    }

    @Test
    public void testSetAndGetStrict() {
        ImagingParameters params = new ImagingParameters();
        params.setStrict(true);
        assertTrue(params.isStrict(), "Strict value should be true after setting it to true");
        params.setStrict(false);
        assertFalse(params.isStrict(), "Strict value should be false after setting it to false");
    }

    @Test
    public void testSetAndGetFileName() {
        ImagingParameters params = new ImagingParameters();
        String fileName = "testFileName";
        params.setFileName(fileName);
        assertEquals(fileName, params.getFileName(), "FileName should match the set value");
    }

    @Test
    public void testSetAndGetBufferedImageFactory() {
        ImagingParameters params = new ImagingParameters();
        BufferedImageFactory factory = new BufferedImageFactory() {
            // Implement necessary methods if required
        };
        params.setBufferedImageFactory(factory);
        assertEquals(factory, params.getBufferedImageFactory(), "BufferedImageFactory should match the set value");
    }

    @Test
    public void testSetAndGetPixelDensity() {
        ImagingParameters params = new ImagingParameters();
        PixelDensity density = new PixelDensity(300, 300);
        params.setPixelDensity(density);
        assertEquals(density, params.getPixelDensity(), "PixelDensity should match the set value");
    }
}