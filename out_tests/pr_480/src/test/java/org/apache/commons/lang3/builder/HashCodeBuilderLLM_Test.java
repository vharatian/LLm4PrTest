package org.apache.commons.lang3.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class HashCodeBuilderLLM_Test {

    static class TestObjectWithFields {
        private int a;
        private int b;
        private int c;

        TestObjectWithFields(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    @Test
    public void testReflectionAppendFieldOrder() throws NoSuchFieldException {
        TestObjectWithFields obj = new TestObjectWithFields(1, 2, 3);
        Field[] fields = TestObjectWithFields.class.getDeclaredFields();
        Arrays.sort(fields, Comparator.comparing(Field::getName));

        assertEquals("a", fields[0].getName());
        assertEquals("b", fields[1].getName());
        assertEquals("c", fields[2].getName());
    }

    @Test
    public void testReflectionHashCodeFieldOrder() {
        TestObjectWithFields obj = new TestObjectWithFields(1, 2, 3);
        int expectedHashCode = new HashCodeBuilder(17, 37)
                .append(obj.a)
                .append(obj.b)
                .append(obj.c)
                .toHashCode();

        assertEquals(expectedHashCode, HashCodeBuilder.reflectionHashCode(obj));
    }
}