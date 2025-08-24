package org.apache.commons.lang3.time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import java.util.Locale;
import java.util.TimeZone;
import static org.junit.jupiter.api.Assertions.*;

public class FormatCacheLLM_Test {

    private FormatCache<SimpleDateFormat> formatCache;

    @BeforeEach
    public void setUp() {
        formatCache = new FormatCache<SimpleDateFormat>() {
            @Override
            protected SimpleDateFormat createInstance(String pattern, TimeZone timeZone, Locale locale) {
                return new SimpleDateFormat(pattern, locale);
            }
        };
    }

    @Test
    public void testGetInstanceWithNullPattern() {
        // Verify that a NullPointerException is thrown when pattern is null
        Executable executable = () -> formatCache.getInstance(null, TimeZone.getDefault(), Locale.getDefault());
        assertThrows(NullPointerException.class, executable, "Expected getInstance() to throw, but it didn't");
    }

    @Test
    public void testGetInstanceWithInvalidPattern() {
        // Verify that an IllegalArgumentException is thrown when pattern is invalid
        Executable executable = () -> formatCache.getInstance("invalid_pattern", TimeZone.getDefault(), Locale.getDefault());
        assertThrows(IllegalArgumentException.class, executable, "Expected getInstance() to throw, but it didn't");
    }
}