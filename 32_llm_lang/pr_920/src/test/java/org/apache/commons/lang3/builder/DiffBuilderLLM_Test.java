package org.apache.commons.lang3.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.lang3.AbstractLangTest;
import org.apache.commons.lang3.ArrayUtils;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

public class DiffBuilderLLM_Test extends AbstractLangTest {

    private static final ToStringStyle SHORT_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;

    @Test
    public void testBooleanFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanField = false;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Boolean.class, diff.getType());
        assertEquals(Boolean.TRUE, diff.getLeft());
        assertEquals(Boolean.FALSE, diff.getRight());
    }

    @Test
    public void testBooleanArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.booleanArrayField = new boolean[] {false, false};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.booleanArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.booleanArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testByteFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteField = 0x01;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Byte.valueOf(class1.byteField), diff.getLeft());
        assertEquals(Byte.valueOf(class2.byteField), diff.getRight());
    }

    @Test
    public void testByteArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.byteArrayField = new byte[] {0x01, 0x02};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.byteArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.byteArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testCharFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charField = 'z';
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Character.valueOf(class1.charField), diff.getLeft());
        assertEquals(Character.valueOf(class2.charField), diff.getRight());
    }

    @Test
    public void testCharArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.charArrayField = new char[] {'f', 'o', 'o'};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.charArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.charArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testDoubleFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleField = 99.99;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Double.valueOf(class1.doubleField), diff.getLeft());
        assertEquals(Double.valueOf(class2.doubleField), diff.getRight());
    }

    @Test
    public void testDoubleArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.doubleArrayField = new double[] {3.0, 2.9, 2.8};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.doubleArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.doubleArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testFloatFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatField = 99.99F;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Float.valueOf(class1.floatField), diff.getLeft());
        assertEquals(Float.valueOf(class2.floatField), diff.getRight());
    }

    @Test
    public void testFloatArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.floatArrayField = new float[] {3.0F, 2.9F, 2.8F};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.floatArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.floatArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testIntFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Integer.valueOf(class1.intField), diff.getLeft());
        assertEquals(Integer.valueOf(class2.intField), diff.getRight());
    }

    @Test
    public void testIntArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.intArrayField = new int[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.intArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.intArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testLongFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longField = 42L;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Long.valueOf(class1.longField), diff.getLeft());
        assertEquals(Long.valueOf(class2.longField), diff.getRight());
    }

    @Test
    public void testLongArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.longArrayField = new long[] {3L, 2L, 1L};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.longArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.longArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testShortFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortField = 42;
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(Short.valueOf(class1.shortField), diff.getLeft());
        assertEquals(Short.valueOf(class2.shortField), diff.getRight());
    }

    @Test
    public void testShortArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.shortArrayField = new short[] {3, 2, 1};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(ArrayUtils.toObject(class1.shortArrayField), (Object[]) diff.getLeft());
        assertArrayEquals(ArrayUtils.toObject(class2.shortArrayField), (Object[]) diff.getRight());
    }

    @Test
    public void testObjectFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectField = "Some string";
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertEquals(class1.objectField, diff.getLeft());
        assertEquals(class2.objectField, diff.getRight());
    }

    @Test
    public void testObjectArrayFieldNameChange() {
        final TypeTestClass class1 = new TypeTestClass();
        final TypeTestClass class2 = new TypeTestClass();
        class2.objectArrayField = new Object[] {"string", 1, 2};
        final DiffResult<TypeTestClass> list = class1.diff(class2);
        assertEquals(1, list.getNumberOfDiffs());
        final Diff<?> diff = list.getDiffs().get(0);
        assertArrayEquals(class1.objectArrayField, (Object[]) diff.getLeft());
        assertArrayEquals(class2.objectArrayField, (Object[]) diff.getRight());
    }
}