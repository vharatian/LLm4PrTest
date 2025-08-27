package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TiffRasterDataLLM_Test {

    int width = 11;
    int height = 10;
    float[] data;
    TiffRasterData raster;
    float meanValue;

    public TiffRasterDataTest2() {
        double sum = 0;
        data = new float[width * height];
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[k] = k;
                sum += k;
                k++;
            }
        }
        raster = new TiffRasterDataFloat(width, height, data);
        meanValue = (float) (sum / k);
    }

    @Test
    public void testSetValueWithSampleIndex() {
        final TiffRasterData instance = new TiffRasterDataFloat(width, height, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, 1, index);
                final int test = (int) instance.getValue(x, y, 1);
                assertEquals(index, test, "Set/get value test failed at (" + x + "," + y + ")");
                instance.setIntValue(x, y, 1, index);
                final int iTest = instance.getIntValue(x, y, 1);
                assertEquals(index, iTest, "Get/set value test failed at (" + x + "," + y + ")");
            }
        }
    }

    @Test
    public void testGetValueWithSampleIndex() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                final int test = (int) raster.getValue(x, y, 0);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
                final int iTest = raster.getIntValue(x, y, 0);
                assertEquals(index, iTest, "Get into source data test failed at (" + x + "," + y + ")");
            }
        }
    }

    @Test
    public void testConstructorWithSamplesPerPixel() {
        assertThrows(IllegalArgumentException.class, () -> new TiffRasterDataFloat(10, 10, 0), "Constructor did not detect bad samplesPerPixel");
    }

    @Test
    public void testCheckCoordinatesAndComputeIndex() {
        final TiffRasterData instance = new TiffRasterDataFloat(width, height, 2);
        assertThrows(IllegalArgumentException.class, () -> instance.checkCoordinatesAndComputeIndex(0, 0, -1), "Method did not detect bad sample index");
        assertThrows(IllegalArgumentException.class, () -> instance.checkCoordinatesAndComputeIndex(0, 0, 2), "Method did not detect bad sample index");
    }

    @Test
    public void testGetSamplesPerPixel() {
        assertEquals(1, raster.getSamplesPerPixel(), "Incorrect number of samples per pixel");
    }
}