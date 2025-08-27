package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EnumUtilsLLM_Test {

    @Test
    public void test_getEnum_withDefault() {
        assertEquals(Traffic.RED, EnumUtils.getEnum(Traffic.class, "RED", Traffic.GREEN));
        assertEquals(Traffic.AMBER, EnumUtils.getEnum(Traffic.class, "AMBER", Traffic.GREEN));
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, "GREEN", Traffic.RED));
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, "PURPLE", Traffic.GREEN));
        assertEquals(Traffic.GREEN, EnumUtils.getEnum(Traffic.class, null, Traffic.GREEN));
    }

    @Test
    public void test_getEnum_withDefault_nonEnumClass() {
        final Class rawType = Object.class;
        assertNull(EnumUtils.getEnum(rawType, "rawType", null));
    }

    @Test
    public void test_getEnum_withDefault_nullClass() {
        assertThrows(NullPointerException.class, () -> EnumUtils.getEnum((Class<Traffic>) null, "PURPLE", Traffic.RED));
    }

    @Test
    public void test_getEnumIgnoreCase_withDefault() {
        assertEquals(Traffic.RED, EnumUtils.getEnumIgnoreCase(Traffic.class, "red", Traffic.GREEN));
        assertEquals(Traffic.AMBER, EnumUtils.getEnumIgnoreCase(Traffic.class, "Amber", Traffic.GREEN));
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, "grEEn", Traffic.RED));
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, "purple", Traffic.GREEN));
        assertEquals(Traffic.GREEN, EnumUtils.getEnumIgnoreCase(Traffic.class, null, Traffic.GREEN));
    }

    @Test
    public void test_getEnumIgnoreCase_withDefault_nonEnumClass() {
        final Class rawType = Object.class;
        assertNull(EnumUtils.getEnumIgnoreCase(rawType, "rawType", null));
    }

    @Test
    public void test_getEnumIgnoreCase_withDefault_nullClass() {
        assertThrows(NullPointerException.class, () -> EnumUtils.getEnumIgnoreCase((Class<Traffic>) null, "PURPLE", Traffic.RED));
    }
}