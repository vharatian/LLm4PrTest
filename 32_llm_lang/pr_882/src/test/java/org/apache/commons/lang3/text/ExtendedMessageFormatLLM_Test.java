package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParsePosition;
import org.junit.jupiter.api.Test;

public class ExtendedMessageFormatLLM_Test {

    @Test
    public void testAppendQuotedString() {
        ExtendedMessageFormat emf = new ExtendedMessageFormat("");
        String pattern = "it''s a {0,lower} 'test'!";
        ParsePosition pos = new ParsePosition(0);
        StringBuilder result = new StringBuilder();
        emf.appendQuotedString(pattern, pos, result);
        assertEquals("it'", result.toString(), "Quoted string should be correctly appended");

        pattern = "'quoted text'";
        pos = new ParsePosition(0);
        result = new StringBuilder();
        emf.appendQuotedString(pattern, pos, result);
        assertEquals("'quoted text'", result.toString(), "Quoted string should be correctly appended");

        pattern = "unmatched 'quote";
        pos = new ParsePosition(0);
        result = new StringBuilder();
        try {
            emf.appendQuotedString(pattern, pos, result);
        } catch (IllegalArgumentException e) {
            assertEquals("Unterminated quoted string at position 0", e.getMessage(), "Exception message should match");
        }
    }
}