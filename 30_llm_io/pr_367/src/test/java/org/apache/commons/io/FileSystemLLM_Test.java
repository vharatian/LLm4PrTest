package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class FileSystemLLM_Test {

    @Test
    public void testConstructorCaseSensitiveParameter() {
        // Test case-sensitive parameter
        FileSystem fs = FileSystem.LINUX;
        assertTrue(fs.isCaseSensitive(), "LINUX should be case-sensitive");

        fs = FileSystem.WINDOWS;
        assertFalse(fs.isCaseSensitive(), "WINDOWS should not be case-sensitive");
    }

    @Test
    public void testConstructorCasePreservingParameter() {
        // Test case-preserving parameter
        FileSystem fs = FileSystem.LINUX;
        assertTrue(fs.isCasePreserving(), "LINUX should be case-preserving");

        fs = FileSystem.WINDOWS;
        assertTrue(fs.isCasePreserving(), "WINDOWS should be case-preserving");
    }
}