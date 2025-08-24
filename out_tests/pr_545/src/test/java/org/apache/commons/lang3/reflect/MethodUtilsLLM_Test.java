package org.apache.commons.lang3.reflect;

import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ClassUtils.Interfaces;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.Mutable;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.reflect.testbed.Annotated;
import org.apache.commons.lang3.reflect.testbed.GenericConsumer;
import org.apache.commons.lang3.reflect.testbed.GenericParent;
import org.apache.commons.lang3.reflect.testbed.PublicChild;
import org.apache.commons.lang3.reflect.testbed.StringParameterizedChild;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MethodUtilsLLM_Test {

    private TestBean testBean;
    private final Map<Class<?>, Class<?>[]> classCache = new HashMap<>();

    @BeforeEach
    public void setUp() {
        testBean = new TestBean();
        classCache.clear();
    }

    @Test
    public void testGetAllSuperclassesAndInterfaces() {
        // Test with a class that has both superclasses and interfaces
        List<Class<?>> result = MethodUtils.getAllSuperclassesAndInterfaces(PublicChild.class);
        assertNotNull(result);
        assertTrue(result.contains(GenericParent.class));
        assertTrue(result.contains(GenericConsumer.class));
        assertTrue(result.contains(Object.class));

        // Test with a class that has no superclasses or interfaces
        result = MethodUtils.getAllSuperclassesAndInterfaces(Object.class);
        assertNotNull(result);
        assertTrue(result.isEmpty());

        // Test with a null input
        result = MethodUtils.getAllSuperclassesAndInterfaces(null);
        assertNull(result);
    }

    @Test
    public void testGetAllSuperclassesAndInterfacesOrder() {
        // Test the order of the returned list
        List<Class<?>> result = MethodUtils.getAllSuperclassesAndInterfaces(PublicChild.class);
        assertNotNull(result);
        assertEquals(GenericParent.class, result.get(0));
        assertEquals(GenericConsumer.class, result.get(1));
        assertEquals(Object.class, result.get(2));
    }

    @Test
    public void testGetAllSuperclassesAndInterfacesWithInterfacesOnly() {
        // Test with a class that implements interfaces but has no superclasses
        List<Class<?>> result = MethodUtils.getAllSuperclassesAndInterfaces(TestBeanWithInterfaces.class);
        assertNotNull(result);
        assertTrue(result.contains(PrivateInterface.class));
        assertTrue(result.contains(Object.class));
    }

    @Test
    public void testGetAllSuperclassesAndInterfacesWithSuperclassesOnly() {
        // Test with a class that has superclasses but no interfaces
        List<Class<?>> result = MethodUtils.getAllSuperclassesAndInterfaces(TestBean.class);
        assertNotNull(result);
        assertTrue(result.contains(Object.class));
    }
}