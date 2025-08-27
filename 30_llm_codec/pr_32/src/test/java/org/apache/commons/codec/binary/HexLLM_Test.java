package org.apache.commons.codec.binary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

public class HexLLM_Test {

    private ByteBuffer getByteBufferUtf8(String string) {
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        final ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        return bb;
    }

    @Test
    public void testEncodeHexByteBufferRemaining() {
        final ByteBuffer bb = getByteBufferUtf8("Hello World");
        final String expected = "48656c6c6f20576f726c64";
        char[] actual;

        actual = Hex.encodeHex(bb);
        assertEquals(expected, new String(actual));
        assertEquals(0, bb.remaining());

        bb.flip();
        actual = Hex.encodeHex(bb, true);
        assertEquals(expected, new String(actual));
        assertEquals(0, bb.remaining());

        bb.flip();
        actual = Hex.encodeHex(bb, false);
        assertEquals(expected.toUpperCase(), new String(actual));
        assertEquals(0, bb.remaining());
    }

    @Test
    public void testEncodeHexStringByteBufferRemaining() {
        final ByteBuffer bb = getByteBufferUtf8("Hello World");
        final String expected = "48656c6c6f20576f726c64";

        String actual = Hex.encodeHexString(bb);
        assertEquals(expected, actual);
        assertEquals(0, bb.remaining());

        bb.flip();
        actual = Hex.encodeHexString(bb, true);
        assertEquals(expected, actual);
        assertEquals(0, bb.remaining());

        bb.flip();
        actual = Hex.encodeHexString(bb, false);
        assertEquals(expected.toUpperCase(), actual);
        assertEquals(0, bb.remaining());
    }

    @Test
    public void testDecodeByteBufferRemaining() throws DecoderException {
        final ByteBuffer bb = getByteBufferUtf8("48656c6c6f20576f726c64");
        final byte[] expected = "Hello World".getBytes(StandardCharsets.UTF_8);

        byte[] actual = new Hex().decode(bb);
        assertTrue(Arrays.equals(expected, actual));
        assertEquals(0, bb.remaining());

        bb.flip();
        actual = new Hex().decode(bb);
        assertTrue(Arrays.equals(expected, actual));
        assertEquals(0, bb.remaining());
    }

    @Test
    public void testToByteArray() {
        final ByteBuffer bb = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            bb.put((byte) i);
        }
        bb.flip();
        byte[] byteArray = Hex.toByteArray(bb);
        assertEquals(0, bb.remaining());
        assertTrue(Arrays.equals(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, byteArray));
    }

    @Test
    public void testToByteArrayWithArray() {
        final ByteBuffer bb = ByteBuffer.allocate(10);
        for (int i = 0; i < 10; i++) {
            bb.put((byte) i);
        }
        bb.flip();
        byte[] byteArray = Hex.toByteArray(bb);
        assertEquals(0, bb.remaining());
        assertTrue(Arrays.equals(new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, byteArray));
    }

    @Test
    public void testEncodeHexByteBufferWithLimit() {
        final ByteBuffer bb = ByteBuffer.allocate(16);
        for (int i = 0; i < 16; i++) {
            bb.put((byte) i);
        }
        bb.flip();
        final String expected = "000102030405060708090a0b0c0d0e0f";
        for (int i = 0; i < 15; i++) {
            bb.position(i);
            bb.limit(i + 2);
            assertEquals(expected.substring(i * 2, i * 2 + 4), new String(Hex.encodeHex(bb)));
            assertEquals(0, bb.remaining());
        }
    }

    @Test
    public void testEncodeHexByteString_ByteBufferWithLimit() {
        final ByteBuffer bb = ByteBuffer.allocate(36);
        bb.limit(3);
        assertEquals("000000", Hex.encodeHexString(bb));
        assertEquals(0, bb.remaining());
        bb.position(1);
        bb.limit(3);
        assertEquals("0000", Hex.encodeHexString(bb));
        assertEquals(0, bb.remaining());
    }

    @Test
    public void testDecodeByteBufferWithLimit() throws DecoderException {
        final ByteBuffer bb = getByteBufferUtf8("000102030405060708090a0b0c0d0e0f");
        final byte[] expected = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        for (int i = 0; i < 15; i++) {
            bb.position(i * 2);
            bb.limit(i * 2 + 4);
            assertEquals(new String(Arrays.copyOfRange(expected, i, i + 2)), new String(new Hex().decode(bb)));
            assertEquals(0, bb.remaining());
        }
    }
}