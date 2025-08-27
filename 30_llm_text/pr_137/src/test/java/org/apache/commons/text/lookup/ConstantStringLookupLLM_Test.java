package org.apache.commons.text.lookup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ConstantStringLookupLLM_Test {

    private ConstantStringLookup stringLookup;

    @AfterEach
    public void afterEach() {
        ConstantStringLookup.clear();
    }

    @BeforeEach
    public void beforeEach() {
        stringLookup = ConstantStringLookup.INSTANCE;
    }

    @Test
    public void testFieldSeparatorConstant() {
        // Ensure the FIELD_SEPARATOR constant is correctly defined
        Assertions.assertEquals('.', getFieldSeparator(), "FIELD_SEPARATOR constant has incorrect value");
    }

    private char getFieldSeparator() {
        try {
            java.lang.reflect.Field field = ConstantStringLookup.class.getDeclaredField("FIELD_SEPARATOR");
            field.setAccessible(true);
            return field.getChar(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access FIELD_SEPARATOR field", e);
        }
    }
}