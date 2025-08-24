package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.apache.commons.lang3.JavaVersion.JAVA_14;
import static org.apache.commons.lang3.JavaVersion.JAVA_15;
import static org.apache.commons.lang3.JavaVersion.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaVersionLLM_Test {

    @Test
    public void testGetJavaVersionForNewVersions() {
        // Test for Java 14
        assertEquals(JAVA_14, get("14"), "14 failed");
        
        // Test for Java 15
        assertEquals(JAVA_15, get("15"), "15 failed");
    }
}