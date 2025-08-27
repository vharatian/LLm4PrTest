package org.apache.commons.codec.binary;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class HexLLM_Test {

    @Test
    public void testEncodeHexWithRenamedVariables() {
        byte[] data = {0x0A, 0x1B, 0x2C};
        char[] expected = {'0', 'a', '1', 'b', '2', 'c'};
        char[] result = Hex.encodeHex(data, true);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeHexWithRenamedVariablesUpperCase() {
        byte[] data = {0x0A, 0x1B, 0x2C};
        char[] expected = {'0', 'A', '1', 'B', '2', 'C'};
        char[] result = Hex.encodeHex(data, false);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeObjectWithFinalByteArray() throws EncoderException {
        Hex hex = new Hex();
        String input = "test";
        byte[] expected = Hex.encodeHex(input.getBytes(StandardCharsets.UTF_8));
        char[] result = (char[]) hex.encode(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeObjectWithFinalByteArrayByteBuffer() throws EncoderException {
        Hex hex = new Hex();
        ByteBuffer input = ByteBuffer.wrap("test".getBytes(StandardCharsets.UTF_8));
        byte[] expected = Hex.encodeHex(input.array());
        char[] result = (char[]) hex.encode(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeObjectWithFinalByteArrayInvalidType() {
        Hex hex = new Hex();
        try {
            hex.encode(new int[]{1, 2, 3});
            fail("Expected EncoderException");
        } catch (EncoderException e) {
            // Expected exception
        }
    }
}