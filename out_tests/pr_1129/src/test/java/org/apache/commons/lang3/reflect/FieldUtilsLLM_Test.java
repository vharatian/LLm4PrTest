package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FieldUtilsLLM_Test {

    @Test
    public void testReadDeclaredFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readDeclaredField(null, "fieldName"));
    }

    @Test
    public void testReadDeclaredFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readDeclaredField(null, "fieldName", true));
    }

    @Test
    public void testWriteDeclaredFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteDeclaredFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredField(null, "fieldName", "value", true));
    }

    @Test
    public void testWriteDeclaredStaticFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteDeclaredStaticFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(null, "fieldName", "value", true));
    }

    @Test
    public void testWriteFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeField(null, "fieldName", "value", true));
    }

    @Test
    public void testWriteStaticFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeStaticField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteStaticFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeStaticField(null, "fieldName", "value", true));
    }
}