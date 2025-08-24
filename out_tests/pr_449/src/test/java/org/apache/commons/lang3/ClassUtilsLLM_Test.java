package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    public void test_getShortClassName_Object() {
        assertEquals("ClassUtils", ClassUtils.getShortClassName(new ClassUtils(), "<null>"));
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(new ClassUtilsTest.Inner(), "<null>"));
        assertEquals("String", ClassUtils.getShortClassName("hello", "<null>"));
        assertEquals("<null>", ClassUtils.getShortClassName(null, "<null>"));
        class Named {
        }
        assertEquals("ClassUtilsTest2.1", ClassUtils.getShortClassName(new Object() {
        }, "<null>"));
        assertEquals("ClassUtilsTest2.1Named", ClassUtils.getShortClassName(new Named(), "<null>"));
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(new ClassUtilsTest.Inner(), "<null>"));
    }

    @Test
    public void test_getShortClassName_Class() {
        assertEquals("ClassUtils", ClassUtils.getShortClassName(ClassUtils.class));
        assertEquals("Map.Entry", ClassUtils.getShortClassName(Map.Entry.class));
        assertEquals("", ClassUtils.getShortClassName((Class<?>) null));
        assertEquals("String[]", ClassUtils.getShortClassName(String[].class));
        assertEquals("Map.Entry[]", ClassUtils.getShortClassName(Map.Entry[].class));
        assertEquals("boolean", ClassUtils.getShortClassName(boolean.class));
        assertEquals("byte", ClassUtils.getShortClassName(byte.class));
        assertEquals("char", ClassUtils.getShortClassName(char.class));
        assertEquals("short", ClassUtils.getShortClassName(short.class));
        assertEquals("int", ClassUtils.getShortClassName(int.class));
        assertEquals("long", ClassUtils.getShortClassName(long.class));
        assertEquals("float", ClassUtils.getShortClassName(float.class));
        assertEquals("double", ClassUtils.getShortClassName(double.class));
        assertEquals("boolean[]", ClassUtils.getShortClassName(boolean[].class));
        assertEquals("byte[]", ClassUtils.getShortClassName(byte[].class));
        assertEquals("char[]", ClassUtils.getShortClassName(char[].class));
        assertEquals("short[]", ClassUtils.getShortClassName(short[].class));
        assertEquals("int[]", ClassUtils.getShortClassName(int[].class));
        assertEquals("long[]", ClassUtils.getShortClassName(long[].class));
        assertEquals("float[]", ClassUtils.getShortClassName(float[].class));
        assertEquals("double[]", ClassUtils.getShortClassName(double[].class));
        assertEquals("String[][]", ClassUtils.getShortClassName(String[][].class));
        assertEquals("String[][][]", ClassUtils.getShortClassName(String[][][].class));
        assertEquals("String[][][][]", ClassUtils.getShortClassName(String[][][][].class));
        class Named {
        }
        assertEquals("ClassUtilsTest2.2", ClassUtils.getShortClassName(new Object() {
        }.getClass()));
        assertEquals("ClassUtilsTest2.2Named", ClassUtils.getShortClassName(Named.class));
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(ClassUtilsTest.Inner.class));
    }

    @Test
    public void test_getShortClassName_String() {
        assertEquals("ClassUtils", ClassUtils.getShortClassName("org.apache.commons.lang3.ClassUtils"));
        assertEquals("Map.Entry", ClassUtils.getShortClassName("java.util.Map$Entry"));
        assertEquals("", ClassUtils.getShortClassName((String) null));
        assertEquals("", ClassUtils.getShortClassName(""));
    }

    @Test
    public void test_getSimpleName_Class() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(ClassUtils.class));
        assertEquals("Entry", ClassUtils.getSimpleName(Map.Entry.class));
        assertEquals("", ClassUtils.getSimpleName(null));
        assertEquals("String[]", ClassUtils.getSimpleName(String[].class));
        assertEquals("Entry[]", ClassUtils.getSimpleName(Map.Entry[].class));
        assertEquals("boolean", ClassUtils.getSimpleName(boolean.class));
        assertEquals("byte", ClassUtils.getSimpleName(byte.class));
        assertEquals("char", ClassUtils.getSimpleName(char.class));
        assertEquals("short", ClassUtils.getSimpleName(short.class));
        assertEquals("int", ClassUtils.getSimpleName(int.class));
        assertEquals("long", ClassUtils.getSimpleName(long.class));
        assertEquals("float", ClassUtils.getSimpleName(float.class));
        assertEquals("double", ClassUtils.getSimpleName(double.class));
        assertEquals("boolean[]", ClassUtils.getSimpleName(boolean[].class));
        assertEquals("byte[]", ClassUtils.getSimpleName(byte[].class));
        assertEquals("char[]", ClassUtils.getSimpleName(char[].class));
        assertEquals("short[]", ClassUtils.getSimpleName(short[].class));
        assertEquals("int[]", ClassUtils.getSimpleName(int[].class));
        assertEquals("long[]", ClassUtils.getSimpleName(long[].class));
        assertEquals("float[]", ClassUtils.getSimpleName(float[].class));
        assertEquals("double[]", ClassUtils.getSimpleName(double[].class));
        assertEquals("String[][]", ClassUtils.getSimpleName(String[][].class));
        assertEquals("String[][][]", ClassUtils.getSimpleName(String[][][].class));
        assertEquals("String[][][][]", ClassUtils.getSimpleName(String[][][][].class));
        class Named {
        }
        assertEquals("", ClassUtils.getSimpleName(new Object() {
        }.getClass()));
        assertEquals("Named", ClassUtils.getSimpleName(Named.class));
    }

    @Test
    public void test_getSimpleName_Object() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(new ClassUtils()));
        assertEquals("Inner", ClassUtils.getSimpleName(new ClassUtilsTest.Inner()));
        assertEquals("String", ClassUtils.getSimpleName("hello"));
        assertEquals("", ClassUtils.getSimpleName(null));
    }

    @Test
    public void test_getSimpleName_Object_String() {
        assertEquals("ClassUtils", ClassUtils.getSimpleName(new ClassUtils(), "<null>"));
        assertEquals("Inner", ClassUtils.getSimpleName(new ClassUtilsTest.Inner(), "<null>"));
        assertEquals("String", ClassUtils.getSimpleName("hello", "<null>"));
        assertEquals("<null>", ClassUtils.getSimpleName(null, "<null>"));
        assertNull(ClassUtils.getSimpleName(null, null));
    }

    @Test
    public void test_getName_Class() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getName(ClassUtils.class));
        assertEquals("java.util.Map$Entry", ClassUtils.getName(Map.Entry.class));
        assertEquals("", ClassUtils.getName((Class<?>) null));
        assertEquals("[Ljava.lang.String;", ClassUtils.getName(String[].class));
        assertEquals("[Ljava.util.Map$Entry;", ClassUtils.getName(Map.Entry[].class));
        assertEquals("boolean", ClassUtils.getName(boolean.class));
        assertEquals("byte", ClassUtils.getName(byte.class));
        assertEquals("char", ClassUtils.getName(char.class));
        assertEquals("short", ClassUtils.getName(short.class));
        assertEquals("int", ClassUtils.getName(int.class));
        assertEquals("long", ClassUtils.getName(long.class));
        assertEquals("float", ClassUtils.getName(float.class));
        assertEquals("double", ClassUtils.getName(double.class));
        assertEquals("[Z", ClassUtils.getName(boolean[].class));
        assertEquals("[B", ClassUtils.getName(byte[].class));
        assertEquals("[C", ClassUtils.getName(char[].class));
        assertEquals("[S", ClassUtils.getName(short[].class));
        assertEquals("[I", ClassUtils.getName(int[].class));
        assertEquals("[J", ClassUtils.getName(long[].class));
        assertEquals("[F", ClassUtils.getName(float[].class));
        assertEquals("[D", ClassUtils.getName(double[].class));
        assertEquals("[[Ljava.lang.String;", ClassUtils.getName(String[][].class));
        assertEquals("[[[Ljava.lang.String;", ClassUtils.getName(String[][][].class));
        assertEquals("[[[[Ljava.lang.String;", ClassUtils.getName(String[][][][].class));
        class Named {
        }
        assertEquals("org.apache.commons.lang3.ClassUtilsTest2.3", ClassUtils.getName(new Object() {
        }.getClass()));
        assertEquals("org.apache.commons.lang3.ClassUtilsTest2.3Named", ClassUtils.getName(Named.class));
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$Inner", ClassUtils.getName(ClassUtilsTest.Inner.class));
    }

    @Test
    public void test_getName_Object() {
        assertEquals("org.apache.commons.lang3.ClassUtils", ClassUtils.getName(new ClassUtils(), "<null>"));
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$Inner", ClassUtils.getName(new ClassUtilsTest.Inner(), "<null>"));
        assertEquals("java.lang.String", ClassUtils.getName("hello", "<null>"));
        assertEquals("<null>", ClassUtils.getName(null, "<null>"));
        class Named {
        }
        assertEquals("org.apache.commons.lang3.ClassUtilsTest2.4", ClassUtils.getName(new Object() {
        }, "<null>"));
        assertEquals("org.apache.commons.lang3.ClassUtilsTest2.4Named", ClassUtils.getName(new Named(), "<null>"));
        assertEquals("org.apache.commons.lang3.ClassUtilsTest$Inner", ClassUtils.getName(new ClassUtilsTest.Inner(), "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_String() {
        assertEquals("org.apache.commons.lang3",
                ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils"));
        assertEquals("org.apache.commons.lang3",
                ClassUtils.getPackageCanonicalName("[Lorg.apache.commons.lang3.ClassUtils;"));
        assertEquals("org.apache.commons.lang3",
                ClassUtils.getPackageCanonicalName("[[Lorg.apache.commons.lang3.ClassUtils;"));
        assertEquals("org.apache.commons.lang3",
                ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils[]"));
        assertEquals("org.apache.commons.lang3",
                ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils[][]"));
        assertEquals("", ClassUtils.getPackageCanonicalName("[I"));
        assertEquals("", ClassUtils.getPackageCanonicalName("[[I"));
        assertEquals("", ClassUtils.getPackageCanonicalName("int[]"));
        assertEquals("", ClassUtils.getPackageCanonicalName("int[][]"));
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$6"));
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$5Named"));
        assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$Inner"));
    }

    @Test
    public void test_getShortCanonicalName_String() {
        assertEquals("ClassUtils", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils"));
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName("[Lorg.apache.commons.lang3.ClassUtils;"));
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName("[[Lorg.apache.commons.lang3.ClassUtils;"));
        assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils[]"));
        assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils[][]"));
        assertEquals("int[]", ClassUtils.getShortCanonicalName("[I"));
        assertEquals("int[][]", ClassUtils.getShortCanonicalName("[[I"));
        assertEquals("int[]", ClassUtils.getShortCanonicalName("int[]"));
        assertEquals("int[][]", ClassUtils.getShortCanonicalName("int[][]"));
        assertEquals("ClassUtilsTest.6", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$6"));
        assertEquals("ClassUtilsTest.5Named", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$5Named"));
        assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$Inner"));
    }
}