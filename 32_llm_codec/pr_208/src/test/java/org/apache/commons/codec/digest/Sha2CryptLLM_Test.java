package org.apache.commons.codec.digest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;
import java.util.Random;

public class Sha2CryptLLM_Test {

    @Test
    public void testSha256CryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha256Crypt(keyBytes, null);
        assertNotNull(result);
    }

    @Test
    public void testSha256CryptWithCustomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String salt = Sha2Crypt.SHA256_PREFIX + "customSalt";
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);
        assertNotNull(result);
    }

    @Test
    public void testSha256CryptWithRandomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        Random random = new SecureRandom();
        String result = Sha2Crypt.sha256Crypt(keyBytes, null, random);
        assertNotNull(result);
    }

    @Test
    public void testSha512CryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha512Crypt(keyBytes, null);
        assertNotNull(result);
    }

    @Test
    public void testSha512CryptWithCustomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String salt = Sha2Crypt.SHA512_PREFIX + "customSalt";
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt);
        assertNotNull(result);
    }

    @Test
    public void testSha512CryptWithRandomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        Random random = new SecureRandom();
        String result = Sha2Crypt.sha512Crypt(keyBytes, null, random);
        assertNotNull(result);
    }

    @Test
    public void testSha256CryptWithInvalidSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String invalidSalt = "invalidSalt";
        assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha256Crypt(keyBytes, invalidSalt);
        });
    }

    @Test
    public void testSha512CryptWithInvalidSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String invalidSalt = "invalidSalt";
        assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha512Crypt(keyBytes, invalidSalt);
        });
    }
}