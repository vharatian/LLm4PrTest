package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExtendedMessageFormatLLM_Test {

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

    private static class LowerCaseFormatFactory implements FormatFactory {
        private static final Format LOWER_INSTANCE = new LowerCaseFormat();

        @Override
        public Format getFormat(final String name, final String arguments, final Locale locale) {
            return LOWER_INSTANCE;
        }
    }

    private final Map<String, FormatFactory> registry = new HashMap<>();

    @BeforeEach
    public void setUp() {
        registry.put("lower", new LowerCaseFormatFactory());
    }

    @Test
    public void testRegistryIsUnmodifiable() {
        Map<String, FormatFactory> modifiableRegistry = new HashMap<>();
        modifiableRegistry.put("lower", new LowerCaseFormatFactory());

        ExtendedMessageFormat emf = new ExtendedMessageFormat("Pattern: {0,lower}", Locale.US, modifiableRegistry);

        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> {
            emf.registry.put("upper", new LowerCaseFormatFactory());
        });
    }

    @Test
    public void testNullRegistry() {
        ExtendedMessageFormat emf = new ExtendedMessageFormat("Pattern: {0,lower}", Locale.US, null);
        assertEquals("Pattern: {0,lower}", emf.toPattern());
    }
}