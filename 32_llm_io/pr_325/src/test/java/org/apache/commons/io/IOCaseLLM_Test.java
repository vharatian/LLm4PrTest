package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class IOCaseLLM_Test {

    @Test
    public void test_isCaseSensitive() {
        // Test for SENSITIVE case
        assertTrue(IOCase.isCaseSensitive(IOCase.SENSITIVE));

        // Test for INSENSITIVE case
        assertFalse(IOCase.isCaseSensitive(IOCase.INSENSITIVE));

        // Test for SYSTEM case
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
        assertTrue(IOCase.isCaseSensitive(IOCase.SYSTEM) != isWindows);

        // Test for null input
        assertFalse(IOCase.isCaseSensitive(null));
    }
}