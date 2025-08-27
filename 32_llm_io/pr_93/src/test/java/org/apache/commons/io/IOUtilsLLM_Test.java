package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IOUtilsLLM_Test {

    @Test
    public void testContentEqualsIgnoreEOL() throws Exception {
        // Test case where both readers are equal
        BufferedReader reader1 = new BufferedReader(new StringReader("line1\nline2\nline3"));
        BufferedReader reader2 = new BufferedReader(new StringReader("line1\nline2\nline3"));
        assertTrue(IOUtils.contentEqualsIgnoreEOL(reader1, reader2));

        // Test case where readers differ in content
        reader1 = new BufferedReader(new StringReader("line1\nline2\nline3"));
        reader2 = new BufferedReader(new StringReader("line1\nline2\ndifferentLine"));
        assertFalse(IOUtils.contentEqualsIgnoreEOL(reader1, reader2));

        // Test case where one reader is longer than the other
        reader1 = new BufferedReader(new StringReader("line1\nline2\nline3"));
        reader2 = new BufferedReader(new StringReader("line1\nline2"));
        assertFalse(IOUtils.contentEqualsIgnoreEOL(reader1, reader2));

        // Test case where both readers are empty
        reader1 = new BufferedReader(new StringReader(""));
        reader2 = new BufferedReader(new StringReader(""));
        assertTrue(IOUtils.contentEqualsIgnoreEOL(reader1, reader2));

        // Test case where one reader is empty and the other is not
        reader1 = new BufferedReader(new StringReader(""));
        reader2 = new BufferedReader(new StringReader("line1"));
        assertFalse(IOUtils.contentEqualsIgnoreEOL(reader1, reader2));
    }
}