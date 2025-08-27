package org.apache.commons.imaging.formats.tiff.taginfos;

import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.common.RationalNumber;
import org.apache.commons.imaging.formats.tiff.constants.TiffDirectoryType;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TagInfoRationalsLLM_Test {

    @Test
    public void testGetValue() {
        // Arrange
        TagInfoRationals tagInfoRationals = new TagInfoRationals("Test", 1, 1, TiffDirectoryType.TIFF_DIRECTORY_IFD0);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        byte[] bytes = new byte[]{0, 0, 0, 1, 0, 0, 0, 2}; // Represents RationalNumber(1, 2)

        // Act
        RationalNumber[] result = tagInfoRationals.getValue(byteOrder, bytes);

        // Assert
        RationalNumber[] expected = new RationalNumber[]{new RationalNumber(1, 2)};
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeValue() {
        // Arrange
        TagInfoRationals tagInfoRationals = new TagInfoRationals("Test", 1, 1, TiffDirectoryType.TIFF_DIRECTORY_IFD0);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        RationalNumber[] values = new RationalNumber[]{new RationalNumber(1, 2)};

        // Act
        byte[] result = tagInfoRationals.encodeValue(byteOrder, values);

        // Assert
        byte[] expected = new byte[]{0, 0, 0, 1, 0, 0, 0, 2}; // Represents RationalNumber(1, 2)
        assertArrayEquals(expected, result);
    }
}