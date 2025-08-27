package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CryptLLM_Test {
    @Test
    public void testCryptWithNullSalt() {
        // Test crypt with null salt for byte array input
        final byte[] keyBytes = new byte[] { 't', 'e', 's', 't' };
        final String hash = Crypt.crypt(keyBytes, null);
        assertTrue(hash.startsWith("$6$") || hash.startsWith("$5$") || hash.startsWith("$1$") || hash.startsWith("$2a$"));
    }

    @Test
    public void testCryptWithNullSaltString() {
        // Test crypt with null salt for string input
        final String hash = Crypt.crypt("test", null);
        assertTrue(hash.startsWith("$6$") || hash.startsWith("$5$") || hash.startsWith("$1$") || hash.startsWith("$2a$"));
    }

    @Test
    public void testCryptWithSecureRandomSalt() {
        // Test crypt with SecureRandom generated salt
        final byte[] keyBytes = new byte[] { 's', 'e', 'c', 'u', 'r', 'e' };
        final String salt = "$6$" + new SecureRandom().nextInt();
        final String hash = Crypt.crypt(keyBytes, salt);
        assertTrue(hash.startsWith("$6$"));
    }

    @Test
    public void testCryptWithThreadLocalRandomSalt() {
        // Test crypt with ThreadLocalRandom generated salt
        final byte[] keyBytes = new byte[] { 'r', 'a', 'n', 'd', 'o', 'm' };
        final String salt = "$5$" + ThreadLocalRandom.current().nextInt();
        final String hash = Crypt.crypt(keyBytes, salt);
        assertTrue(hash.startsWith("$5$"));
    }
}