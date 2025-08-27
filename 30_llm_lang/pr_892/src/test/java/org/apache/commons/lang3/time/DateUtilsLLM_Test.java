package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DateUtilsLLM_Test {

    @Test
    public void testGetStandaloneLongMonths() {
        Locale locale = Locale.ENGLISH;
        String[] expected = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] actual = DateUtils.getStandaloneLongMonths(locale);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetStandaloneShortMonths() {
        Locale locale = Locale.ENGLISH;
        String[] expected = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] actual = DateUtils.getStandaloneShortMonths(locale);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testGetStandaloneLongMonths_NullLocale() {
        assertThrows(NullPointerException.class, () -> DateUtils.getStandaloneLongMonths(null));
    }

    @Test
    public void testGetStandaloneShortMonths_NullLocale() {
        assertThrows(NullPointerException.class, () -> DateUtils.getStandaloneShortMonths(null));
    }
}