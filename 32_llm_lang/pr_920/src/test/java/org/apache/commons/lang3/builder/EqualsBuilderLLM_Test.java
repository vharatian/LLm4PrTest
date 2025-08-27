package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class EqualsBuilderLLM_Test {

    @Test
    public void testReflectionEqualsWithCorrectedJavadoc() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(EqualsBuilder.reflectionEquals(o1, o1));
        assertFalse(EqualsBuilder.reflectionEquals(o1, o2));
        o2.setA(4);
        assertTrue(EqualsBuilder.reflectionEquals(o1, o2));
        assertFalse(EqualsBuilder.reflectionEquals(o1, this));
        assertFalse(EqualsBuilder.reflectionEquals(o1, null));
        assertFalse(EqualsBuilder.reflectionEquals(null, o2));
        assertTrue(EqualsBuilder.reflectionEquals(null, null));
    }

    @Test
    public void testReflectionAppendWithCorrectedJavadoc() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(new EqualsBuilder().reflectionAppend(o1, o1).build());
        assertFalse(new EqualsBuilder().reflectionAppend(o1, o2).build());
        o2.setA(4);
        assertTrue(new EqualsBuilder().reflectionAppend(o1, o2).build());
        assertFalse(new EqualsBuilder().reflectionAppend(o1, this).build());
        assertFalse(new EqualsBuilder().reflectionAppend(o1, null).build());
        assertFalse(new EqualsBuilder().reflectionAppend(null, o2).build());
    }

    @Test
    public void testAppendWithCorrectedJavadoc() {
        final TestObject o1 = new TestObject(4);
        final TestObject o2 = new TestObject(5);
        assertTrue(new EqualsBuilder().append(o1, o1).isEquals());
        assertFalse(new EqualsBuilder().append(o1, o2).isEquals());
        o2.setA(4);
        assertTrue(new EqualsBuilder().append(o1, o2).isEquals());
        assertFalse(new EqualsBuilder().append(o1, this).isEquals());
        assertFalse(new EqualsBuilder().append(o1, null).isEquals());
        assertFalse(new EqualsBuilder().append(null, o2).isEquals());
        assertTrue(new EqualsBuilder().append((Object) null, null).isEquals());
    }

    @Test
    public void testAppendArrayWithCorrectedJavadoc() {
        final long[] array1 = new long[2];
        array1[0] = 5L;
        array1[1] = 6L;
        final long[] array2 = new long[2];
        array2[0] = 5L;
        array2[1] = 6L;
        assertTrue(new EqualsBuilder().append(array1, array1).isEquals());
        assertTrue(new EqualsBuilder().append(array1, array2).isEquals());
        array1[1] = 7;
        assertFalse(new EqualsBuilder().append(array1, array2).isEquals());
    }
}