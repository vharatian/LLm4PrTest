package org.apache.commons.imaging.formats.tiff.taginfos;

import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TagInfoSRationalsLLM_Test {

    @Test
    public void testGetValue() {
        TagInfoSRationals tagInfo = new TagInfoSRationals("Test", 1, 1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        byte[] bytes = ByteConversions.toBytes(new RationalNumber[]{new RationalNumber(1, 2)}, ByteOrder.BIG_ENDIAN);
        RationalNumber[] expected = new RationalNumber[]{new RationalNumber(1, 2)};
        RationalNumber[] actual = tagInfo.getValue(ByteOrder.BIG_ENDIAN, bytes);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEncodeValue() {
        TagInfoSRationals tagInfo = new TagInfoSRationals("Test", 1, 1, TiffDirectoryType.EXIF_DIRECTORY_UNKNOWN);
        RationalNumber[] rationals = new RationalNumber[]{new RationalNumber(1, 2)};
        byte[] expected = ByteConversions.toBytes(rationals, ByteOrder.BIG_ENDIAN);
        byte[] actual = tagInfo.encodeValue(ByteOrder.BIG_ENDIAN, rationals);
        assertArrayEquals(expected, actual);
    }
}