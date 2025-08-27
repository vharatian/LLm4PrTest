package org.apache.commons.lang3.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DiffBuilderLLM_Test {

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    private static class TypeTestClass implements Diffable<TypeTestClass> {
        private ToStringStyle style = SHORT_STYLE;
        private boolean booleanField = true;
        private boolean[] booleanArrayField = {true};
        private byte byteField = (byte) 0xFF;
        private byte[] byteArrayField = {(byte) 0xFF};
        private char charField = 'a';
        private char[] charArrayField = {'a'};
        private double doubleField = 1.0;
        private double[] doubleArrayField = {1.0};
        private float floatField = 1.0f;
        private float[] floatArrayField = {1.0f};
        private int intField = 1;
        private int[] intArrayField = {1};
        private long longField = 1L;
        private long[] longArrayField = {1L};
        private short shortField = 1;
        private short[] shortArrayField = {1};
        private Object objectField = null;
        private Object[] objectArrayField = {null};

        @Override
        public DiffResult<TypeTestClass> diff(final TypeTestClass obj) {
            return new DiffBuilder<TypeTestClass>(this, obj, style)
                .append("boolean", booleanField, obj.booleanField)
                .append("booleanArray", booleanArrayField, obj.booleanArrayField)
                .append("byte", byteField, obj.byteField)
                .append("byteArray", byteArrayField, obj.byteArrayField)
                .append("char", charField, obj.charField)
                .append("charArray", charArrayField, obj.charArrayField)
                .append("double", doubleField, obj.doubleField)
                .append("doubleArray", doubleArrayField, obj.doubleArrayField)
                .append("float", floatField, obj.floatField)
                .append("floatArray", floatArrayField, obj.floatArrayField)
                .append("int", intField, obj.intField)
                .append("intArray", intArrayField, obj.intArrayField)
                .append("long", longField, obj.longField)
                .append("longArray", longArrayField, obj.longArrayField)
                .append("short", shortField, obj.shortField)
                .append("shortArray", shortArrayField, obj.shortArrayField)
                .append("objectField", objectField, obj.objectField)
                .append("objectArrayField", objectArrayField, obj.objectArrayField)
                .build();
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

    @Test
    public void testGenericType() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 42;
        final DiffResult<TypeTestClass> list = new DiffBuilder<TypeTestClass>(class1, class2, SHORT_STYLE)
            .append("intField", class1.intField, class2.intField)
            .build();
        assertEquals(1, list.getNumberOfDiffs());
        assertEquals(Integer.class, list.getDiffs().get(0).getType());
        assertEquals(Integer.valueOf(class1.intField), list.getDiffs().get(0).getLeft());
        assertEquals(Integer.valueOf(class2.intField), list.getDiffs().get(0).getRight());
    }

    @Test
    public void testNullLhsWithGenericType() {
        assertThrows(IllegalArgumentException.class, () -> new DiffBuilder<TypeTestClass>(null, new TypeTestClass(), ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testNullRhsWithGenericType() {
        assertThrows(IllegalArgumentException.class, () -> new DiffBuilder<TypeTestClass>(new TypeTestClass(), null, ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testTriviallyEqualTestDisabledWithGenericType() {
        final Matcher<Integer> equalToOne = equalTo(1);
        final DiffBuilder<TypeTestClass> explicitTestAndNotEqual1 = new DiffBuilder<>(new TypeTestClass(), new TypeTestClass(), null, false);
        explicitTestAndNotEqual1.append("letter", "X", "Y");
        assertThat(explicitTestAndNotEqual1.build().getNumberOfDiffs(), equalToOne);
    }

    @Test
    public void testTriviallyEqualTestEnabledWithGenericType() {
        final Matcher<Integer> equalToZero = equalTo(0);
        final Matcher<Integer> equalToOne = equalTo(1);
        final DiffBuilder<TypeTestClass> implicitTestAndEqual = new DiffBuilder<>(new TypeTestClass(), new TypeTestClass(), null);
        implicitTestAndEqual.append("letter", "X", "Y");
        assertThat(implicitTestAndEqual.build().getNumberOfDiffs(), equalToZero);
        final DiffBuilder<TypeTestClass> implicitTestAndNotEqual = new DiffBuilder<>(new TypeTestClass(), new TypeTestClass(), null);
        implicitTestAndNotEqual.append("letter", "X", "Y");
        assertThat(implicitTestAndNotEqual.build().getNumberOfDiffs(), equalToOne);
    }
}