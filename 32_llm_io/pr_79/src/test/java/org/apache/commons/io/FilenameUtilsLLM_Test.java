package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilenameUtilsLLM_Test {

    @Test
    public void testNormalizeWithAdjoiningSlashes() {
        // Test case where prefix is 0 and there are adjoining slashes
        String input = "/foo//bar";
        String expected = "/foo/bar";
        assertEquals(expected, FilenameUtils.normalize(input));

        // Test case where prefix is greater than 0 and there are adjoining slashes
        input = "C:\\foo\\\\bar";
        expected = "C:\\foo\\bar";
        assertEquals(expected, FilenameUtils.normalize(input, false));
    }

    @Test
    public void testNormalizeWithSingleSlash() {
        // Test case where prefix is 0 and there is a single slash
        String input = "/foo/bar";
        String expected = "/foo/bar";
        assertEquals(expected, FilenameUtils.normalize(input));

        // Test case where prefix is greater than 0 and there is a single slash
        input = "C:\\foo\\bar";
        expected = "C:\\foo\\bar";
        assertEquals(expected, FilenameUtils.normalize(input, false));
    }

    @Test
    public void testNormalizeWithNoSlashes() {
        // Test case where prefix is 0 and there are no slashes
        String input = "foobar";
        String expected = "foobar";
        assertEquals(expected, FilenameUtils.normalize(input));

        // Test case where prefix is greater than 0 and there are no slashes
        input = "C:foobar";
        expected = "C:foobar";
        assertEquals(expected, FilenameUtils.normalize(input, false));
    }
}