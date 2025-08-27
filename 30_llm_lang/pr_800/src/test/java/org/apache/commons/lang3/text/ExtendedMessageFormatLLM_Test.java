package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParsePosition;
import org.junit.jupiter.api.Test;

public class ExtendedMessageFormatLLM_Test {

    /**
     * Test to ensure seekNonWs method correctly skips whitespace characters.
     */
    @Test
    public void testSeekNonWs() {
        ExtendedMessageFormat emf = new ExtendedMessageFormat("");
        ParsePosition pos = new ParsePosition(0);
        
        // Test with leading whitespaces
        String pattern = "   abc";
        emf.seekNonWs(pattern, pos);
        assertEquals(3, pos.getIndex(), "Position should be at the first non-whitespace character");

        // Test with no leading whitespaces
        pos.setIndex(0);
        pattern = "abc";
        emf.seekNonWs(pattern, pos);
        assertEquals(0, pos.getIndex(), "Position should remain at the start when no leading whitespace");

        // Test with mixed whitespaces
        pos.setIndex(0);
        pattern = " \t\nabc";
        emf.seekNonWs(pattern, pos);
        assertEquals(3, pos.getIndex(), "Position should be at the first non-whitespace character after mixed whitespaces");
    }
}