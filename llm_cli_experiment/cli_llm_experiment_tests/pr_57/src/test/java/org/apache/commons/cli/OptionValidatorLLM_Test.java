package org.apache.commons.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OptionValidatorLLM_Test {

    @Test
    void testValidateOptionWithNull() {
        assertDoesNotThrow(() -> OptionValidator.validateOption(null));
    }

    @Test
    void testValidateOptionWithSingleValidCharacter() {
        assertDoesNotThrow(() -> OptionValidator.validateOption("a"));
        assertDoesNotThrow(() -> OptionValidator.validateOption("?"));
        assertDoesNotThrow(() -> OptionValidator.validateOption("@"));
    }

    @Test
    void testValidateOptionWithSingleInvalidCharacter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OptionValidator.validateOption("!"));
        assertEquals("Illegal option name '!'", exception.getMessage());
    }

    @Test
    void testValidateOptionWithMultipleValidCharacters() {
        assertDoesNotThrow(() -> OptionValidator.validateOption("abc"));
        assertDoesNotThrow(() -> OptionValidator.validateOption("a1b2c3"));
    }

    @Test
    void testValidateOptionWithMultipleInvalidCharacters() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> OptionValidator.validateOption("a!b"));
        assertEquals("The option 'a!b' contains an illegal character : '!'", exception.getMessage());
    }
}