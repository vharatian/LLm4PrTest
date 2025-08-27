package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class EndianUtilsLLM_Test {

    @Test
    public void testReadSwappedIntegerWithInvalidOffset() {
        final byte[] bytes = { 0x04, 0x03, 0x02 };
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.readSwappedInteger(bytes, 0));
    }

    @Test
    public void testReadSwappedLongWithInvalidOffset() {
        final byte[] bytes = { 0x08, 0x07, 0x06, 0x05, 0x04, 0x03, 0x02 };
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.readSwappedLong(bytes, 0));
    }

    @Test
    public void testReadSwappedShortWithInvalidOffset() {
        final byte[] bytes = { 0x02 };
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.readSwappedShort(bytes, 0));
    }

    @Test
    public void testReadSwappedUnsignedIntegerWithInvalidOffset() {
        final byte[] bytes = { 0x04, 0x03, 0x02 };
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.readSwappedUnsignedInteger(bytes, 0));
    }

    @Test
    public void testReadSwappedUnsignedShortWithInvalidOffset() {
        final byte[] bytes = { 0x02 };
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.readSwappedUnsignedShort(bytes, 0));
    }

    @Test
    public void testWriteSwappedIntegerWithInvalidOffset() {
        final byte[] bytes = new byte[3];
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.writeSwappedInteger(bytes, 0, 0x01020304));
    }

    @Test
    public void testWriteSwappedLongWithInvalidOffset() {
        final byte[] bytes = new byte[7];
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.writeSwappedLong(bytes, 0, 0x0102030405060708L));
    }

    @Test
    public void testWriteSwappedShortWithInvalidOffset() {
        final byte[] bytes = new byte[1];
        assertThrows(IllegalArgumentException.class, () -> EndianUtils.writeSwappedShort(bytes, 0, (short) 0x0102));
    }
}