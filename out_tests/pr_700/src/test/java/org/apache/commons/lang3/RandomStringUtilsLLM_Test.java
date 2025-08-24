package org.apache.commons.lang3;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class RandomStringUtilsLLM_Test {

    @Test
    public void testRandomStringUtilsFinalCodePoint() {
        // Test to ensure the final keyword in codePoint variable does not affect functionality
        String r1 = RandomStringUtils.random(50);
        assertEquals(50, r1.length(), "random(50) length");
        String r2 = RandomStringUtils.random(50);
        assertEquals(50, r2.length(), "random(50) length");
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.randomAscii(50);
        assertEquals(50, r1.length(), "randomAscii(50) length");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(r1.charAt(i) >= 32 && r1.charAt(i) <= 127, "char between 32 and 127");
        }
        r2 = RandomStringUtils.randomAscii(50);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.randomAlphabetic(50);
        assertEquals(50, r1.length(), "randomAlphabetic(50)");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(Character.isLetter(r1.charAt(i)) && !Character.isDigit(r1.charAt(i)), "r1 contains alphabetic");
        }
        r2 = RandomStringUtils.randomAlphabetic(50);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.randomAlphanumeric(50);
        assertEquals(50, r1.length(), "randomAlphanumeric(50)");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(Character.isLetterOrDigit(r1.charAt(i)), "r1 contains alphanumeric");
        }
        r2 = RandomStringUtils.randomAlphabetic(50);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.randomGraph(50);
        assertEquals(50, r1.length(), "randomGraph(50) length");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(r1.charAt(i) >= 33 && r1.charAt(i) <= 126, "char between 33 and 126");
        }
        r2 = RandomStringUtils.randomGraph(50);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.randomNumeric(50);
        assertEquals(50, r1.length(), "randomNumeric(50)");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(Character.isDigit(r1.charAt(i)) && !Character.isLetter(r1.charAt(i)), "r1 contains numeric");
        }
        r2 = RandomStringUtils.randomNumeric(50);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.randomPrint(50);
        assertEquals(50, r1.length(), "randomPrint(50) length");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(r1.charAt(i) >= 32 && r1.charAt(i) <= 126, "char between 32 and 126");
        }
        r2 = RandomStringUtils.randomPrint(50);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        String set = "abcdefg";
        r1 = RandomStringUtils.random(50, set);
        assertEquals(50, r1.length(), "random(50, \"abcdefg\")");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(set.indexOf(r1.charAt(i)) > -1, "random char in set");
        }
        r2 = RandomStringUtils.random(50, set);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.random(50, (String) null);
        assertEquals(50, r1.length(), "random(50) length");
        r2 = RandomStringUtils.random(50, (String) null);
        assertEquals(50, r2.length(), "random(50) length");
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        set = "stuvwxyz";
        r1 = RandomStringUtils.random(50, set.toCharArray());
        assertEquals(50, r1.length(), "random(50, \"stuvwxyz\")");
        for (int i = 0; i < r1.length(); i++) {
            assertTrue(set.indexOf(r1.charAt(i)) > -1, "random char in set");
        }
        r2 = RandomStringUtils.random(50, set);
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        r1 = RandomStringUtils.random(50, (char[]) null);
        assertEquals(50, r1.length(), "random(50) length");
        r2 = RandomStringUtils.random(50, (char[]) null);
        assertEquals(50, r2.length(), "random(50) length");
        assertTrue(!r1.equals(r2), "!r1.equals(r2)");

        final long seedMillis = System.currentTimeMillis();
        r1 = RandomStringUtils.random(50, 0, 0, true, true, null, new Random(seedMillis));
        r2 = RandomStringUtils.random(50, 0, 0, true, true, null, new Random(seedMillis));
        assertEquals(r1, r2, "r1.equals(r2)");

        r1 = RandomStringUtils.random(0);
        assertEquals("", r1, "random(0).equals(\"\")");
    }
}