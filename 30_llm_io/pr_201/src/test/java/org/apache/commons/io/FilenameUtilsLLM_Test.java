package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testWildcardMatch() {
        assertTrue(FilenameUtils.wildcardMatch("file.txt", "file.txt"));
        assertTrue(FilenameUtils.wildcardMatch("file.txt", "*.txt"));
        assertTrue(FilenameUtils.wildcardMatch("file.txt", "f?le.txt"));
        assertFalse(FilenameUtils.wildcardMatch("file.txt", "file.tx?"));
        assertFalse(FilenameUtils.wildcardMatch("file.txt", "file.tx?"));
    }

    @Test
    public void testWildcardMatchWithCaseSensitivity() {
        assertTrue(FilenameUtils.wildcardMatch("file.txt", "FILE.TXT", IOCase.INSENSITIVE));
        assertFalse(FilenameUtils.wildcardMatch("file.txt", "FILE.TXT", IOCase.SENSITIVE));
    }

    @Test
    public void testWildcardMatchOnSystem() {
        assertTrue(FilenameUtils.wildcardMatchOnSystem("file.txt", "file.txt"));
        assertTrue(FilenameUtils.wildcardMatchOnSystem("file.txt", "*.txt"));
        assertTrue(FilenameUtils.wildcardMatchOnSystem("file.txt", "f?le.txt"));
        assertFalse(FilenameUtils.wildcardMatchOnSystem("file.txt", "file.tx?"));
    }

    @Test
    public void testWildcardMatchWithBacktrack() {
        assertTrue(FilenameUtils.wildcardMatch("file.txt", "*file.txt"));
        assertTrue(FilenameUtils.wildcardMatch("file.txt", "file.*"));
        assertFalse(FilenameUtils.wildcardMatch("file.txt", "file.*.txt"));
    }
}