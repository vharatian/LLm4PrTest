package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testEqualsWithNormalization() {
        // Test when normalization returns null for fileName1
        assertFalse(FilenameUtils.equals(null, "file2.txt", true, IOCase.SENSITIVE));
        assertFalse(FilenameUtils.equals("", "file2.txt", true, IOCase.SENSITIVE));

        // Test when normalization returns null for fileName2
        assertFalse(FilenameUtils.equals("file1.txt", null, true, IOCase.SENSITIVE));
        assertFalse(FilenameUtils.equals("file1.txt", "", true, IOCase.SENSITIVE));

        // Test when both file names are null after normalization
        assertFalse(FilenameUtils.equals(null, null, true, IOCase.SENSITIVE));
        assertFalse(FilenameUtils.equals("", "", true, IOCase.SENSITIVE));

        // Test when both file names are not null after normalization
        assertTrue(FilenameUtils.equals("file1.txt", "file1.txt", true, IOCase.SENSITIVE));
        assertFalse(FilenameUtils.equals("file1.txt", "file2.txt", true, IOCase.SENSITIVE));
    }
}