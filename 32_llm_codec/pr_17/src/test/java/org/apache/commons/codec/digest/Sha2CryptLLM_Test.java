package org.apache.commons.codec.digest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;

public class Sha2CryptLLM_Test {

    @Test
    public void testSha256CryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha256Crypt(keyBytes, null);
        assertNotNull(result);
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX));
    }

    @Test
    public void testSha256CryptWithCustomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String salt = "customSalt";
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.contains(salt));
    }

    @Test
    public void testSha256CryptWithRandomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        Random random = new SecureRandom();
        String result = Sha2Crypt.sha256Crypt(keyBytes, null, random);
        assertNotNull(result);
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX));
    }

    @Test
    public void testSha512CryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha512Crypt(keyBytes, null);
        assertNotNull(result);
        assertTrue(result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    @Test
    public void testSha512CryptWithCustomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String salt = "customSalt";
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.contains(salt));
    }

    @Test
    public void testSha512CryptWithRandomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        Random random = new SecureRandom();
        String result = Sha2Crypt.sha512Crypt(keyBytes, null, random);
        assertNotNull(result);
        assertTrue(result.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    @Test
    public void testInvalidSaltPattern() {
        byte[] keyBytes = "testKey".getBytes();
        String invalidSalt = "$invalid$salt";
        try {
            Sha2Crypt.sha256Crypt(keyBytes, invalidSalt);
            fail("Expected IllegalArgumentException for invalid salt pattern");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}