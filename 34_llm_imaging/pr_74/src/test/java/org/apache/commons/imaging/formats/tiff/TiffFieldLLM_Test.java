package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TiffFieldLLM_Test {

    @Test
    public void testGetValueDescriptionWithObjectArray() throws ImageReadException {
        TagInfo tagInfo = new TagInfo("TestTag", 1, FieldType.ASCII, 1, null);
        TiffField tiffField = new TiffField(1, 1, FieldType.ASCII, 1, 0, new byte[0], ByteOrder.BIG_ENDIAN, 0);
        Object[] values = new Object[55];
        for (int i = 0; i < 55; i++) {
            values[i] = i;
        }
        String description = tiffField.getValueDescription(values);
        assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50... (55)", description);
    }

    @Test
    public void testGetValueDescriptionWithShortArray() throws ImageReadException {
        TagInfo tagInfo = new TagInfo("TestTag", 1, FieldType.SHORT, 1, null);
        TiffField tiffField = new TiffField(1, 1, FieldType.SHORT, 1, 0, new byte[0], ByteOrder.BIG_ENDIAN, 0);
        short[] values = new short[55];
        for (short i = 0; i < 55; i++) {
            values[i] = i;
        }
        String description = tiffField.getValueDescription(values);
        assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50... (55)", description);
    }

    @Test
    public void testToString() throws ImageReadException {
        TagInfo tagInfo = new TagInfo("TestTag", 1, FieldType.ASCII, 1, null);
        TiffField tiffField = new TiffField(1, 1, FieldType.ASCII, 1, 0, new byte[0], ByteOrder.BIG_ENDIAN, 0);
        String expected = "1 (0x1: TestTag): null (1 ASCII)";
        assertEquals(expected, tiffField.toString());
    }
}