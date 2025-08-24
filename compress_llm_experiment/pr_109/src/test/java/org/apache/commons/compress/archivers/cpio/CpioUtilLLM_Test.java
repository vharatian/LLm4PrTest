package org.apache.commons.compress.archivers.cpio;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CpioUtilLLM_Test {

    @Test
    public void testByteArray2longWithEvenLength() {
        byte[] byteArray = new byte[] { 0x01, 0x02, 0x03, 0x04 };
        long expected = 0x01020304L;
        assertEquals(expected, CpioUtil.byteArray2long(byteArray, false));
    }

    @Test
    public void testByteArray2longWithEvenLengthSwapped() {
        byte[] byteArray = new byte[] { 0x02, 0x01, 0x04, 0x03 };
        long expected = 0x01020304L;
        assertEquals(expected, CpioUtil.byteArray2long(byteArray, true));
    }

    @Test
    public void testLong2byteArrayWithEvenLength() {
        long number = 0x01020304L;
        byte[] expected = new byte[] { 0x01, 0x02, 0x03, 0x04 };
        assertArrayEquals(expected, CpioUtil.long2byteArray(number, 4, false));
    }

    @Test
    public void testLong2byteArrayWithEvenLengthSwapped() {
        long number = 0x01020304L;
        byte[] expected = new byte[] { 0x02, 0x01, 0x04, 0x03 };
        assertArrayEquals(expected, CpioUtil.long2byteArray(number, 4, true));
    }
}