package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class HashCodeBuilderLLM_Test {

    @Test
    public void testReflectionHashCodeNullPointerException1() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null));
    }

    @Test
    public void testReflectionHashCodeNullPointerException2() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(17, 37, null));
    }

    @Test
    public void testReflectionHashCodeNullPointerException3() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(17, 37, null, true));
    }

    @Test
    public void testReflectionHashCodeNullPointerException4() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(17, 37, null, true, TestObject.class));
    }

    @Test
    public void testReflectionHashCodeNullPointerException5() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null, "field1", "field2"));
    }

    @Test
    public void testReflectionHashCodeNullPointerException6() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null, true));
    }

    @Test
    public void testReflectionHashCodeNullPointerException7() {
        assertThrows(NullPointerException.class, () -> HashCodeBuilder.reflectionHashCode(null, (Collection<String>) null));
    }
}