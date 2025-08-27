package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testGetPrefixLength_Windows() {
        assertEquals(0, FilenameUtils.getPrefixLength("a\\b\\c.txt"));
        assertEquals(1, FilenameUtils.getPrefixLength("\\a\\b\\c.txt"));
        assertEquals(2, FilenameUtils.getPrefixLength("C:a\\b\\c.txt"));
        assertEquals(3, FilenameUtils.getPrefixLength("C:\\a\\b\\c.txt"));
        assertEquals(9, FilenameUtils.getPrefixLength("\\\\server\\a\\b\\c.txt"));
        assertEquals(-1, FilenameUtils.getPrefixLength("\\\\\\a\\b\\c.txt"));
    }

    @Test
    public void testGetPrefixLength_Unix() {
        assertEquals(0, FilenameUtils.getPrefixLength("a/b/c.txt"));
        assertEquals(1, FilenameUtils.getPrefixLength("/a/b/c.txt"));
        assertEquals(2, FilenameUtils.getPrefixLength("~/a/b/c.txt"));
        assertEquals(2, FilenameUtils.getPrefixLength("~"));
        assertEquals(6, FilenameUtils.getPrefixLength("~user/a/b/c.txt"));
        assertEquals(6, FilenameUtils.getPrefixLength("~user"));
        assertEquals(9, FilenameUtils.getPrefixLength("//server/a/b/c.txt"));
        assertEquals(-1, FilenameUtils.getPrefixLength("///a/b/c.txt"));
        assertEquals(0, FilenameUtils.getPrefixLength("C:"));
    }

    @Test
    public void testGetPrefixLength_DriveLetterSupport() {
        // Assuming FileSystem.getCurrent().supportsDriveLetter() returns false
        // Mock or set up the environment accordingly if needed
        assertEquals(0, FilenameUtils.getPrefixLength("C:"));
    }
}