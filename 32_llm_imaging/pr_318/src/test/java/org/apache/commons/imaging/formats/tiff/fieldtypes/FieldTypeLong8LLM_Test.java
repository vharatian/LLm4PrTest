package org.apache.commons.imaging.formats.tiff.fieldtypes;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTypeLong8LLM_Test {

    @Test
    public void testGetValue_singleValue() {
        byte[] bytes = ByteConversions.toBytes(123456789L, ByteOrder.BIG_ENDIAN);
        TiffField tiffField = new TiffField(1, 1, 1, bytes, ByteOrder.BIG_ENDIAN);
        FieldTypeLong8 fieldTypeLong8 = new FieldTypeLong8(1, "Long8");

        Object value = fieldTypeLong8.getValue(tiffField);

        assertTrue(value instanceof Long);
        assertEquals(123456789L, value);
    }

    @Test
    public void testGetValue_multipleValues() {
        long[] longValues = {123456789L, 987654321L};
        byte[] bytes = ByteConversions.toBytes(longValues, ByteOrder.BIG_ENDIAN);
        TiffField tiffField = new TiffField(1, 1, 2, bytes, ByteOrder.BIG_ENDIAN);
        FieldTypeLong8 fieldTypeLong8 = new FieldTypeLong8(1, "Long8");

        Object value = fieldTypeLong8.getValue(tiffField);

        assertTrue(value instanceof long[]);
        assertArrayEquals(longValues, (long[]) value);
    }

    @Test
    public void testWriteData_singleInteger() throws ImagingException {
        FieldTypeLong8 fieldTypeLong8 = new FieldTypeLong8(1, "Long8");
        byte[] bytes = fieldTypeLong8.writeData(123456789, ByteOrder.BIG_ENDIAN);

        assertArrayEquals(ByteConversions.toBytes(123456789, ByteOrder.BIG_ENDIAN), bytes);
    }

    @Test
    public void testWriteData_integerArray() throws ImagingException {
        FieldTypeLong8 fieldTypeLong8 = new FieldTypeLong8(1, "Long8");
        int[] intValues = {123456789, 987654321};
        byte[] bytes = fieldTypeLong8.writeData(intValues, ByteOrder.BIG_ENDIAN);

        assertArrayEquals(ByteConversions.toBytes(intValues, ByteOrder.BIG_ENDIAN), bytes);
    }

    @Test
    public void testWriteData_integerObjectArray() throws ImagingException {
        FieldTypeLong8 fieldTypeLong8 = new FieldTypeLong8(1, "Long8");
        Integer[] intValues = {123456789, 987654321};
        byte[] bytes = fieldTypeLong8.writeData(intValues, ByteOrder.BIG_ENDIAN);

        int[] expectedValues = {123456789, 987654321};
        assertArrayEquals(ByteConversions.toBytes(expectedValues, ByteOrder.BIG_ENDIAN), bytes);
    }

    @Test
    public void testWriteData_invalidData() {
        FieldTypeLong8 fieldTypeLong8 = new FieldTypeLong8(1, "Long8");

        assertThrows(ImagingException.class, () -> {
            fieldTypeLong8.writeData("invalid data", ByteOrder.BIG_ENDIAN);
        });
    }
}