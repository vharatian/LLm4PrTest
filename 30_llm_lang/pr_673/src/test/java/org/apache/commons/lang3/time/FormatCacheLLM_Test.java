package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FormatCacheLLM_Test {

    private FormatCache<SimpleDateFormat> formatCache;

    @BeforeEach
    public void setUp() {
        formatCache = new FormatCache<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat createInstance(String pattern, TimeZone timeZone, Locale locale) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
                sdf.setTimeZone(timeZone);
                return sdf;
            }
        };
    }

    @Test
    public void testGetInstanceWithPatternTimeZoneLocale() {
        String pattern = "yyyy-MM-dd";
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Locale locale = Locale.US;

        SimpleDateFormat format1 = formatCache.getInstance(pattern, timeZone, locale);
        SimpleDateFormat format2 = formatCache.getInstance(pattern, timeZone, locale);

        assertNotNull(format1);
        assertNotNull(format2);
        assertSame(format1, format2);
    }

    @Test
    public void testGetPatternForStyle() {
        Locale locale = Locale.US;
        String pattern = FormatCache.getPatternForStyle(DateFormat.SHORT, DateFormat.SHORT, locale);

        assertNotNull(pattern);
        assertFalse(pattern.isEmpty());
    }

    @Test
    public void testMultipartKeyEqualsAndHashCode() {
        FormatCache.MultipartKey key1 = new FormatCache.MultipartKey("key1", "key2");
        FormatCache.MultipartKey key2 = new FormatCache.MultipartKey("key1", "key2");
        FormatCache.MultipartKey key3 = new FormatCache.MultipartKey("key1", "key3");

        assertEquals(key1, key2);
        assertNotEquals(key1, key3);
        assertEquals(key1.hashCode(), key2.hashCode());
        assertNotEquals(key1.hashCode(), key3.hashCode());
    }

    @Test
    public void testGetInstanceDefault() {
        SimpleDateFormat format = formatCache.getInstance();
        assertNotNull(format);
    }
}