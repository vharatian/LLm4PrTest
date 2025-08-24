package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BooleanUtilsLLM_Test {

    /**
     * Test for the new constants added in BooleanUtils.
     */
    @Test
    public void testConstants() {
        assertEquals("false", BooleanUtils.FALSE);
        assertEquals("no", BooleanUtils.NO);
        assertEquals("off", BooleanUtils.OFF);
        assertEquals("on", BooleanUtils.ON);
        assertEquals("true", BooleanUtils.TRUE);
        assertEquals("yes", BooleanUtils.YES);
    }

    /**
     * Test for toBooleanObject(String) method with the new constants.
     */
    @Test
    public void testToBooleanObjectWithConstants() {
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(BooleanUtils.TRUE));
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(BooleanUtils.FALSE));
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(BooleanUtils.YES));
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(BooleanUtils.NO));
        assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(BooleanUtils.ON));
        assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(BooleanUtils.OFF));
    }

    /**
     * Test for toStringOnOff(boolean) method with the new constants.
     */
    @Test
    public void testToStringOnOffWithConstants() {
        assertEquals(BooleanUtils.ON, BooleanUtils.toStringOnOff(true));
        assertEquals(BooleanUtils.OFF, BooleanUtils.toStringOnOff(false));
    }

    /**
     * Test for toStringOnOff(Boolean) method with the new constants.
     */
    @Test
    public void testToStringOnOffBooleanWithConstants() {
        assertNull(BooleanUtils.toStringOnOff(null));
        assertEquals(BooleanUtils.ON, BooleanUtils.toStringOnOff(Boolean.TRUE));
        assertEquals(BooleanUtils.OFF, BooleanUtils.toStringOnOff(Boolean.FALSE));
    }

    /**
     * Test for toStringTrueFalse(boolean) method with the new constants.
     */
    @Test
    public void testToStringTrueFalseWithConstants() {
        assertEquals(BooleanUtils.TRUE, BooleanUtils.toStringTrueFalse(true));
        assertEquals(BooleanUtils.FALSE, BooleanUtils.toStringTrueFalse(false));
    }

    /**
     * Test for toStringTrueFalse(Boolean) method with the new constants.
     */
    @Test
    public void testToStringTrueFalseBooleanWithConstants() {
        assertNull(BooleanUtils.toStringTrueFalse(null));
        assertEquals(BooleanUtils.TRUE, BooleanUtils.toStringTrueFalse(Boolean.TRUE));
        assertEquals(BooleanUtils.FALSE, BooleanUtils.toStringTrueFalse(Boolean.FALSE));
    }

    /**
     * Test for toStringYesNo(boolean) method with the new constants.
     */
    @Test
    public void testToStringYesNoWithConstants() {
        assertEquals(BooleanUtils.YES, BooleanUtils.toStringYesNo(true));
        assertEquals(BooleanUtils.NO, BooleanUtils.toStringYesNo(false));
    }

    /**
     * Test for toStringYesNo(Boolean) method with the new constants.
     */
    @Test
    public void testToStringYesNoBooleanWithConstants() {
        assertNull(BooleanUtils.toStringYesNo(null));
        assertEquals(BooleanUtils.YES, BooleanUtils.toStringYesNo(Boolean.TRUE));
        assertEquals(BooleanUtils.NO, BooleanUtils.toStringYesNo(Boolean.FALSE));
    }
}