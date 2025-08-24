package org.apache.commons.compress.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class ParsingUtilsLLM_Test {

    @Test
    public void testParseIntValueWithValidInput() throws IOException {
        assertEquals(123, ParsingUtils.parseIntValue("123"));
    }

    @Test
    public void testParseIntValueWithInvalidInput() {
        assertThrows(IOException.class, () -> ParsingUtils.parseIntValue("abc"));
    }

    @Test
    public void testParseIntValueWithRadix() throws IOException {
        assertEquals(10, ParsingUtils.parseIntValue("A", 16));
    }

    @Test
    public void testParseIntValueWithInvalidRadixInput() {
        assertThrows(IOException.class, () -> ParsingUtils.parseIntValue("G", 16));
    }

    @Test
    public void testParseLongValueWithValidInput() throws IOException {
        assertEquals(123456789012345L, ParsingUtils.parseLongValue("123456789012345"));
    }

    @Test
    public void testParseLongValueWithInvalidInput() {
        assertThrows(IOException.class, () -> ParsingUtils.parseLongValue("abc"));
    }

    @Test
    public void testParseLongValueWithRadix() throws IOException {
        assertEquals(255, ParsingUtils.parseLongValue("FF", 16));
    }

    @Test
    public void testParseLongValueWithInvalidRadixInput() {
        assertThrows(IOException.class, () -> ParsingUtils.parseLongValue("G", 16));
    }
}