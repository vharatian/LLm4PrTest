package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

public class DiffBuilderLLM_Test {

    private static class TypeTestClass implements Diffable<TypeTestClass> {
        private ToStringStyle style = ToStringStyle.SHORT_PREFIX_STYLE;
        private Object objectField = null;

        @Override
        public DiffResult<TypeTestClass> diff(final TypeTestClass obj) {
            return new DiffBuilder<>(this, obj, style)
                .append("objectField", objectField, obj.objectField)
                .build();
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, false);
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }
    }

    @Test
    public void testObjectUtilsIsArray() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class1.objectField = new int[] {1, 2, 3};
        class2.objectField = new int[] {4, 5, 6};

        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Object[].class, diff.getType());
        assertEquals(ArrayUtils.toObject((int[]) class1.objectField), diff.getLeft());
        assertEquals(ArrayUtils.toObject((int[]) class2.objectField), diff.getRight());
    }

    @Test
    public void testObjectUtilsIsArrayWithNull() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class1.objectField = new int[] {1, 2, 3};
        class2.objectField = null;

        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Object[].class, diff.getType());
        assertEquals(ArrayUtils.toObject((int[]) class1.objectField), diff.getLeft());
        assertEquals(null, diff.getRight());
    }

    @Test
    public void testObjectUtilsIsArrayWithNonArray() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class1.objectField = 123;
        class2.objectField = 456;

        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Integer.class, diff.getType());
        assertEquals(123, diff.getLeft());
        assertEquals(456, diff.getRight());
    }
}