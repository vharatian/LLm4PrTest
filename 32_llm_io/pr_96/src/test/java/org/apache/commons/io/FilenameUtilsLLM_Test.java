package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testConcatWithParentDirectory() {
        // Test case for the updated javadoc example
        String basePath = "/foo/a/";
        String fullFileNameToAdd = "../bar";
        String expected = "/foo/bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertEquals(expected, result);
    }

    @Test
    public void testConcatWithNullBasePath() {
        // Test case for null base path
        String basePath = null;
        String fullFileNameToAdd = "/bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertNull(result);
    }

    @Test
    public void testConcatWithEmptyBasePath() {
        // Test case for empty base path
        String basePath = "";
        String fullFileNameToAdd = "/bar";
        String expected = "/bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertEquals(expected, result);
    }

    @Test
    public void testConcatWithAbsolutePath() {
        // Test case for absolute path
        String basePath = "/foo";
        String fullFileNameToAdd = "/bar";
        String expected = "/bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertEquals(expected, result);
    }

    @Test
    public void testConcatWithRelativePath() {
        // Test case for relative path
        String basePath = "/foo";
        String fullFileNameToAdd = "bar";
        String expected = "/foo/bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertEquals(expected, result);
    }

    @Test
    public void testConcatWithWindowsPath() {
        // Test case for Windows path
        String basePath = "C:\\foo";
        String fullFileNameToAdd = "bar";
        String expected = "C:\\foo\\bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertEquals(expected, result);
    }

    @Test
    public void testConcatWithMixedSeparators() {
        // Test case for mixed separators
        String basePath = "/foo";
        String fullFileNameToAdd = "C:/bar";
        String expected = "C:/bar";
        String result = FilenameUtils.concat(basePath, fullFileNameToAdd);
        assertEquals(expected, result);
    }
}