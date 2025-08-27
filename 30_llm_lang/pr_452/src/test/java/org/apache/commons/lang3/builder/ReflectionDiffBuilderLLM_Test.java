package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ReflectionDiffBuilderLLM_Test {

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    @SuppressWarnings("unused")
    private static class TypeTestClass implements Diffable<TypeTestClass> {
        private final ToStringStyle style = SHORT_STYLE;
        private final boolean booleanField = true;
        private final boolean[] booleanArrayField = {true};
        private final byte byteField = (byte) 0xFF;
        private final byte[] byteArrayField = {(byte) 0xFF};
        private char charField = 'a';
        private char[] charArrayField = {'a'};
        private final double doubleField = 1.0;
        private final double[] doubleArrayField = {1.0};
        private final float floatField = 1.0f;
        private final float[] floatArrayField = {1.0f};
        int intField = 1;
        private final int[] intArrayField = {1};
        private final long longField = 1L;
        private final long[] longArrayField = {1L};
        private final short shortField = 1;
        private final short[] shortArrayField = {1};
        private final Object objectField = null;
        private final Object[] objectArrayField = {null};
        private static int staticField;
        private transient String transientField;

        @Override
        public DiffResult<TypeTestClass> diff(final TypeTestClass obj) {
            return new ReflectionDiffBuilder<>(this, obj, style).build();
        }

        @Override
        public int hashCode() {
            return HashCodeBuilder.reflectionHashCode(this, false);
        }

        @Override
        public boolean equals(final Object obj) {
            return EqualsBuilder.reflectionEquals(this, obj, false);
        }
    }

    @SuppressWarnings("unused")
    private static class TypeTestChildClass extends TypeTestClass {
        String field = "a";
    }

    @Test
    public void test_generic_type_handling() {
        final TypeTestClass firstObject = new TypeTestClass();
        final TypeTestClass secondObject = new TypeTestClass();
        final DiffResult<TypeTestClass> list = firstObject.diff(secondObject);
        assertEquals(0, list.getNumberOfDiffs());
    }

    @Test
    public void test_generic_type_handling_with_difference() {
        final TypeTestClass firstObject = new TypeTestClass();
        firstObject.charField = 'c';
        final TypeTestClass secondObject = new TypeTestClass();
        final DiffResult<TypeTestClass> list = firstObject.diff(secondObject);
        assertEquals(1, list.getNumberOfDiffs());
    }
}