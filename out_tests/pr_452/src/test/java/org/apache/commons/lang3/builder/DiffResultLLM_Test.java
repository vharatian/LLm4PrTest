package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DiffResultLLM_Test {

    private static final SimpleClass SIMPLE_FALSE = new SimpleClass(false);
    private static final SimpleClass SIMPLE_TRUE = new SimpleClass(true);
    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    private static class SimpleClass implements Diffable<SimpleClass> {
        private final boolean booleanField;

        SimpleClass(final boolean booleanField) {
            this.booleanField = booleanField;
        }

        static String getFieldName() {
            return "booleanField";
        }

        @Override
        public DiffResult<SimpleClass> diff(final SimpleClass obj) {
            return new DiffBuilder<>(this, obj, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(getFieldName(), booleanField, obj.booleanField)
                .build();
        }
    }

    private static class EmptyClass {
    }

    @Test
    public void testGenericType() {
        final SimpleClass lhs = new SimpleClass(true);
        final SimpleClass rhs = new SimpleClass(false);
        final List<Diff<?>> diffs = lhs.diff(rhs).getDiffs();
        final DiffResult<SimpleClass> diffResult = new DiffResult<>(lhs, rhs, diffs, SHORT_STYLE);

        // Ensure the left and right objects are of type SimpleClass
        assertEquals(lhs, diffResult.getLeft());
        assertEquals(rhs, diffResult.getRight());
    }

    @Test
    public void testGenericTypeWithEmptyClass() {
        final EmptyClass lhs = new EmptyClass();
        final EmptyClass rhs = new EmptyClass();
        final List<Diff<?>> diffs = new DiffBuilder<>(lhs, rhs, SHORT_STYLE).build().getDiffs();
        final DiffResult<EmptyClass> diffResult = new DiffResult<>(lhs, rhs, diffs, SHORT_STYLE);

        // Ensure the left and right objects are of type EmptyClass
        assertEquals(lhs, diffResult.getLeft());
        assertEquals(rhs, diffResult.getRight());
    }
}