package org.apache.commons.codec.digest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.SecureRandom;
import org.junit.jupiter.api.Test;

public class Md5CryptLLM_Test {

    @Test
    public void testMd5CryptWithSecureRandom() {
        final SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), secureRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithNullSalt() {
        final SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), null, "$1$", secureRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testApr1CryptWithSecureRandom() {
        final SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.apr1Crypt("secret".getBytes(), secureRandom).matches("^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testApr1CryptWithNullSalt() {
        final SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.apr1Crypt("secret".getBytes(), null, "$apr1$", secureRandom).matches("^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithInvalidSalt() {
        final SecureRandom secureRandom = new SecureRandom();
        assertThrows(IllegalArgumentException.class, () -> Md5Crypt.md5Crypt("secret".getBytes(), "invalid$salt", "$1$", secureRandom));
    }

    @Test
    public void testApr1CryptWithInvalidSalt() {
        final SecureRandom secureRandom = new SecureRandom();
        assertThrows(IllegalArgumentException.class, () -> Md5Crypt.apr1Crypt("secret".getBytes(), "invalid$salt", "$apr1$", secureRandom));
    }
}