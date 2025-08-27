package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RandomUtilsLLM_Test {

    private static final double DELTA = 1e-5;

    @Test
    public void testNextDoubleEndExclusive() {
        final double result = RandomUtils.nextDouble(33d, 42d);
        assertTrue(result >= 33d && result < 42d);
    }

    @Test
    public void testNextFloatEndExclusive() {
        final float result = RandomUtils.nextFloat(33f, 42f);
        assertTrue(result >= 33f && result < 42f);
    }

    @Test
    public void testNextDoubleEndExclusiveMinimalRange() {
        assertEquals(42.1, RandomUtils.nextDouble(42.1, 42.1), DELTA);
    }

    @Test
    public void testNextFloatEndExclusiveMinimalRange() {
        assertEquals(42.1f, RandomUtils.nextFloat(42.1f, 42.1f), DELTA);
    }

    @Test
    public void testNextDoubleEndExclusiveNegative() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextDouble(-1, 1));
    }

    @Test
    public void testNextFloatEndExclusiveNegative() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextFloat(-1, 1));
    }

    @Test
    public void testNextDoubleEndExclusiveLowerGreaterUpper() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextDouble(2, 1));
    }

    @Test
    public void testNextFloatEndExclusiveLowerGreaterUpper() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextFloat(2, 1));
    }
}