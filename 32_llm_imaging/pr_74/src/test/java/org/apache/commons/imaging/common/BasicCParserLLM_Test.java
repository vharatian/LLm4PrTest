package org.apache.commons.imaging.common;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BasicCParserLLM_Test {

    @Test
    public void testUnescapeStringHexParsing() throws ImageReadException {
        StringBuilder stringBuilder = new StringBuilder();
        String input = "\"\\x41\""; // "\x41" should be parsed as 'A'
        BasicCParser.unescapeString(stringBuilder, input);
        assertEquals("A", stringBuilder.toString());
    }

    @Test
    public void testUnescapeStringInvalidHex() {
        StringBuilder stringBuilder = new StringBuilder();
        String input = "\"\\xG1\""; // Invalid hex character 'G'
        assertThrows(ImageReadException.class, () -> {
            BasicCParser.unescapeString(stringBuilder, input);
        });
    }

    @Test
    public void testUnescapeStringShortHex() {
        StringBuilder stringBuilder = new StringBuilder();
        String input = "\"\\x4\""; // Hex constant too short
        assertThrows(ImageReadException.class, () -> {
            BasicCParser.unescapeString(stringBuilder, input);
        });
    }
}