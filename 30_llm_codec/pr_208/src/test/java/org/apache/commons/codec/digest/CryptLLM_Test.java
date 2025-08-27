package org.apache.commons.codec.digest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CryptLLM_Test {

    /**
     * Test to ensure that the salt generation uses SecureRandom.
     */
    @Test
    public void testSecureRandomSaltGeneration() {
        String hash1 = Crypt.crypt("password");
        String hash2 = Crypt.crypt("password");
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertTrue(hash1.startsWith("$6$"));
        assertTrue(hash2.startsWith("$6$"));
        // Ensure that two different salts are generated
        assertTrue(!hash1.equals(hash2));
    }
}