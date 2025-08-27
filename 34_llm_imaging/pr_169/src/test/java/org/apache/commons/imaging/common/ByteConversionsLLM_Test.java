package org.apache.commons.imaging.common;

import org.junit.jupiter.api.Test;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class ByteConversionsLLM_Test {

    @Test
    public void testToRationalWithUnsignedType() {
        byte[] bytes = new byte[]{0, 0, 0, 1, 0, 0, 0, 2};
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        boolean unsignedType = true;

        RationalNumber result = ByteConversions.toRational(bytes, byteOrder, unsignedType);

        assertEquals(1, result.numerator);
        assertEquals(2, result.divisor);
        assertTrue(result.isUnsigned());
    }

    @Test
    public void testToRationalsWithUnsignedType() {
        byte[] bytes = new byte[]{
            0, 0, 0, 1, 0, 0, 0, 2,
            0, 0, 0, 3, 0, 0, 0, 4
        };
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        boolean unsignedType = true;

        RationalNumber[] result = ByteConversions.toRationals(bytes, byteOrder, unsignedType);

        assertEquals(2, result.length);
        assertEquals(1, result[0].numerator);
        assertEquals(2, result[0].divisor);
        assertTrue(result[0].isUnsigned());
        assertEquals(3, result[1].numerator);
        assertEquals(4, result[1].divisor);
        assertTrue(result[1].isUnsigned());
    }

    @Test
    public void testToRationalWithSignedType() {
        byte[] bytes = new byte[]{0, 0, 0, 1, 0, 0, 0, 2};
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        boolean unsignedType = false;

        RationalNumber result = ByteConversions.toRational(bytes, byteOrder, unsignedType);

        assertEquals(1, result.numerator);
        assertEquals(2, result.divisor);
        assertFalse(result.isUnsigned());
    }

    @Test
    public void testToRationalsWithSignedType() {
        byte[] bytes = new byte[]{
            0, 0, 0, 1, 0, 0, 0, 2,
            0, 0, 0, 3, 0, 0, 0, 4
        };
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        boolean unsignedType = false;

        RationalNumber[] result = ByteConversions.toRationals(bytes, byteOrder, unsignedType);

        assertEquals(2, result.length);
        assertEquals(1, result[0].numerator);
        assertEquals(2, result[0].divisor);
        assertFalse(result[0].isUnsigned());
        assertEquals(3, result[1].numerator);
        assertEquals(4, result[1].divisor);
        assertFalse(result[1].isUnsigned());
    }
}