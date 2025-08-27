package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.apache.commons.codec.Charsets;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;

public class Md5CryptLLM_Test {

    @Test
    public void testMd5CryptWithSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), secureRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithNullSaltAndSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), null, "$1$", secureRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithNullSaltAndThreadLocalRandom() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), null, "$1$", threadLocalRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithExplicitSaltAndSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        assertEquals("$1$1234$ImZYBLmYC.rbBKg9ERxX70", Md5Crypt.md5Crypt("secret".getBytes(), "1234", "$1$", secureRandom));
    }

    @Test
    public void testMd5CryptWithExplicitSaltAndThreadLocalRandom() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        assertEquals("$1$1234$ImZYBLmYC.rbBKg9ERxX70", Md5Crypt.md5Crypt("secret".getBytes(), "1234", "$1$", threadLocalRandom));
    }
}