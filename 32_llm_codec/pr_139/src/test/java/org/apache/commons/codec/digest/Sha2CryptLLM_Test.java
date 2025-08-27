package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Sha2CryptLLM_Test {

    @Test
    public void testSha256CryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha256Crypt(keyBytes, null);
        assertNotNull(result);
    }

    @Test
    public void testSha512CryptWithNullSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha512Crypt(keyBytes, null);
        assertNotNull(result);
    }

    @Test
    public void testSha256CryptWithCustomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String salt = "$5$customSalt";
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);
        assertNotNull(result);
    }

    @Test
    public void testSha512CryptWithCustomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String salt = "$6$customSalt";
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt);
        assertNotNull(result);
    }

    @Test
    public void testSha256CryptWithRandomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha256Crypt(keyBytes, null, new SecureRandom());
        assertNotNull(result);
    }

    @Test
    public void testSha512CryptWithRandomSalt() {
        byte[] keyBytes = "testKey".getBytes();
        String result = Sha2Crypt.sha512Crypt(keyBytes, null, new SecureRandom());
        assertNotNull(result);
    }
}