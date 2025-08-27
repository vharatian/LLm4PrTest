package org.apache.commons.imaging.formats.tiff.taginfos;

import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagInfoSRationalLLM_Test {

    @Test
    public void testGetValue() {
        TagInfoSRational tagInfo = new TagInfoSRational("Test", 1, TiffDirectoryType.TIFF_DIRECTORY_UNKNOWN);
        byte[] bytes = ByteConversions.toBytes(new RationalNumber(1, 2), ByteOrder.BIG_ENDIAN);
        RationalNumber result = tagInfo.getValue(ByteOrder.BIG_ENDIAN, bytes);
        assertEquals(new RationalNumber(1, 2), result);
    }

    @Test
    public void testEncodeValue() {
        TagInfoSRational tagInfo = new TagInfoSRational("Test", 1, TiffDirectoryType.TIFF_DIRECTORY_UNKNOWN);
        RationalNumber value = new RationalNumber(1, 2);
        byte[] expectedBytes = ByteConversions.toBytes(value, ByteOrder.BIG_ENDIAN);
        byte[] resultBytes = tagInfo.encodeValue(ByteOrder.BIG_ENDIAN, value);
        assertArrayEquals(expectedBytes, resultBytes);
    }
}