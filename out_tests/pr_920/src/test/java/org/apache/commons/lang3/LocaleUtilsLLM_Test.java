package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class LocaleUtilsLLM_Test {

    /**
     * Test case for the updated Javadoc in the languagesByCountry method.
     * Ensures that the method correctly handles 2-letter country codes.
     */
    @Test
    public void testLanguagesByCountryWithTwoLetterCountryCode() {
        // Valid 2-letter country code
        List<Locale> locales = LocaleUtils.languagesByCountry("US");
        assertNotNull(locales);
        assertTrue(locales.size() > 0);
        for (Locale locale : locales) {
            assertEquals("US", locale.getCountry());
        }

        // Invalid country code (not 2-letter)
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.languagesByCountry("USA"));
    }

    /**
     * Test case for the languagesByCountry method with null input.
     * Ensures that the method returns an empty list when the input is null.
     */
    @Test
    public void testLanguagesByCountryWithNullInput() {
        List<Locale> locales = LocaleUtils.languagesByCountry(null);
        assertNotNull(locales);
        assertTrue(locales.isEmpty());
    }

    /**
     * Test case for the languagesByCountry method with an empty string input.
     * Ensures that the method returns an empty list when the input is an empty string.
     */
    @Test
    public void testLanguagesByCountryWithEmptyString() {
        List<Locale> locales = LocaleUtils.languagesByCountry("");
        assertNotNull(locales);
        assertTrue(locales.isEmpty());
    }

    /**
     * Test case for the languagesByCountry method with a non-existent country code.
     * Ensures that the method returns an empty list when the input is a non-existent country code.
     */
    @Test
    public void testLanguagesByCountryWithNonExistentCountryCode() {
        List<Locale> locales = LocaleUtils.languagesByCountry("ZZ");
        assertNotNull(locales);
        assertTrue(locales.isEmpty());
    }
}