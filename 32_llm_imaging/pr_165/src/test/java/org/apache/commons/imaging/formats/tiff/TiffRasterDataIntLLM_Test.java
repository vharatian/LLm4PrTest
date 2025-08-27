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
    public void testConstructorWithNullData() {
        assertThrows(IllegalArgumentException.class, () -> new TiffRasterDataInt(width, height, null));
    }

    @Test
    public void testConstructorWithInsufficientData() {
        int[] insufficientData = new int[width * height - 1];
        assertThrows(IllegalArgumentException.class, () -> new TiffRasterDataInt(width, height, insufficientData));
    }

    @Test
    public void testGetDataReturnsDirectReference() {
        int[] dataRef = raster.getIntData();
        dataRef[0] = -999;
        assertEquals(-999, raster.getIntValue(0, 0), "Direct reference to internal data array was not returned");
    }

    @Test
    public void testGetDataReturnsCorrectFloatValues() {
        float[] floatData = raster.getData();
        for (int i = 0; i < data.length; i++) {
            assertEquals((float) data[i], floatData[i], "Float data conversion failed at index " + i);
        }
    }

    @Test
    public void testGetDataType() {
        TiffRasterDataType dataType = raster.getDataType();
        assertEquals(TiffRasterDataType.INTEGER, dataType, "Unexpected data type " + dataType.name());
    }

    @Test
    public void testSetValueWithFloat() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                instance.setValue(x, y, index + 0.4f);
                int test = (int) instance.getValue(x, y);
                assertEquals(index, test, "Set/get value test failed");
            }
        }
    }

    @Test
    public void testSetIntValue() {
        final TiffRasterData instance = new TiffRasterDataInt(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int index = y * width + x;
                instance.setIntValue(x, y, index);
                int test = instance.getIntValue(x, y);
                assertEquals(index, test, "Set/get int value test failed");
            }
        }
    }

    @Test
    public void testGetSimpleStatistics() {
        final TiffRasterStatistics result = raster.getSimpleStatistics();
        assertEquals(0, result.getMinValue(), "Min value failure");
        assertEquals(width * height - 1, result.getMaxValue(), "Max value failure");
        assertEquals(meanValue, result.getMeanValue(), "Mean value failure");
    }

    @Test
    public void testGetSimpleStatisticsWithExclusion() {
        final TiffRasterStatistics result = raster.getSimpleStatistics(width * height - 1);
        assertEquals(width * height - 2, result.getMaxValue(), "Max value failure");
    }

    @Test
    public void testBadCoordinates() {
        try {
            final int[] sample = new int[100];
            final TiffRasterData raster = new TiffRasterDataInt(10, 10, sample);
            raster.getIntValue(11, 11);
            fail("Access method getValue() did not detect bad coordinates");
        } catch (final IllegalArgumentException illArgEx) {
        }
        try {
            final int[] sample = new int[100];
            final TiffRasterData raster = new TiffRasterDataInt(10, 10, sample);
            raster.setValue(11, 11, 5.0f);
            fail("Access method setValue() did not detect bad coordinates");
        } catch (final IllegalArgumentException illArgEx) {
        }
    }
}