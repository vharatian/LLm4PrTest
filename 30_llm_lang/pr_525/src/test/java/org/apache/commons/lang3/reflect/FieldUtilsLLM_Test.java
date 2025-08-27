package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FieldUtilsLLM_Test {

    @Test
    public void testGetFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "fieldName"));
    }

    @Test
    public void testGetFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "fieldName", true));
    }

    @Test
    public void testGetFieldIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, ""));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, " "));
    }

    @Test
    public void testGetFieldForceAccessIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, "", true));
        assertThrows(IllegalArgumentException.class, () -> FieldUtils.getField(PublicChild.class, " ", true));
    }
}