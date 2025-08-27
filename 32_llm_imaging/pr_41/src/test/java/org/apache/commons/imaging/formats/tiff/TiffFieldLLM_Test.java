package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.fieldtypes.FieldType;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.*;

public class TiffFieldLLM_Test {

    @Test
    public void testGetOffset() {
        int tag = 1;
        int directoryType = 1;
        FieldType fieldType = FieldType.SHORT;
        long count = 1;
        long offset = 123456789L;
        byte[] value = new byte[]{0, 1, 2, 3};
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int sortHint = 0;

        TiffField tiffField = new TiffField(tag, directoryType, fieldType, count, offset, value, byteOrder, sortHint);
        assertEquals((int) offset, tiffField.getOffset());
    }
}