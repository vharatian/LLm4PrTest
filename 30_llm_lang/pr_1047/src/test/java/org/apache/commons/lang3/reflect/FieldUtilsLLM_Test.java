package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FieldUtilsLLM_Test {

    @Test
    public void testGetFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getField(null, "fieldName"));
    }

    @Test
    public void testGetDeclaredFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getDeclaredField(null, "fieldName"));
    }

    @Test
    public void testGetAllFieldsNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getAllFields(null));
    }

    @Test
    public void testGetAllFieldsListNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getAllFieldsList(null));
    }

    @Test
    public void testGetFieldsWithAnnotationNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(null, Annotated.class));
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsWithAnnotation(FieldUtilsTest2.class, null));
    }

    @Test
    public void testGetFieldsListWithAnnotationNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(null, Annotated.class));
        assertThrows(NullPointerException.class, () -> FieldUtils.getFieldsListWithAnnotation(FieldUtilsTest2.class, null));
    }

    @Test
    public void testReadStaticFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null));
    }

    @Test
    public void testReadStaticFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null, true));
    }

    @Test
    public void testReadStaticFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null, "fieldName"));
    }

    @Test
    public void testReadStaticFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readStaticField(null, "fieldName", true));
    }

    @Test
    public void testReadDeclaredStaticFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(null, "fieldName"));
    }

    @Test
    public void testReadDeclaredStaticFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readDeclaredStaticField(null, "fieldName", true));
    }

    @Test
    public void testReadFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readField(null, new Object()));
    }

    @Test
    public void testReadFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readField(null, new Object(), true));
    }

    @Test
    public void testReadFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readField(null, "fieldName"));
    }

    @Test
    public void testReadFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readField(null, "fieldName", true));
    }

    @Test
    public void testReadDeclaredFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readDeclaredField(null, "fieldName"));
    }

    @Test
    public void testReadDeclaredFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.readDeclaredField(null, "fieldName", true));
    }

    @Test
    public void testWriteStaticFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeStaticField(null, "value"));
    }

    @Test
    public void testWriteStaticFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeStaticField(null, "value", true));
    }

    @Test
    public void testWriteStaticFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeStaticField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteStaticFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeStaticField(null, "fieldName", "value", true));
    }

    @Test
    public void testWriteDeclaredStaticFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteDeclaredStaticFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredStaticField(null, "fieldName", "value", true));
    }

    @Test
    public void testWriteFieldNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeField(null, new Object(), "value"));
    }

    @Test
    public void testWriteFieldForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeField(null, new Object(), "value", true));
    }

    @Test
    public void testWriteFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeField(null, "fieldName", "value", true));
    }

    @Test
    public void testWriteDeclaredFieldByNameNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredField(null, "fieldName", "value"));
    }

    @Test
    public void testWriteDeclaredFieldByNameForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.writeDeclaredField(null, "fieldName", "value", true));
    }

    @Test
    public void testRemoveFinalModifierNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.removeFinalModifier(null));
    }

    @Test
    public void testRemoveFinalModifierForceAccessNullPointerException() {
        assertThrows(NullPointerException.class, () -> FieldUtils.removeFinalModifier(null, true));
    }
}