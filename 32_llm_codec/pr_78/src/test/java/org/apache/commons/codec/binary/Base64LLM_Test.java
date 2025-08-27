package org.apache.commons.codec.binary;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BinaryCodec;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64LLM_Test {

    @Test
    public void testEncodeBase64WithNullInput() {
        assertNull(Base64.encodeBase64(null), "Base64.encodeBase64(null) should return null");
    }

    @Test
    public void testEncodeBase64WithEmptyInput() {
        byte[] empty = new byte[0];
        byte[] result = Base64.encodeBase64(empty);
        assertEquals(0, result.length, "Base64.encodeBase64(empty) should return an empty array");
    }

    @Test
    public void testEncodeBase64WithBinaryCodecIsEmpty() {
        byte[] empty = new byte[0];
        byte[] result = Base64.encodeBase64(empty, false, false, Integer.MAX_VALUE);
        assertEquals(0, result.length, "Base64.encodeBase64(empty, false, false, Integer.MAX_VALUE) should return an empty array");
    }

    @Test
    public void testEncodeBase64WithBinaryCodecIsEmptyNull() {
        byte[] result = Base64.encodeBase64(null, false, false, Integer.MAX_VALUE);
        assertNull(result, "Base64.encodeBase64(null, false, false, Integer.MAX_VALUE) should return null");
    }
}