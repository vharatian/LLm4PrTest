package org.apache.commons.imaging.formats.tiff.photometricinterpreters;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class PhotometricInterpreterLogLuvLLM_Test {

    @Test
    public void testInterpretPixelWithValidSamples() throws ImageReadException, IOException {
        PhotometricInterpreterLogLuv interpreter = new PhotometricInterpreterLogLuv(3, new int[]{8, 8, 8}, 1, 100, 100);
        ImageBuilder imageBuilder = new ImageBuilder(100, 100, false);
        int[] samples = {128, 128, 128};
        interpreter.interpretPixel(imageBuilder, samples, 50, 50);
        int rgb = imageBuilder.getRGB(50, 50);
        assertEquals(0xff808080, rgb);
    }

    @Test
    public void testInterpretPixelWithInvalidSamples() {
        PhotometricInterpreterLogLuv interpreter = new PhotometricInterpreterLogLuv(3, new int[]{8, 8, 8}, 1, 100, 100);
        ImageBuilder imageBuilder = new ImageBuilder(100, 100, false);
        int[] samples = {128, 128};
        assertThrows(ImageReadException.class, () -> {
            interpreter.interpretPixel(imageBuilder, samples, 50, 50);
        });
    }

    @Test
    public void testGetTristimulusValues() {
        PhotometricInterpreterLogLuv interpreter = new PhotometricInterpreterLogLuv(3, new int[]{8, 8, 8}, 1, 100, 100);
        PhotometricInterpreterLogLuv.TristimulusValues values = interpreter.getTristimulusValues(128, 128, 128);
        assertNotNull(values);
        assertTrue(values.x > 0);
        assertTrue(values.y > 0);
        assertTrue(values.z > 0);
    }

    @Test
    public void testGetRgbValues() {
        PhotometricInterpreterLogLuv interpreter = new PhotometricInterpreterLogLuv(3, new int[]{8, 8, 8}, 1, 100, 100);
        PhotometricInterpreterLogLuv.TristimulusValues tristimulusValues = interpreter.getTristimulusValues(128, 128, 128);
        PhotometricInterpreterLogLuv.RgbValues rgbValues = interpreter.getRgbValues(tristimulusValues);
        assertNotNull(rgbValues);
        assertTrue(rgbValues.r >= 0 && rgbValues.r <= 255);
        assertTrue(rgbValues.g >= 0 && rgbValues.g <= 255);
        assertTrue(rgbValues.b >= 0 && rgbValues.b <= 255);
    }
}