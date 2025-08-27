package org.apache.commons.imaging.common;

import org.junit.jupiter.api.Test;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class ByteConversionsLLM_Test {

    @Test
    public void testToBytesLongBigEndian() {
        long value = 0x0123456789ABCDEFL;
        byte[] expected = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
        byte[] result = ByteConversions.toBytes(value, ByteOrder.BIG_ENDIAN);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testToBytesLongLittleEndian() {
        long value = 0x0123456789ABCDEFL;
        byte[] expected = {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89, 0x67, 0x45, 0x23, 0x01};
        byte[] result = ByteConversions.toBytes(value, ByteOrder.LITTLE_ENDIAN);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testToLongBigEndian() {
        byte[] bytes = {0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};
        long expected = 0x0123456789ABCDEFL;
        long result = ByteConversions.toLong(bytes, ByteOrder.BIG_ENDIAN);
        assertEquals(expected, result);
    }

    @Test
    public void testToLongLittleEndian() {
        byte[] bytes = {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89, 0x67, 0x45, 0x23, 0x01};
        long expected = 0x0123456789ABCDEFL;
        long result = ByteConversions.toLong(bytes, ByteOrder.LITTLE_ENDIAN);
        assertEquals(expected, result);
    }

    @Test
    public void testToLongsBigEndian() {
        byte[] bytes = {
            0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF,
            0x10, 0x32, 0x54, 0x76, (byte) 0x98, (byte) 0xBA, (byte) 0xDC, (byte) 0xFE
        };
        long[] expected = {0x0123456789ABCDEFL, 0x1032547698BADCFE};
        long[] result = ByteConversions.toLongs(bytes, ByteOrder.BIG_ENDIAN);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testToLongsLittleEndian() {
        byte[] bytes = {
            (byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89, 0x67, 0x45, 0x23, 0x01,
            (byte) 0xFE, (byte) 0xDC, (byte) 0xBA, (byte) 0x98, 0x76, 0x54, 0x32, 0x10
        };
        long[] expected = {0x0123456789ABCDEFL, 0x1032547698BADCFE};
        long[] result = ByteConversions.toLongs(bytes, ByteOrder.LITTLE_ENDIAN);
        assertArrayEquals(expected, result);
    }
}