package org.apache.commons.lang3.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NumberUtilsLLM_Test {

    @Test
    public void testIsCreatable() {
        // Test cases for the corrected javadoc comments in isCreatable method
        assertTrue(NumberUtils.isCreatable("12345"));
        assertTrue(NumberUtils.isCreatable("1234.5"));
        assertTrue(NumberUtils.isCreatable(".12345"));
        assertTrue(NumberUtils.isCreatable("1234E5"));
        assertTrue(NumberUtils.isCreatable("1234E+5"));
        assertTrue(NumberUtils.isCreatable("1234E-5"));
        assertTrue(NumberUtils.isCreatable("123.4E5"));
        assertTrue(NumberUtils.isCreatable("-1234"));
        assertTrue(NumberUtils.isCreatable("-1234.5"));
        assertTrue(NumberUtils.isCreatable("-.12345"));
        assertTrue(NumberUtils.isCreatable("-1234E5"));
        assertTrue(NumberUtils.isCreatable("0"));
        assertTrue(NumberUtils.isCreatable("0.1"));
        assertTrue(NumberUtils.isCreatable("-0"));
        assertTrue(NumberUtils.isCreatable("01234"));
        assertTrue(NumberUtils.isCreatable("-01234"));
        assertTrue(NumberUtils.isCreatable("-0xABC123"));
        assertTrue(NumberUtils.isCreatable("-0x0"));
        assertTrue(NumberUtils.isCreatable("123.4E21D"));
        assertTrue(NumberUtils.isCreatable("-221.23F"));
        assertTrue(NumberUtils.isCreatable("22338L"));
        assertFalse(NumberUtils.isCreatable(null));
        assertFalse(NumberUtils.isCreatable(""));
        assertFalse(NumberUtils.isCreatable(" "));
        assertFalse(NumberUtils.isCreatable("\r\n\t"));
        assertFalse(NumberUtils.isCreatable("--2.3"));
        assertFalse(NumberUtils.isCreatable(".12.3"));
        assertFalse(NumberUtils.isCreatable("-123E"));
        assertFalse(NumberUtils.isCreatable("-123E+-212"));
        assertFalse(NumberUtils.isCreatable("-123E2.12"));
        assertFalse(NumberUtils.isCreatable("0xGF"));
        assertFalse(NumberUtils.isCreatable("0xFAE-1"));
        assertFalse(NumberUtils.isCreatable("."));
        assertFalse(NumberUtils.isCreatable("-0ABC123"));
        assertFalse(NumberUtils.isCreatable("123.4E-D"));
        assertFalse(NumberUtils.isCreatable("123.4ED"));
        assertFalse(NumberUtils.isCreatable("1234E5l"));
        assertFalse(NumberUtils.isCreatable("11a"));
        assertFalse(NumberUtils.isCreatable("1a"));
        assertFalse(NumberUtils.isCreatable("a"));
        assertFalse(NumberUtils.isCreatable("11g"));
        assertFalse(NumberUtils.isCreatable("11z"));
        assertFalse(NumberUtils.isCreatable("11def"));
        assertFalse(NumberUtils.isCreatable("11d11"));
        assertFalse(NumberUtils.isCreatable("11 11"));
        assertFalse(NumberUtils.isCreatable(" 1111"));
        assertFalse(NumberUtils.isCreatable("1111 "));
        assertTrue(NumberUtils.isCreatable("2."));
        assertFalse(NumberUtils.isCreatable("1.1L"));
        assertTrue(NumberUtils.isCreatable("+0xF"));
        assertTrue(NumberUtils.isCreatable("+0xFFFFFFFF"));
        assertTrue(NumberUtils.isCreatable("+0xFFFFFFFFFFFFFFFF"));
        assertTrue(NumberUtils.isCreatable(".0"));
        assertTrue(NumberUtils.isCreatable("0."));
        assertTrue(NumberUtils.isCreatable("0.D"));
        assertTrue(NumberUtils.isCreatable("0e1"));
        assertTrue(NumberUtils.isCreatable("0e1D"));
        assertFalse(NumberUtils.isCreatable(".D"));
        assertFalse(NumberUtils.isCreatable(".e10"));
        assertFalse(NumberUtils.isCreatable(".e10D"));
    }

    @Test
    public void testIsNumber() {
        // Test cases for the corrected javadoc comments in isNumber method
        assertTrue(NumberUtils.isNumber("12345"));
        assertTrue(NumberUtils.isNumber("1234.5"));
        assertTrue(NumberUtils.isNumber(".12345"));
        assertTrue(NumberUtils.isNumber("1234E5"));
        assertTrue(NumberUtils.isNumber("1234E+5"));
        assertTrue(NumberUtils.isNumber("1234E-5"));
        assertTrue(NumberUtils.isNumber("123.4E5"));
        assertTrue(NumberUtils.isNumber("-1234"));
        assertTrue(NumberUtils.isNumber("-1234.5"));
        assertTrue(NumberUtils.isNumber("-.12345"));
        assertTrue(NumberUtils.isNumber("-0001.12345"));
        assertTrue(NumberUtils.isNumber("-000.12345"));
        assertTrue(NumberUtils.isNumber("+00.12345"));
        assertTrue(NumberUtils.isNumber("+0002.12345"));
        assertTrue(NumberUtils.isNumber("-1234E5"));
        assertTrue(NumberUtils.isNumber("0"));
        assertTrue(NumberUtils.isNumber("-0"));
        assertTrue(NumberUtils.isNumber("01234"));
        assertTrue(NumberUtils.isNumber("-01234"));
        assertTrue(NumberUtils.isNumber("-0xABC123"));
        assertTrue(NumberUtils.isNumber("-0x0"));
        assertTrue(NumberUtils.isNumber("123.4E21D"));
        assertTrue(NumberUtils.isNumber("-221.23F"));
        assertTrue(NumberUtils.isNumber("22338L"));
        assertFalse(NumberUtils.isNumber(null));
        assertFalse(NumberUtils.isNumber(""));
        assertFalse(NumberUtils.isNumber(" "));
        assertFalse(NumberUtils.isNumber("\r\n\t"));
        assertFalse(NumberUtils.isNumber("--2.3"));
        assertFalse(NumberUtils.isNumber(".12.3"));
        assertFalse(NumberUtils.isNumber("-123E"));
        assertFalse(NumberUtils.isNumber("-123E+-212"));
        assertFalse(NumberUtils.isNumber("-123E2.12"));
        assertFalse(NumberUtils.isNumber("0xGF"));
        assertFalse(NumberUtils.isNumber("0xFAE-1"));
        assertFalse(NumberUtils.isNumber("."));
        assertFalse(NumberUtils.isNumber("-0ABC123"));
        assertFalse(NumberUtils.isNumber("123.4E-D"));
        assertFalse(NumberUtils.isNumber("123.4ED"));
        assertFalse(NumberUtils.isNumber("+000E.12345"));
        assertFalse(NumberUtils.isNumber("-000E.12345"));
        assertFalse(NumberUtils.isNumber("1234E5l"));
        assertFalse(NumberUtils.isNumber("11a"));
        assertFalse(NumberUtils.isNumber("1a"));
        assertFalse(NumberUtils.isNumber("a"));
        assertFalse(NumberUtils.isNumber("11g"));
        assertFalse(NumberUtils.isNumber("11z"));
        assertFalse(NumberUtils.isNumber("11def"));
        assertFalse(NumberUtils.isNumber("11d11"));
        assertFalse(NumberUtils.isNumber("11 11"));
        assertFalse(NumberUtils.isNumber(" 1111"));
        assertFalse(NumberUtils.isNumber("1111 "));
        assertTrue(NumberUtils.isNumber("2."));
        assertFalse(NumberUtils.isNumber("1.1L"));
        assertTrue(NumberUtils.isNumber("+0xF"));
        assertTrue(NumberUtils.isNumber("+0xFFFFFFFF"));
        assertTrue(NumberUtils.isNumber("+0xFFFFFFFFFFFFFFFF"));
        assertTrue(NumberUtils.isNumber(".0"));
        assertTrue(NumberUtils.isNumber("0."));
        assertTrue(NumberUtils.isNumber("0.D"));
        assertTrue(NumberUtils.isNumber("0e1"));
        assertTrue(NumberUtils.isNumber("0e1D"));
        assertFalse(NumberUtils.isNumber(".D"));
        assertFalse(NumberUtils.isNumber(".e10"));
        assertFalse(NumberUtils.isNumber(".e10D"));
    }

    @Test
    public void testIsCreatableEdgeCases() {
        // Test cases for edge cases in isCreatable method
        assertTrue(NumberUtils.isCreatable("0x7FFFFFFFFFFFFFFF"));
        assertTrue(NumberUtils.isCreatable("0x8000000000000000"));
        assertTrue(NumberUtils.isCreatable("0xFFFFFFFFFFFFFFFF"));
        assertTrue(NumberUtils.isCreatable("0x8000000000000000L"));
        assertTrue(NumberUtils.isCreatable("0xFFFFFFFFFFFFFFFFL"));
        assertTrue(NumberUtils.isCreatable("0x7FFFFFFFFFFFFFFFL"));
        assertTrue(NumberUtils.isCreatable("0x8000000000000000l"));
        assertTrue(NumberUtils.isCreatable("0xFFFFFFFFFFFFFFFFl"));
        assertTrue(NumberUtils.isCreatable("0x7FFFFFFFFFFFFFFFl"));
    }

    @Test
    public void testIsNumberEdgeCases() {
        // Test cases for edge cases in isNumber method
        assertTrue(NumberUtils.isNumber("0x7FFFFFFFFFFFFFFF"));
        assertTrue(NumberUtils.isNumber("0x8000000000000000"));
        assertTrue(NumberUtils.isNumber("0xFFFFFFFFFFFFFFFF"));
        assertTrue(NumberUtils.isNumber("0x8000000000000000L"));
        assertTrue(NumberUtils.isNumber("0xFFFFFFFFFFFFFFFFL"));
        assertTrue(NumberUtils.isNumber("0x7FFFFFFFFFFFFFFFL"));
        assertTrue(NumberUtils.isNumber("0x8000000000000000l"));
        assertTrue(NumberUtils.isNumber("0xFFFFFFFFFFFFFFFFl"));
        assertTrue(NumberUtils.isNumber("0x7FFFFFFFFFFFFFFFl"));
    }
}