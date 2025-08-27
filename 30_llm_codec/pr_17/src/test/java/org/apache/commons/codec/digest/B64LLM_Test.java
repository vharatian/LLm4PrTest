package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import java.security.SecureRandom;
import java.util.Random;

public class B64LLM_Test {

    @Test
    public void testGetRandomSaltWithDefaultRandom() {
        String salt = B64.getRandomSalt(8);
        assertNotNull(salt);
        assertEquals(8, salt.length());
    }

    @Test
    public void testGetRandomSaltWithCustomRandom() {
        Random customRandom = new SecureRandom();
        String salt = B64.getRandomSalt(10, customRandom);
        assertNotNull(salt);
        assertEquals(10, salt.length());
    }
}