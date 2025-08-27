package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.apache.commons.lang3.JavaVersion.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JavaVersionLLM_Test {

    @Test
    public void testGetJavaVersionWithNull() {
        // Test for the change where null input should return null
        assertNull(get(null), "null input should return null");
    }

    @Test
    public void testGetJavaVersionWithInvalidVersion() {
        // Test for an invalid version string that should return null
        assertNull(get("invalid"), "invalid version string should return null");
    }
}