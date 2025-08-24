package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

public class CharUtilsLLM_Test {

    @Test
    public void testToString_char() {
        // Test for characters within the CHAR_STRING_ARRAY range
        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            assertSame(str, str2);
            assertEquals(1, str.length());
            assertEquals(i, str.charAt(0));
        }

        // Test for characters outside the CHAR_STRING_ARRAY range
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            assertEquals(str, str2);
            assertEquals(1, str.length());
            assertEquals(i, str.charAt(0));
            assertEquals(1, str2.length());
            assertEquals(i, str2.charAt(0));
        }
    }
}