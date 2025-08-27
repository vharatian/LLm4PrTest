package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

public class UnixCryptLLM_Test {

    @Test
    public void testCryptWithOriginalLength() {
        // Test with a byte array of length greater than 8
        byte[] original = "longpassword".getBytes(StandardCharsets.UTF_8);
        String salt = "xx";
        String result = UnixCrypt.crypt(original, salt);
        assertTrue(result.matches("^[a-zA-Z0-9./]{13}$"));

        // Test with a byte array of length less than 8
        original = "short".getBytes(StandardCharsets.UTF_8);
        result = UnixCrypt.crypt(original, salt);
        assertTrue(result.matches("^[a-zA-Z0-9./]{13}$"));
    }

    @Test
    public void testCryptWithEmptyOriginal() {
        // Test with an empty byte array
        byte[] original = new byte[0];
        String salt = "xx";
        String result = UnixCrypt.crypt(original, salt);
        assertTrue(result.matches("^[a-zA-Z0-9./]{13}$"));
    }

    @Test
    public void testCryptWithNullSalt() {
        // Test with null salt
        byte[] original = "password".getBytes(StandardCharsets.UTF_8);
        String result = UnixCrypt.crypt(original, null);
        assertTrue(result.matches("^[a-zA-Z0-9./]{13}$"));
    }
}