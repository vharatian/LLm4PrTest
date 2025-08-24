package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;

public class NewAttributeBandsLLM_Test {

    @Test
    public void testGetStreamUpToMatchingBracket() throws IOException {
        NewAttributeBands bands = new NewAttributeBands(1, null, null, null);
        StringReader reader = new StringReader("[test]");
        StringReader result = bands.getStreamUpToMatchingBracket(reader);
        char[] buffer = new char[4];
        result.read(buffer);
        assertArrayEquals(new char[]{'t', 'e', 's', 't'}, buffer);
    }

    @Test
    public void testReadUpToMatchingBracket() throws IOException {
        NewAttributeBands bands = new NewAttributeBands(1, null, null, null);
        StringReader reader = new StringReader("[test]");
        String result = bands.readUpToMatchingBracket(reader);
        assertEquals("test", result);
    }
}