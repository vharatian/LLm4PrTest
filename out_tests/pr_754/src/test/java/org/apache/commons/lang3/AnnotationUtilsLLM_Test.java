package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnnotationUtilsLLM_Test {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
        String string();
        String[] strings();
        Class<?> type();
        Class<?>[] types();
        byte byteValue();
        byte[] byteValues();
        short shortValue();
        short[] shortValues();
        int intValue();
        int[] intValues();
        char charValue();
        char[] charValues();
        long longValue();
        long[] longValues();
        float floatValue();
        float[] floatValues();
        double doubleValue();
        double[] doubleValues();
        boolean booleanValue();
        boolean[] booleanValues();
        Stooge stooge();
        Stooge[] stooges();
        NestAnnotation nest();
        NestAnnotation[] nests();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface NestAnnotation {
        String string();
        String[] strings();
        Class<?> type();
        Class<?>[] types();
        byte byteValue();
        byte[] byteValues();
        short shortValue();
        short[] shortValues();
        int intValue();
        int[] intValues();
        char charValue();
        char[] charValues();
        long longValue();
        long[] longValues();
        float floatValue();
        float[] floatValues();
        double doubleValue();
        double[] doubleValues();
        boolean booleanValue();
        boolean[] booleanValues();
        Stooge stooge();
        Stooge[] stooges();
    }

    public enum Stooge {
        MOE, LARRY, CURLY, JOE, SHEMP
    }

    private Field field1;
    private Field field2;

    @TestAnnotation(
        string = "",
        strings = { "" },
        type = Object.class,
        types = { Object.class },
        byteValue = 0,
        byteValues = { 0 },
        shortValue = 0,
        shortValues = { 0 },
        intValue = 0,
        intValues = { 0 },
        charValue = 0,
        charValues = { 0 },
        longValue = 0,
        longValues = { 0 },
        floatValue = 0,
        floatValues = { 0 },
        doubleValue = 0,
        doubleValues = { 0 },
        booleanValue = false,
        booleanValues = { false },
        stooge = Stooge.CURLY,
        stooges = { Stooge.MOE, Stooge.LARRY, Stooge.SHEMP },
        nest = @NestAnnotation(
            string = "",
            strings = { "" },
            type = Object.class,
            types = { Object.class },
            byteValue = 0,
            byteValues = { 0 },
            shortValue = 0,
            shortValues = { 0 },
            intValue = 0,
            intValues = { 0 },
            charValue = 0,
            charValues = { 0 },
            longValue = 0,
            longValues = { 0 },
            floatValue = 0,
            floatValues = { 0 },
            doubleValue = 0,
            doubleValues = { 0 },
            booleanValue = false,
            booleanValues = { false },
            stooge = Stooge.CURLY,
            stooges = { Stooge.MOE, Stooge.LARRY, Stooge.SHEMP }
        ),
        nests = {
            @NestAnnotation(
                string = "",
                strings = { "" },
                type = Object.class,
                types = { Object.class },
                byteValue = 0,
                byteValues = { 0 },
                shortValue = 0,
                shortValues = { 0 },
                intValue = 0,
                intValues = { 0 },
                charValue = 0,
                charValues = { 0 },
                longValue = 0,
                longValues = { 0 },
                floatValue = 0,
                floatValues = { 0 },
                doubleValue = 0,
                doubleValues = { 0 },
                booleanValue = false,
                booleanValues = { false },
                stooge = Stooge.CURLY,
                stooges = { Stooge.MOE, Stooge.LARRY, Stooge.SHEMP }
            )
        }
    )
    public Object dummy1;

    @TestAnnotation(
        string = "",
        strings = { "" },
        type = Object[].class,
        types = { Object[].class },
        byteValue = 0,
        byteValues = { 0 },
        shortValue = 0,
        shortValues = { 0 },
        intValue = 0,
        intValues = { 0 },
        charValue = 0,
        charValues = { 0 },
        longValue = 0,
        longValues = { 0 },
        floatValue = 0,
        floatValues = { 0 },
        doubleValue = 0,
        doubleValues = { 0 },
        booleanValue = false,
        booleanValues = { false },
        stooge = Stooge.CURLY,
        stooges = { Stooge.MOE, Stooge.LARRY, Stooge.SHEMP },
        nest = @NestAnnotation(
            string = "",
            strings = { "" },
            type = Object.class,
            types = { Object.class },
            byteValue = 0,
            byteValues = { 0 },
            shortValue = 0,
            shortValues = { 0 },
            intValue = 0,
            intValues = { 0 },
            charValue = 0,
            charValues = { 0 },
            longValue = 0,
            longValues = { 0 },
            floatValue = 0,
            floatValues = { 0 },
            doubleValue = 0,
            doubleValues = { 0 },
            booleanValue = false,
            booleanValues = { false },
            stooge = Stooge.CURLY,
            stooges = { Stooge.MOE, Stooge.LARRY, Stooge.SHEMP }
        ),
        nests = {
            @NestAnnotation(
                string = "",
                strings = { "" },
                type = Object.class,
                types = { Object.class },
                byteValue = 0,
                byteValues = { 0 },
                shortValue = 0,
                shortValues = { 0 },
                intValue = 0,
                intValues = { 0 },
                charValue = 0,
                charValues = { 0 },
                longValue = 0,
                longValues = { 0 },
                floatValue = 0,
                floatValues = { 0 },
                doubleValue = 0,
                doubleValues = { 0 },
                booleanValue = false,
                booleanValues = { false },
                stooge = Stooge.CURLY,
                stooges = { Stooge.MOE, Stooge.LARRY, Stooge.SHEMP }
            )
        }
    )
    public Object dummy2;

    @BeforeEach
    public void setup() throws Exception {
        field1 = getClass().getDeclaredField("dummy1");
        field2 = getClass().getDeclaredField("dummy2");
    }

    @Test
    public void testHashMemberWithArray() {
        TestAnnotation annotation1 = field1.getAnnotation(TestAnnotation.class);
        TestAnnotation annotation2 = field2.getAnnotation(TestAnnotation.class);

        int hash1 = AnnotationUtils.hashCode(annotation1);
        int hash2 = AnnotationUtils.hashCode(annotation2);

        assertEquals(hash1, hash2);
    }

    @Test
    public void testHashMemberWithNonArray() {
        TestAnnotation annotation1 = field1.getAnnotation(TestAnnotation.class);

        int hash = AnnotationUtils.hashCode(annotation1);

        assertTrue(hash != 0);
    }

    @Test
    public void testHashMemberWithNullValue() {
        TestAnnotation annotation1 = field1.getAnnotation(TestAnnotation.class);

        int hash = AnnotationUtils.hashCode(annotation1);

        assertTrue(hash != 0);
    }
}