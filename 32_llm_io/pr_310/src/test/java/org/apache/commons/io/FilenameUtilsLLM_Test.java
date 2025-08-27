package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class FilenameUtilsLLM_Test {

    @Test
    public void testConcat_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.concat("a", "b\u0000c"));
    }

    @Test
    public void testDoGetFullPath_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Using reflection to access private method
            java.lang.reflect.Method method = FilenameUtils.class.getDeclaredMethod("doGetFullPath", String.class, boolean.class);
            method.setAccessible(true);
            method.invoke(null, "a\u0000b/c", true);
        });
    }

    @Test
    public void testDoGetPath_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Using reflection to access private method
            java.lang.reflect.Method method = FilenameUtils.class.getDeclaredMethod("doGetPath", String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, "a\u0000b/c", 1);
        });
    }

    @Test
    public void testDoNormalize_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Using reflection to access private method
            java.lang.reflect.Method method = FilenameUtils.class.getDeclaredMethod("doNormalize", String.class, char.class, boolean.class);
            method.setAccessible(true);
            method.invoke(null, "a\u0000b/c", '/', true);
        });
    }

    @Test
    public void testGetBaseName_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getBaseName("a\u0000b/c.txt"));
    }

    @Test
    public void testGetFullPath_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getFullPath("a\u0000b/c.txt"));
    }

    @Test
    public void testGetFullPathNoEndSeparator_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getFullPathNoEndSeparator("a\u0000b/c.txt"));
    }

    @Test
    public void testGetName_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getName("a\u0000b/c.txt"));
    }

    @Test
    public void testGetPath_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getPath("a\u0000b/c.txt"));
    }

    @Test
    public void testGetPathNoEndSeparator_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getPathNoEndSeparator("a\u0000b/c.txt"));
    }

    @Test
    public void testGetPrefix_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.getPrefix("a\u0000b/c.txt"));
    }

    @Test
    public void testIsExtension_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.isExtension("a\u0000b/c.txt", "txt"));
    }

    @Test
    public void testNormalize_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.normalize("a\u0000b/c.txt"));
    }

    @Test
    public void testNormalizeNoEndSeparator_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.normalizeNoEndSeparator("a\u0000b/c.txt"));
    }

    @Test
    public void testRemoveExtension_withNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> FilenameUtils.removeExtension("a\u0000b/c.txt"));
    }
}