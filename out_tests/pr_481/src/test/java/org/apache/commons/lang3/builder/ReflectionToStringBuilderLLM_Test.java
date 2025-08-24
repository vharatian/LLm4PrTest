package org.apache.commons.lang3.builder;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReflectionToStringBuilderLLM_Test {

    @Test
    public void testFieldsAreSortedByName() throws NoSuchFieldException {
        class TestClass {
            private int bField;
            private int aField;
            private int cField;
        }

        Field[] fields = TestClass.class.getDeclaredFields();
        Arrays.sort(fields, Comparator.comparing(Field::getName));

        String[] fieldNames = Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
        assertArrayEquals(new String[]{"aField", "bField", "cField"}, fieldNames);
    }
}