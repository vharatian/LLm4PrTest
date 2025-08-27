package org.apache.commons.imaging.formats.tiff;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TiffRasterDataTypeLLM_Test {

    @Test
    public void testEnumValues() {
        TiffRasterDataType[] expectedValues = {TiffRasterDataType.INTEGER, TiffRasterDataType.FLOAT};
        assertArrayEquals(expectedValues, TiffRasterDataType.values());
    }

    @Test
    public void testValueOfInteger() {
        assertEquals(TiffRasterDataType.INTEGER, TiffRasterDataType.valueOf("INTEGER"));
    }

    @Test
    public void testValueOfFloat() {
        assertEquals(TiffRasterDataType.FLOAT, TiffRasterDataType.valueOf("FLOAT"));
    }
}