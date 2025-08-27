package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.*;

public class Md5CryptLLM_Test {

    @Test
    public void testMd5CryptWithMinFunctionChange() {
        byte[] keyBytes = "testKey".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$testSalt";
        String result = Md5Crypt.md5Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.startsWith("$1$testSalt$"));
    }

    @Test
    public void testMd5CryptWithMinFunctionChangeLongKey() {
        byte[] keyBytes = new byte[32];
        for (int i = 0; i < 32; i++) {
            keyBytes[i] = (byte) i;
        }
        String salt = "$1$testSalt";
        String result = Md5Crypt.md5Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.startsWith("$1$testSalt$"));
    }

    @Test
    public void testMd5CryptWithMinFunctionChangeShortKey() {
        byte[] keyBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            keyBytes[i] = (byte) i;
        }
        String salt = "$1$testSalt";
        String result = Md5Crypt.md5Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.startsWith("$1$testSalt$"));
    }
}