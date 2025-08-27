package org.apache.commons.lang3.mutable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MutableIntLLM_Test {
    /**
     * Test for the toInteger method to ensure it returns an Integer instance.
     */
    @Test
    public void testToInteger() {
        MutableInt mutNum = new MutableInt(0);
        assertEquals(Integer.valueOf(0), mutNum.toInteger());

        mutNum = new MutableInt(123);
        assertEquals(Integer.valueOf(123), mutNum.toInteger());
    }
}