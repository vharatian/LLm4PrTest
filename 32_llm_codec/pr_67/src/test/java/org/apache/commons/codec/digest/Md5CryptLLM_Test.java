package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class Md5CryptLLM_Test {

    @Test
    public void testMd5CryptWithNullSalt() {
        // Test case to ensure md5Crypt handles null salt correctly
        String result = Md5Crypt.md5Crypt("secret".getBytes(), (String) null);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptWithValidSalt() {
        // Test case to ensure md5Crypt handles valid salt correctly
        String result = Md5Crypt.md5Crypt("secret".getBytes(), "$1$validSalt");
        assertTrue(result.matches("^\\$1\\$validSalt\\$.{1,}$"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMd5CryptWithInvalidSalt() {
        // Test case to ensure md5Crypt throws IllegalArgumentException for invalid salt
        Md5Crypt.md5Crypt("secret".getBytes(), "$1$invalid_salt_value");
    }

    @Test
    public void testMd5CryptWithRandomSalt() {
        // Test case to ensure md5Crypt handles random salt correctly
        final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        String result = Md5Crypt.md5Crypt("secret".getBytes(), threadLocalRandom);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }
}