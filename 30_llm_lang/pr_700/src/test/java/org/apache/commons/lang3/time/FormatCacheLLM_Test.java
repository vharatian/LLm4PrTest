package org.apache.commons.lang3.time;

import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import static org.junit.jupiter.api.Assertions.*;

public class FormatCacheLLM_Test {

    private static class TestFormatCache extends FormatCache<SimpleDateFormat> {
        @Override
        protected SimpleDateFormat createInstance(String pattern, TimeZone timeZone, Locale locale) {
            SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
            format.setTimeZone(timeZone);
            return format;
        }
    }

    private final FormatCache<SimpleDateFormat> formatCache = new TestFormatCache();

    @Test
    public void testGetPatternForStyleWithDateStyleNull() {
        Locale locale = Locale.US;
        Integer timeStyle = DateFormat.SHORT;
        String pattern = FormatCache.getPatternForStyle(null, timeStyle, locale);
        assertNotNull(pattern);
        assertEquals(((SimpleDateFormat) DateFormat.getTimeInstance(timeStyle, locale)).toPattern(), pattern);
    }

    @Test
    public void testGetPatternForStyleWithTimeStyleNull() {
        Locale locale = Locale.US;
        Integer dateStyle = DateFormat.SHORT;
        String pattern = FormatCache.getPatternForStyle(dateStyle, null, locale);
        assertNotNull(pattern);
        assertEquals(((SimpleDateFormat) DateFormat.getDateInstance(dateStyle, locale)).toPattern(), pattern);
    }

    @Test
    public void testGetPatternForStyleWithBothStyles() {
        Locale locale = Locale.US;
        Integer dateStyle = DateFormat.SHORT;
        Integer timeStyle = DateFormat.SHORT;
        String pattern = FormatCache.getPatternForStyle(dateStyle, timeStyle, locale);
        assertNotNull(pattern);
        assertEquals(((SimpleDateFormat) DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale)).toPattern(), pattern);
    }

    @Test
    public void testArrayKeyEqualsAndHashCode() {
        FormatCache.ArrayKey key1 = new FormatCache.ArrayKey("pattern", TimeZone.getDefault(), Locale.US);
        FormatCache.ArrayKey key2 = new FormatCache.ArrayKey("pattern", TimeZone.getDefault(), Locale.US);
        FormatCache.ArrayKey key3 = new FormatCache.ArrayKey("different", TimeZone.getDefault(), Locale.US);

        assertEquals(key1, key2);
        assertNotEquals(key1, key3);
        assertEquals(key1.hashCode(), key2.hashCode());
        assertNotEquals(key1.hashCode(), key3.hashCode());
    }
}