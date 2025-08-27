package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DiffBuilderLLM_Test extends AbstractLangTest {

    @Test
    public void testNullFieldNameThrowsNullPointerException() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, true, false));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new boolean[]{true}, new boolean[]{false}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, (byte) 1, (byte) 2));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new byte[]{1}, new byte[]{2}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, 'a', 'b'));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new char[]{'a'}, new char[]{'b'}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, 1.0, 2.0));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new double[]{1.0}, new double[]{2.0}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, 1.0f, 2.0f));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new float[]{1.0f}, new float[]{2.0f}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, 1, 2));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new int[]{1}, new int[]{2}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, 1L, 2L));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new long[]{1L}, new long[]{2L}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, (short) 1, (short) 2));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new short[]{1}, new short[]{2}));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new Object(), new Object()));
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append(null, new Object[]{new Object()}, new Object[]{new Object()}));
    }

    @Test
    public void testNullDiffResultThrowsNullPointerException() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, SHORT_STYLE).append("fieldName", (DiffResult<TypeTestClass>) null));
    }
}