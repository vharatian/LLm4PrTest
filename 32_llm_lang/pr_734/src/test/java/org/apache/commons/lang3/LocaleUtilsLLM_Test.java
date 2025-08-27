package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LocaleUtilsLLM_Test {

    @Test
    public void testIsAvailableLocaleUsingSet() {
        // Ensure that the method isAvailableLocale uses the set of available locales
        final Set<Locale> set = LocaleUtils.availableLocaleSet();
        for (Locale locale : set) {
            assertTrue(LocaleUtils.isAvailableLocale(locale), "Locale should be available: " + locale);
        }
    }

    @Test
    public void testIsAvailableLocaleNotInSet() {
        // Ensure that the method isAvailableLocale returns false for locales not in the set
        final Locale nonExistentLocale = new Locale("xx", "YY");
        assertTrue(!LocaleUtils.isAvailableLocale(nonExistentLocale), "Locale should not be available: " + nonExistentLocale);
    }
}