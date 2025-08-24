package org.apache.commons.lang3.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ExtendedMessageFormatLLM_Test {

    @Test
    public void testFormatElementPattern() {
        final String pattern = "{0,number,#.##} and {1,date,short}";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern);
        assertEquals(pattern, emf.toPattern(), "Pattern should match the input pattern");
    }

    @Test
    public void testFormatElementPatternWithWhitespace() {
        final String pattern = "{ 0 , number , #.## } and { 1 , date , short }";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern);
        assertEquals("{0,number,#.##} and {1,date,short}", emf.toPattern(), "Pattern should be trimmed of surrounding whitespace");
    }

    @Test
    public void testFormatElementPatternWithNestedBraces() {
        final String pattern = "{0,choice,0#{1,number,#.##}|1#{1,date,short}}";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern);
        assertEquals(pattern, emf.toPattern(), "Pattern should handle nested braces correctly");
    }
}