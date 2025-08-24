package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void test_toBoolean_String_String_String_caseSensitive() {
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean("X", "Y", "N"));
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBoolean(null, "Y", "N"));
    }

    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_caseSensitive() {
        assertThrows(IllegalArgumentException.class,
                () -> BooleanUtils.toBooleanObject(Integer.valueOf(9), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8)));
        assertThrows(IllegalArgumentException.class,
                () -> BooleanUtils.toBooleanObject(null, Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8)));
    }

    @Test
    public void test_toBooleanObject_String_String_String_String_caseSensitive() {
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject("X", "Y", "N", "U"));
        assertThrows(IllegalArgumentException.class, () -> BooleanUtils.toBooleanObject(null, "Y", "N", "U"));
    }
}