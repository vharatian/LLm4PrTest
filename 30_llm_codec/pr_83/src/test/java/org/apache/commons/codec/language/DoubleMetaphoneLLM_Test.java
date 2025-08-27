package org.apache.commons.codec.language;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DoubleMetaphoneLLM_Test {

    @Test
    public void testConditionCH0() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

        // Test cases where conditionCH0 should return true
        assertTrue(doubleMetaphone.conditionCH0("CHARACTER", 0));
        assertTrue(doubleMetaphone.conditionCH0("HARAC", 0));
        assertTrue(doubleMetaphone.conditionCH0("HARIS", 0));
        assertTrue(doubleMetaphone.conditionCH0("HOR", 0));
        assertTrue(doubleMetaphone.conditionCH0("HYM", 0));
        assertTrue(doubleMetaphone.conditionCH0("HIA", 0));
        assertTrue(doubleMetaphone.conditionCH0("HEM", 0));

        // Test cases where conditionCH0 should return false
        assertFalse(doubleMetaphone.conditionCH0("CHORE", 0));
        assertFalse(doubleMetaphone.conditionCH0("CHORES", 0));
        assertFalse(doubleMetaphone.conditionCH0("CH", 0));
        assertFalse(doubleMetaphone.conditionCH0("CHARACTER", 1));
    }

    @Test
    public void testConditionL0() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();

        // Test cases where conditionL0 should return true
        assertTrue(doubleMetaphone.conditionL0("ILLO", 1));
        assertTrue(doubleMetaphone.conditionL0("ILLA", 1));
        assertTrue(doubleMetaphone.conditionL0("ALLE", 1));
        assertTrue(doubleMetaphone.conditionL0("ASALLE", 1));
        assertTrue(doubleMetaphone.conditionL0("OSALLE", 1));
        assertTrue(doubleMetaphone.conditionL0("AALLE", 1));
        assertTrue(doubleMetaphone.conditionL0("OALLE", 1));

        // Test cases where conditionL0 should return false
        assertFalse(doubleMetaphone.conditionL0("ILLO", 2));
        assertFalse(doubleMetaphone.conditionL0("ILLA", 2));
        assertFalse(doubleMetaphone.conditionL0("ALLE", 2));
        assertFalse(doubleMetaphone.conditionL0("ASALLE", 2));
        assertFalse(doubleMetaphone.conditionL0("OSALLE", 2));
        assertFalse(doubleMetaphone.conditionL0("AALLE", 2));
        assertFalse(doubleMetaphone.conditionL0("OALLE", 2));
    }
}