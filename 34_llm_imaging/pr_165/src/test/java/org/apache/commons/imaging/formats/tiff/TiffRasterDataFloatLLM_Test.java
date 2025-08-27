package org.apache.commons.imaging.formats.tiff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiffRasterDataFloatLLM_Test {

    @Test
    public void testConstructorWithValidData() {
        int width = 2;
        int height = 2;
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height, data);
        assertArrayEquals(data, rasterData.getData());
    }

    @Test
    public void testConstructorWithInvalidData() {
        int width = 2;
        int height = 2;
        float[] data = {1.0f, 2.0f}; // insufficient data
        assertThrows(IllegalArgumentException.class, () -> {
            new TiffRasterDataFloat(width, height, data);
        });
    }

    @Test
    public void testSetValueAndGetValue() {
        int width = 2;
        int height = 2;
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height);
        rasterData.setValue(1, 1, 5.0f);
        assertEquals(5.0f, rasterData.getValue(1, 1));
    }

    @Test
    public void testSetIntValueAndGetIntValue() {
        int width = 2;
        int height = 2;
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height);
        rasterData.setIntValue(1, 1, 5);
        assertEquals(5, rasterData.getIntValue(1, 1));
    }

    @Test
    public void testGetSimpleStatistics() {
        int width = 2;
        int height = 2;
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height, data);
        TiffRasterStatistics stats = rasterData.getSimpleStatistics();
        assertNotNull(stats);
    }

    @Test
    public void testGetSimpleStatisticsWithExclusion() {
        int width = 2;
        int height = 2;
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height, data);
        TiffRasterStatistics stats = rasterData.getSimpleStatistics(2.0f);
        assertNotNull(stats);
    }

    @Test
    public void testGetData() {
        int width = 2;
        int height = 2;
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height, data);
        assertArrayEquals(data, rasterData.getData());
    }

    @Test
    public void testGetIntData() {
        int width = 2;
        int height = 2;
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(width, height, data);
        int[] expectedIntData = {1, 2, 3, 4};
        assertArrayEquals(expectedIntData, rasterData.getIntData());
    }
}