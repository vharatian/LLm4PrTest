package org.apache.commons.imaging.formats.tiff.taginfos;

import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TagInfoRationalLLM_Test {

    @Test
    public void testGetValue() {
        TagInfoRational tagInfoRational = new TagInfoRational("Test", 1, TiffDirectoryType.TIFF_DIRECTORY_UNKNOWN);
        byte[] bytes = ByteConversions.toBytes(new RationalNumber(1, 2), ByteOrder.BIG_ENDIAN);
        RationalNumber result = tagInfoRational.getValue(ByteOrder.BIG_ENDIAN, bytes);
        assertEquals(new RationalNumber(1, 2), result);
    }

    @Test
    public void testEncodeValue() {
        TagInfoRational tagInfoRational = new TagInfoRational("Test", 1, TiffDirectoryType.TIFF_DIRECTORY_UNKNOWN);
        RationalNumber rationalNumber = new RationalNumber(1, 2);
        byte[] result = tagInfoRational.encodeValue(ByteOrder.BIG_ENDIAN, rationalNumber);
        byte[] expected = ByteConversions.toBytes(rationalNumber, ByteOrder.BIG_ENDIAN);
        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }
}