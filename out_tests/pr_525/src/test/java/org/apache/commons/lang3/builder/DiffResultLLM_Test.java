package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    /**
     * Test to ensure NullPointerException is thrown when lhs is null.
     */
    @Test
    public void testNullLhsThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
            () -> new DiffResult(null, SIMPLE_FALSE, SIMPLE_TRUE.diff(SIMPLE_FALSE).getDiffs(), SHORT_STYLE));
    }

    /**
     * Test to ensure NullPointerException is thrown when rhs is null.
     */
    @Test
    public void testNullRhsThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
            () -> new DiffResult(SIMPLE_TRUE, null, SIMPLE_TRUE.diff(SIMPLE_FALSE).getDiffs(), SHORT_STYLE));
    }

    /**
     * Test to ensure NullPointerException is thrown when diffs list is null.
     */
    @Test
    public void testNullDiffsThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
            () -> new DiffResult(SIMPLE_TRUE, SIMPLE_FALSE, null, SHORT_STYLE));
    }
}