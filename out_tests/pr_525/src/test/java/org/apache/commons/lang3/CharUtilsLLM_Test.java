package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CharUtilsLLM_Test {

    @Test
    public void testToChar_Character_NullPointerException() {
        assertThrows(NullPointerException.class, () -> CharUtils.toChar((Character) null));
    }

    @Test
    public void testToChar_String_NullPointerException() {
        assertThrows(NullPointerException.class, () -> CharUtils.toChar((String) null));
    }

    @Test
    public void testToIntValue_Character_NullPointerException() {
        assertThrows(NullPointerException.class, () -> CharUtils.toIntValue((Character) null));
    }

    @Test
    public void testToIntValue_Character_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CharUtils.toIntValue(Character.valueOf('A')));
    }
}