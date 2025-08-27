package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class TiffRasterDataLLM_Test {

    int width = 11;
    int height = 10;
    float[] data;
    TiffRasterData raster;

    public TiffRasterDataTest2() {
        data = new float[width * height];
        int k = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                data[k] = k;
                k++;
            }
        }
        raster = new TiffRasterDataFloat(width, height, data);
    }

    @Test
    public void testCheckCoordinatesAndComputeIndex() {
        assertThrows(IllegalArgumentException.class, () -> {
            raster.checkCoordinatesAndComputeIndex(-1, 5);
        }, "Expected checkCoordinatesAndComputeIndex() to throw, but it didn't");

        assertThrows(IllegalArgumentException.class, () -> {
            raster.checkCoordinatesAndComputeIndex(5, -1);
        }, "Expected checkCoordinatesAndComputeIndex() to throw, but it didn't");

        assertThrows(IllegalArgumentException.class, () -> {
            raster.checkCoordinatesAndComputeIndex(width, 5);
        }, "Expected checkCoordinatesAndComputeIndex() to throw, but it didn't");

        assertThrows(IllegalArgumentException.class, () -> {
            raster.checkCoordinatesAndComputeIndex(5, height);
        }, "Expected checkCoordinatesAndComputeIndex() to throw, but it didn't");

        int index = raster.checkCoordinatesAndComputeIndex(5, 5);
        assertEquals(5 * width + 5, index, "Index computation failed");
    }

    @Test
    public void testGetIntData() {
        int[] intData = raster.getIntData();
        for (int i = 0; i < data.length; i++) {
            assertEquals((int) data[i], intData[i], "Int data mismatch at index " + i);
        }
    }

    @Test
    public void testGetData() {
        float[] floatData = raster.getData();
        assertArrayEquals(data, floatData, "Float data mismatch");
    }

    @Test
    public void testSetIntValue() {
        raster.setIntValue(0, 0, 42);
        assertEquals(42, raster.getIntValue(0, 0), "Set/get int value failed at (0,0)");
    }

    @Test
    public void testGetIntValue() {
        raster.setIntValue(1, 1, 24);
        assertEquals(24, raster.getIntValue(1, 1), "Get int value failed at (1,1)");
    }
}