package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TiffRasterDataIntLLM_Test {

    int width = 11;
    int height = 10;
    int[] data;
    TiffRasterData raster;
    float meanValue;

    public TiffRasterDataIntTest2() {
        double sum = 0;
        data = new int[width * height];
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[k] = k;
                sum += k;
                k++;
            }
        }
        raster = new TiffRasterDataInt(width, height, data);
        meanValue = (float) (sum / k);
    }

    @Test
    public void testSetValueWithSample() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height, 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + height;
                instance.setValue(x, y, 1, index + 0.4f);
                int test = (int) instance.getValue(x, y, 1);
                assertEquals(index, test, "Set/get value test failed");
                instance.setIntValue(x, y, 1, index);
                test = instance.getIntValue(x, y, 1);
                assertEquals(index, test, "Set/get int value test failed");
            }
        }
    }

    @Test
    public void testGetValueWithSample() {
        int[] data = new int[width * height * 2];
        data[width * height] = 77;
        TiffRasterDataInt instance = new TiffRasterDataInt(width, height, 2, data);
        int test = instance.getIntValue(0, 0, 1);
        assertEquals(77, test, "Get into source data test failed at (0, 0, 1)");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                test = (int) raster.getValue(x, y, 0);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
                test = raster.getIntValue(x, y, 0);
                assertEquals(index, test, "Get into source data test failed at (" + x + "," + y + ")");
            }
        }
    }

    @Test
    public void testConstructorWithSamplesPerPixel() {
        TiffRasterDataInt instance = new TiffRasterDataInt(width, height, 2);
        assertEquals(width, instance.getWidth(), "Width mismatch");
        assertEquals(height, instance.getHeight(), "Height mismatch");
        assertEquals(2, instance.getSamplesPerPixel(), "Samples per pixel mismatch");
    }

    @Test
    public void testConstructorWithSamplesPerPixelAndData() {
        int[] data = new int[width * height * 2];
        TiffRasterDataInt instance = new TiffRasterDataInt(width, height, 2, data);
        assertEquals(width, instance.getWidth(), "Width mismatch");
        assertEquals(height, instance.getHeight(), "Height mismatch");
        assertEquals(2, instance.getSamplesPerPixel(), "Samples per pixel mismatch");
        assertArrayEquals(data, instance.getIntData(), "Data array mismatch");
    }

    @Test
    public void testBadConstructorWithSamplesPerPixel() {
        assertThrows(IllegalArgumentException.class, () -> new TiffRasterDataInt(1, 1, 0), "Constructor did not detect bad samplesPerPixel");
        final int[] s = new int[10];
        assertThrows(IllegalArgumentException.class, () -> new TiffRasterDataInt(2, 3, 2, s), "Constructor did not detect insufficient input array size");
    }

    @Test
    public void testBadCoordinatesWithSamples() {
        try {
            final int[] sample = new int[100];
            final TiffRasterData raster = new TiffRasterDataInt(10, 10, 2, sample);
            raster.getIntValue(11, 11, 1);
            fail("Access method getValue() did not detect bad coordinates");
        } catch (final IllegalArgumentException illArgEx) {
        }
        try {
            final int[] sample = new int[100];
            final TiffRasterData raster = new TiffRasterDataInt(10, 10, 2, sample);
            raster.setValue(11, 11, 1, 5.0f);
            fail("Access method setValue() did not detect bad coordinates");
        } catch (final IllegalArgumentException illArgEx) {
        }
    }
}