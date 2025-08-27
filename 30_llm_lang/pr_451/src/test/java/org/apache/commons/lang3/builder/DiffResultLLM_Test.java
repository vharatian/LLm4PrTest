package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        public DiffResult diff(final SimpleClass obj) {
            return new DiffBuilder(this, obj, ToStringStyle.SHORT_PREFIX_STYLE)
                .append(getFieldName(), booleanField, obj.booleanField)
                .build();
        }
    }

    @Test
    public void testGetLeft() {
        final SimpleClass lhs = new SimpleClass(true);
        final SimpleClass rhs = new SimpleClass(false);
        final List<Diff<?>> diffs = lhs.diff(rhs).getDiffs();
        final DiffResult diffResult = new DiffResult(lhs, rhs, diffs, SHORT_STYLE);
        assertEquals(lhs, diffResult.getLeft());
    }

    @Test
    public void testGetRight() {
        final SimpleClass lhs = new SimpleClass(true);
        final SimpleClass rhs = new SimpleClass(false);
        final List<Diff<?>> diffs = lhs.diff(rhs).getDiffs();
        final DiffResult diffResult = new DiffResult(lhs, rhs, diffs, SHORT_STYLE);
        assertEquals(rhs, diffResult.getRight());
    }
}