package org.apache.commons.imaging.formats.tiff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiffRasterDataFloatLLM_Test {

    @Test
    public void testConstructorWithWidthHeight() {
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20);
        assertEquals(10, rasterData.getWidth());
        assertEquals(20, rasterData.getHeight());
        assertEquals(1, rasterData.getSamplesPerPixel());
        assertNotNull(rasterData.getData());
    }

    @Test
    public void testConstructorWithWidthHeightSamplesPerPixel() {
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, 3);
        assertEquals(10, rasterData.getWidth());
        assertEquals(20, rasterData.getHeight());
        assertEquals(3, rasterData.getSamplesPerPixel());
        assertNotNull(rasterData.getData());
    }

    @Test
    public void testConstructorWithWidthHeightData() {
        float[] data = new float[200];
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, data);
        assertEquals(10, rasterData.getWidth());
        assertEquals(20, rasterData.getHeight());
        assertEquals(1, rasterData.getSamplesPerPixel());
        assertArrayEquals(data, rasterData.getData());
    }

    @Test
    public void testConstructorWithWidthHeightSamplesPerCellData() {
        float[] data = new float[600];
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, 3, data);
        assertEquals(10, rasterData.getWidth());
        assertEquals(20, rasterData.getHeight());
        assertEquals(3, rasterData.getSamplesPerPixel());
        assertArrayEquals(data, rasterData.getData());
    }

    @Test
    public void testSetValueWithSampleIndex() {
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, 3);
        rasterData.setValue(5, 5, 2, 42.0f);
        assertEquals(42.0f, rasterData.getValue(5, 5, 2));
    }

    @Test
    public void testGetValueWithSampleIndex() {
        float[] data = new float[600];
        data[205] = 42.0f;
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, 3, data);
        assertEquals(42.0f, rasterData.getValue(5, 5, 2));
    }

    @Test
    public void testSetIntValueWithSampleIndex() {
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, 3);
        rasterData.setIntValue(5, 5, 2, 42);
        assertEquals(42, rasterData.getIntValue(5, 5, 2));
    }

    @Test
    public void testGetIntValueWithSampleIndex() {
        float[] data = new float[600];
        data[205] = 42.0f;
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, 3, data);
        assertEquals(42, rasterData.getIntValue(5, 5, 2));
    }

    @Test
    public void testGetData() {
        float[] data = new float[200];
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, data);
        assertArrayEquals(data, rasterData.getData());
    }

    @Test
    public void testGetIntData() {
        float[] data = new float[200];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        TiffRasterDataFloat rasterData = new TiffRasterDataFloat(10, 20, data);
        int[] intData = rasterData.getIntData();
        for (int i = 0; i < data.length; i++) {
            assertEquals((int) data[i], intData[i]);
        }
    }
}