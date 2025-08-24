package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class StringUtilsLLM_Test {

    @Test
    public void testJoin_BooleanArrayChar() {
        assertNull(StringUtils.join((boolean[]) null, ','));
        assertEquals("", StringUtils.join(new boolean[] {}, ','));
        assertEquals("true;false;true", StringUtils.join(new boolean[] {true, false, true}, ';'));
        assertEquals("false;false", StringUtils.join(new boolean[] {false, false}, ';'));
    }

    @Test
    public void testJoin_BooleanArrayCharIntInt() {
        assertNull(StringUtils.join((boolean[]) null, ';', 0, 1));
        assertEquals("", StringUtils.join(new boolean[] {}, ';', 0, 1));
        assertEquals("true;false", StringUtils.join(new boolean[] {true, false, true}, ';', 0, 2));
        assertEquals("false", StringUtils.join(new boolean[] {true, false, true}, ';', 1, 2));
        assertEquals("", StringUtils.join(new boolean[] {true, false, true}, ';', 2, 2));
        assertEquals("", StringUtils.join(new boolean[] {true, false, true}, ';', 3, 2));
    }
}