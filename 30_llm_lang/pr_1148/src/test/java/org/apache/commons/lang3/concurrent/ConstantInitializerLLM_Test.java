package org.apache.commons.lang3.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.regex.Pattern;
import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConstantInitializerLLM_Test extends AbstractLangTest {
    private static final Integer VALUE = 42;
    private ConstantInitializer<Integer> init;

    @BeforeEach
    public void setUp() {
        init = new ConstantInitializer<>(VALUE);
    }

    @Test
    public void testToStringFormat() {
        final String s = init.toString();
        final Pattern pattern = Pattern.compile("ConstantInitializer@-?\\d+ \\[ object = " + VALUE + " \\]");
        assertTrue(pattern.matcher(s).matches(), "Wrong string format: " + s);
    }

    @Test
    public void testToStringFormatWithNull() {
        final String s = new ConstantInitializer<>(null).toString();
        assertTrue(s.indexOf("object = null") > 0, "Object not found in string: " + s);
    }
}