package org.apache.commons.imaging.formats.tiff.photometricinterpreters;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhotometricInterpreterLLM_Test {

    @Test
    public void testPhotometricInterpreterConstructor() {
        int samplesPerPixel = 3;
        int[] bitsPerSample = {8, 8, 8};
        int predictor = 2;
        int width = 100;
        int height = 100;

        PhotometricInterpreter interpreter = new PhotometricInterpreter(samplesPerPixel, bitsPerSample, predictor, width, height) {
            @Override
            public void interpretPixel(ImageBuilder imageBuilder, int[] samples, int x, int y) throws ImageReadException, IOException {
                // No-op for test
            }
        };

        assertEquals(samplesPerPixel, interpreter.samplesPerPixel);
        assertEquals(bitsPerSample, interpreter.bitsPerSample);
        assertEquals(predictor, interpreter.predictor);
        assertEquals(width, interpreter.width);
        assertEquals(height, interpreter.height);
    }

    @Test
    public void testGetBitsPerSample() {
        int samplesPerPixel = 3;
        int[] bitsPerSample = {8, 8, 8};
        int predictor = 2;
        int width = 100;
        int height = 100;

        PhotometricInterpreter interpreter = new PhotometricInterpreter(samplesPerPixel, bitsPerSample, predictor, width, height) {
            @Override
            public void interpretPixel(ImageBuilder imageBuilder, int[] samples, int x, int y) throws ImageReadException, IOException {
                // No-op for test
            }
        };

        assertEquals(8, interpreter.getBitsPerSample(0));
        assertEquals(8, interpreter.getBitsPerSample(1));
        assertEquals(8, interpreter.getBitsPerSample(2));
    }
}