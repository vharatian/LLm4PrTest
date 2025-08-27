package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.apache.commons.codec.Charsets;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;
import java.security.SecureRandom;
import java.util.Random;

public class Md5CryptLLM_Test {

    @Test
    public void testMd5CryptWithSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), secureRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithRandom() {
        Random random = new Random();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), random).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMd5CryptWithInvalidSalt() {
        Md5Crypt.md5Crypt("secret".getBytes(), "$1$invalid_salt_value");
    }
}