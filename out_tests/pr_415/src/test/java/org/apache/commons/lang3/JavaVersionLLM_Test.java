package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.apache.commons.lang3.JavaVersion.JAVA_12;
import static org.apache.commons.lang3.JavaVersion.JAVA_13;
import static org.apache.commons.lang3.JavaVersion.JAVA_RECENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.apache.commons.lang3.JavaVersion.get;

public class JavaVersionLLM_Test {

    @Test
    public void testGetJavaVersion() {
        assertEquals(JAVA_12, get("12"), "12 failed");
        assertEquals(JAVA_13, get("13"), "13 failed");
    }

    @Test
    public void testRecentJavaVersion() {
        assertEquals(JAVA_RECENT, get("14"), "14 failed");
        assertEquals(JAVA_RECENT, get("15"), "15 failed");
    }
}