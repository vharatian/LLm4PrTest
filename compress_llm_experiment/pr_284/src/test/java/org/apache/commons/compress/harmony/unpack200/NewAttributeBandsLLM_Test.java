package org.apache.commons.compress.harmony.unpack200;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;

public class NewAttributeBandsLLM_Test {

    @Test
    public void testGetStreamUpToMatchingBracket() throws IOException {
        NewAttributeBands newAttributeBands = new NewAttributeBands(null, null);
        StringReader input = new StringReader("[abc[def]ghi]");
        StringReader result = newAttributeBands.getStreamUpToMatchingBracket(input);
        char[] buffer = new char[100];
        int length = result.read(buffer);
        assertEquals("abc[def]ghi", new String(buffer, 0, length));
    }

    @Test
    public void testReadUpToMatchingBracket() throws IOException {
        NewAttributeBands newAttributeBands = new NewAttributeBands(null, null);
        StringReader input = new StringReader("[abc[def]ghi]");
        String result = newAttributeBands.readUpToMatchingBracket(input);
        assertEquals("abc[def]ghi", result);
    }
}