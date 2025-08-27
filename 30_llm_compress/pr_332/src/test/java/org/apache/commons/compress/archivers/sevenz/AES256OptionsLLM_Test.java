package org.apache.commons.compress.archivers.sevenz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

public class AES256OptionsLLM_Test {

    @Test
    public void testConstructorWithPasswordOnly() {
        char[] password = "password".toCharArray();
        AES256Options options = new AES256Options(password);

        assertNotNull(options.getIv());
        assertNotNull(options.getSalt());
        assertEquals(19, options.getNumCyclesPower());
        assertNotNull(options.getCipher());
    }

    @Test
    public void testConstructorWithAllParameters() {
        char[] password = "password".toCharArray();
        byte[] salt = new byte[16];
        byte[] iv = new byte[16];
        int numCyclesPower = 19;

        AES256Options options = new AES256Options(password, salt, iv, numCyclesPower);

        assertArrayEquals(salt, options.getSalt());
        assertArrayEquals(iv, options.getIv());
        assertEquals(numCyclesPower, options.getNumCyclesPower());
        assertNotNull(options.getCipher());
    }

    @Test
    public void testCipherInitialization() {
        char[] password = "password".toCharArray();
        AES256Options options = new AES256Options(password);

        Cipher cipher = options.getCipher();
        assertNotNull(cipher);
        assertEquals("AES/CBC/NoPadding", cipher.getAlgorithm());
    }

    @Test
    public void testRandomBytes() {
        byte[] bytes = AES256Options.randomBytes(16);
        assertNotNull(bytes);
        assertEquals(16, bytes.length);
    }

    @Test
    public void testExceptionInCipherInitialization() {
        char[] password = "password".toCharArray();
        byte[] salt = new byte[16];
        byte[] iv = new byte[16];
        int numCyclesPower = 19;

        try {
            AES256Options options = new AES256Options(password, salt, iv, numCyclesPower);
            assertNotNull(options.getCipher());
        } catch (IllegalStateException e) {
            assertTrue(e.getCause() instanceof GeneralSecurityException);
        }
    }

    @Test
    public void testExceptionInRandomBytes() {
        try {
            AES256Options.randomBytes(-1);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertTrue(e.getCause() instanceof NoSuchAlgorithmException);
        }
    }
}