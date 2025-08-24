package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.LocaleUtils;
import org.junit.jupiter.api.Test;

public class FastDateParserLLM_Test {

    @Test
    public void testAppendDisplayNames() {
        // Setup
        Calendar calendar = Calendar.getInstance();
        Locale locale = Locale.US;
        int field = Calendar.MONTH;
        StringBuilder regex = new StringBuilder();

        // Execute
        Map<String, Integer> result = FastDateParser.appendDisplayNames(calendar, locale, field, regex);

        // Verify
        assertEquals(12, result.size(), "Expected 12 month names in the result map");
        assertEquals("((?iu)january|february|march|april|may|june|july|august|september|october|november|december)", regex.toString());
    }

    @Test
    public void testAppendDisplayNamesWithDifferentLocale() {
        // Setup
        Calendar calendar = Calendar.getInstance();
        Locale locale = Locale.FRANCE;
        int field = Calendar.MONTH;
        StringBuilder regex = new StringBuilder();

        // Execute
        Map<String, Integer> result = FastDateParser.appendDisplayNames(calendar, locale, field, regex);

        // Verify
        assertEquals(12, result.size(), "Expected 12 month names in the result map");
        assertEquals("((?iu)janvier|février|mars|avril|mai|juin|juillet|août|septembre|octobre|novembre|décembre)", regex.toString());
    }

    @Test
    public void testAppendDisplayNamesWithInvalidField() {
        // Setup
        Calendar calendar = Calendar.getInstance();
        Locale locale = Locale.US;
        int field = -1; // Invalid field
        StringBuilder regex = new StringBuilder();

        // Execute & Verify
        assertThrows(IllegalArgumentException.class, () -> {
            FastDateParser.appendDisplayNames(calendar, locale, field, regex);
        });
    }
}