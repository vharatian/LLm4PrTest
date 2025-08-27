package org.apache.commons.codec.digest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;

public class UnixCryptLLM_Test {

    @Test
    public void testUnixCryptWithSecureRandomSalt() {
        // Test crypt with SecureRandom generated salt
        String hash = UnixCrypt.crypt("test".getBytes());
        assertTrue(hash.matches("^[a-zA-Z0-9./]{13}$"));
    }

    @Test
    public void testUnixCryptWithSecureRandomSaltConsistency() {
        // Ensure different hashes are generated for the same input when salt is null
        String hash1 = UnixCrypt.crypt("test".getBytes(), null);
        String hash2 = UnixCrypt.crypt("test".getBytes(), null);
        assertNotSame(hash1, hash2);
    }

    @Test
    public void testUnixCryptWithInvalidSaltPattern() {
        // Test crypt with invalid salt pattern
        assertThrows(IllegalArgumentException.class, () -> UnixCrypt.crypt("test".getBytes(), "!!"));
    }

    @Test
    public void testUnixCryptWithSecureRandomSaltGeneration() {
        // Ensure SecureRandom is used for salt generation
        SecureRandom randomGenerator = new SecureRandom();
        int numSaltChars = UnixCrypt.SALT_CHARS.length;
        String salt = "" + UnixCrypt.SALT_CHARS[randomGenerator.nextInt(numSaltChars)] +
                UnixCrypt.SALT_CHARS[randomGenerator.nextInt(numSaltChars)];
        String hash = UnixCrypt.crypt("test".getBytes(), salt);
        assertTrue(hash.matches("^[a-zA-Z0-9./]{13}$"));
    }
}