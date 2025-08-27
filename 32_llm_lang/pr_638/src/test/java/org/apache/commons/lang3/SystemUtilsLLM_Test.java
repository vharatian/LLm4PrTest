package org.apache.commons.lang3;

import static org.apache.commons.lang3.JavaVersion.JAVA_10;
import static org.apache.commons.lang3.JavaVersion.JAVA_11;
import static org.apache.commons.lang3.JavaVersion.JAVA_12;
import static org.apache.commons.lang3.JavaVersion.JAVA_13;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_1;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_2;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_3;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_4;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_5;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_6;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_7;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_8;
import static org.apache.commons.lang3.JavaVersion.JAVA_9;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SystemUtilsLLM_Test {

    /**
     * Test for the isJavaVersionAtMost method to ensure it returns true for versions less than or equal to the required version.
     */
    @Test
    public void testIsJavaVersionAtMost() {
        if (SystemUtils.IS_JAVA_1_8) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_9));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_10));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
        } else if (SystemUtils.IS_JAVA_9) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_9));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_10));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
        } else if (SystemUtils.IS_JAVA_10) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_10));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
        } else if (SystemUtils.IS_JAVA_11) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_10));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_11));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
        } else if (SystemUtils.IS_JAVA_12) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_10));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_11));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_12));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
        } else if (SystemUtils.IS_JAVA_13) {
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_1));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_2));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_3));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_4));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_5));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_6));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_7));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_1_8));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_9));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_10));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_11));
            assertFalse(SystemUtils.isJavaVersionAtMost(JAVA_12));
            assertTrue(SystemUtils.isJavaVersionAtMost(JAVA_13));
        }
    }
}