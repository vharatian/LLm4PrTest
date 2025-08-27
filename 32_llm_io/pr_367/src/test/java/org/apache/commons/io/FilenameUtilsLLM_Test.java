package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FilenameUtilsLLM_Test {

    @Test
    public void testNormalizeUnixSeparatorJavadoc() {
        // Test the javadoc change for normalize method with Unix separator
        assertEquals("/a/b/c.txt", FilenameUtils.normalize("a\\b/c.txt", true));
        assertEquals("/a/b/c.txt", FilenameUtils.normalize("\\a\\b/c.txt", true));
    }

    @Test
    public void testNormalizeWindowsSeparatorJavadoc() {
        // Test the javadoc change for normalize method with Windows separator
        assertEquals("\\a\\b\\c.txt", FilenameUtils.normalize("a\\b/c.txt", false));
        assertEquals("\\a\\b\\c.txt", FilenameUtils.normalize("\\a\\b/c.txt", false));
    }

    @Test
    public void testNormalizeNoEndSeparatorUnixSeparatorJavadoc() {
        // Test the javadoc change for normalizeNoEndSeparator method with Unix separator
        assertEquals("/a/b/c.txt", FilenameUtils.normalizeNoEndSeparator("a\\b/c.txt", true));
        assertEquals("/a/b/c.txt", FilenameUtils.normalizeNoEndSeparator("\\a\\b/c.txt", true));
    }

    @Test
    public void testNormalizeNoEndSeparatorWindowsSeparatorJavadoc() {
        // Test the javadoc change for normalizeNoEndSeparator method with Windows separator
        assertEquals("\\a\\b\\c.txt", FilenameUtils.normalizeNoEndSeparator("a\\b/c.txt", false));
        assertEquals("\\a\\b\\c.txt", FilenameUtils.normalizeNoEndSeparator("\\a\\b/c.txt", false));
    }

    @Test
    public void testRequireNonNullCharsJavadoc() {
        // Test the javadoc change for requireNonNullChars method
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.normalize("a\\b/c\u0000.txt"));
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.normalize("\u0000a\\b/c.txt"));
    }
}