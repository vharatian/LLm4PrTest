package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.Allocator;
import org.junit.jupiter.api.Test;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class TiffFieldLLM_Test {

    @Test
    public void testGetIntArrayValueWithLongArray() throws ImagingException {
        long[] longArray = {1L, 2L, 3L};
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return longArray;
            }
        }, longArray.length, 0, value, ByteOrder.BIG_ENDIAN, 0);

        int[] expected = {1, 2, 3};
        int[] result = tiffField.getIntArrayValue();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetLongArrayValueWithNumber() throws ImagingException {
        Number number = 123L;
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return number;
            }
        }, 1, 0, value, ByteOrder.BIG_ENDIAN, 0);

        long[] expected = {123L};
        long[] result = tiffField.getLongArrayValue();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetLongArrayValueWithNumberArray() throws ImagingException {
        Number[] numberArray = {1L, 2L, 3L};
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return numberArray;
            }
        }, numberArray.length, 0, value, ByteOrder.BIG_ENDIAN, 0);

        long[] expected = {1L, 2L, 3L};
        long[] result = tiffField.getLongArrayValue();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetLongArrayValueWithShortArray() throws ImagingException {
        short[] shortArray = {1, 2, 3};
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return shortArray;
            }
        }, shortArray.length, 0, value, ByteOrder.BIG_ENDIAN, 0);

        long[] expected = {1L, 2L, 3L};
        long[] result = tiffField.getLongArrayValue();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetLongArrayValueWithIntArray() throws ImagingException {
        int[] intArray = {1, 2, 3};
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return intArray;
            }
        }, intArray.length, 0, value, ByteOrder.BIG_ENDIAN, 0);

        long[] expected = {1L, 2L, 3L};
        long[] result = tiffField.getLongArrayValue();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetLongArrayValueWithLongArray() throws ImagingException {
        long[] longArray = {1L, 2L, 3L};
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return longArray;
            }
        }, longArray.length, 0, value, ByteOrder.BIG_ENDIAN, 0);

        long[] expected = {1L, 2L, 3L};
        long[] result = tiffField.getLongArrayValue();
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGetLongValue() throws ImagingException {
        Number number = 123L;
        byte[] value = new byte[0];
        TiffField tiffField = new TiffField(1, 1, new AbstractFieldType(1, 1) {
            @Override
            public Object getValue(TiffField entry) {
                return number;
            }
        }, 1, 0, value, ByteOrder.BIG_ENDIAN, 0);

        long expected = 123L;
        long result = tiffField.getLongValue();
        assertEquals(expected, result);
    }
}