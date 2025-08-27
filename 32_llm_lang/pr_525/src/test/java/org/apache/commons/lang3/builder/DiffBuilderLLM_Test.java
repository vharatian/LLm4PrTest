package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DiffBuilderLLM_Test {

    @Test
    public void testAppendWithNullFieldNameThrowsNullPointerException() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        assertThrows(NullPointerException.class, () -> new DiffBuilder<>(class1, class2, ToStringStyle.DEFAULT_STYLE).append(null, class1.diff(class2)));
    }
}