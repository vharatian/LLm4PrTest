package org.apache.commons.lang3.text;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

@Deprecated
public class ExtendedMessageFormatLLM_Test {

    private final Map<String, FormatFactory> registry = new HashMap<>();

    @BeforeEach
    public void setUp() {
        registry.put("lower", new LowerCaseFormatFactory());
        registry.put("upper", new UpperCaseFormatFactory());
    }

    @Test
    public void testAppendQuotedString() {
        final String pattern = "This is a ''quoted'' string";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern, registry);
        assertEquals("This is a 'quoted' string", emf.format(new Object[] {}));
    }

    @Test
    public void testAppendQuotedStringWithFormat() {
        final String pattern = "This is a ''{0,upper}'' string";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern, registry);
        assertEquals("This is a 'FOO' string", emf.format(new Object[] {"foo"}));
    }

    @Test
    public void testAppendQuotedStringWithEscapedQuote() {
        final String pattern = "This is a ''''quoted'''' string";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern, registry);
        assertEquals("This is a ''quoted'' string", emf.format(new Object[] {}));
    }

    @Test
    public void testAppendQuotedStringWithNestedQuotes() {
        final String pattern = "This is a ''{0,upper}'' and ''{1,lower}'' string";
        final ExtendedMessageFormat emf = new ExtendedMessageFormat(pattern, registry);
        assertEquals("This is a 'FOO' and 'bar' string", emf.format(new Object[] {"foo", "BAR"}));
    }

    private static class LowerCaseFormat extends Format {
        private static final long serialVersionUID = 1L;

        @Override
        public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
            return toAppendTo.append(((String) obj).toLowerCase(Locale.ROOT));
        }

        @Override
        public Object parseObject(final String source, final ParsePosition pos) {
            throw new UnsupportedOperationException();
        }
    }

    private static class UpperCaseFormat extends Format {
        private static final long serialVersionUID = 1L;

        @Override
        public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
            return toAppendTo.append(((String) obj).toUpperCase(Locale.ROOT));
        }

        @Override
        public Object parseObject(final String source, final ParsePosition pos) {
            throw new UnsupportedOperationException();
        }
    }

    private static class LowerCaseFormatFactory implements FormatFactory {
        private static final Format LOWER_INSTANCE = new LowerCaseFormat();

        @Override
        public Format getFormat(final String name, final String arguments, final Locale locale) {
            return LOWER_INSTANCE;
        }
    }

    private static class UpperCaseFormatFactory implements FormatFactory {
        private static final Format UPPER_INSTANCE = new UpperCaseFormat();

        @Override
        public Format getFormat(final String name, final String arguments, final Locale locale) {
            return UPPER_INSTANCE;
        }
    }
}