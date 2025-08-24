package org.apache.commons.lang3.text.translate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

@Deprecated
public class NumericEntityUnescaperLLM_Test {

    @Test
    public void testHexEntityParsing() {
        final NumericEntityUnescaper neu = new NumericEntityUnescaper();
        final String input = "&#x41;";
        final String expected = "A";
        final String result = neu.translate(input);
        assertEquals(expected, result, "Failed to unescape hex numeric entities");
    }

    @Test
    public void testDecimalEntityParsing() {
        final NumericEntityUnescaper neu = new NumericEntityUnescaper();
        final String input = "&#65;";
        final String expected = "A";
        final String result = neu.translate(input);
        assertEquals(expected, result, "Failed to unescape decimal numeric entities");
    }

    @Test
    public void testEntityWithoutSemicolon() {
        final NumericEntityUnescaper neu = new NumericEntityUnescaper(NumericEntityUnescaper.OPTION.semiColonOptional);
        final String input = "&#65";
        final String expected = "A";
        final String result = neu.translate(input);
        assertEquals(expected, result, "Failed to unescape numeric entities without semicolon");
    }

    @Test
    public void testEntityWithErrorIfNoSemicolon() {
        final NumericEntityUnescaper neu = new NumericEntityUnescaper(NumericEntityUnescaper.OPTION.errorIfNoSemiColon);
        final String input = "&#65";
        assertThrows(IllegalArgumentException.class, () -> neu.translate(input), "Failed to throw exception for numeric entities without semicolon");
    }
}