package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateStringLookupLLM_Test {
    @Test
    public void testBadFormat() {
        assertThrows(IllegalArgumentException.class,
            () -> DateStringLookup.INSTANCE.lookup("this-is-a-bad-format-dontcha-know"));
    }

    @Test
    public void testDefault() throws ParseException {
        final String formatted = DateStringLookup.INSTANCE.lookup(null);
        DateFormat.getInstance().parse(formatted); 
    }

    @Test
    public void testFormat() {
        final String format = "yyyy-MM-dd";
        final String value = DateStringLookup.INSTANCE.lookup(format);
        assertNotNull(value, "No Date");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        final String today = simpleDateFormat.format(new Date());
        assertEquals(value, today);
    }

    @Test
    public void testToString() {
        Assertions.assertFalse(DateStringLookup.INSTANCE.toString().isEmpty());
    }
}