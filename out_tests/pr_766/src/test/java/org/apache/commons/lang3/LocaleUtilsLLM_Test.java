package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class LocaleUtilsLLM_Test {

    @Test
    public void testToLocale_DashSeparator() {
        assertValidToLocale("en-GB", "en", "GB");
        assertValidToLocale("en-001", "en", "001");
        assertValidToLocale("en-GB-xxx", "en", "GB", "xxx");
    }

    @Test
    public void testToLocale_InvalidDashSeparator() {
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en-GB-"), "Must be at least 5 chars if starts with dash");
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en-GB-#"), "Invalid locale format");
        assertThrows(IllegalArgumentException.class, () -> LocaleUtils.toLocale("en-GB-1"), "Must be letter if starts with dash");
    }

    private static void assertValidToLocale(final String localeString, final String language, final String country) {
        final Locale locale = LocaleUtils.toLocale(localeString);
        assertNotNull(locale, "valid locale");
        assertEquals(language, locale.getLanguage());
        assertEquals(country, locale.getCountry());
        assertTrue(locale.getVariant() == null || locale.getVariant().isEmpty());
    }

    private static void assertValidToLocale(final String localeString, final String language, final String country, final String variant) {
        final Locale locale = LocaleUtils.toLocale(localeString);
        assertNotNull(locale, "valid locale");
        assertEquals(language, locale.getLanguage());
        assertEquals(country, locale.getCountry());
        assertEquals(variant, locale.getVariant());
    }
}