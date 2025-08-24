package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RandomUtilsLLM_Test {

    @Test
    public void testNextLongWithPositiveBound() {
        final long bound = 100L;
        final long result = RandomUtils.nextLong(bound);
        assertTrue(result >= 0 && result < bound);
    }

    @Test
    public void testNextLongWithZeroBound() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextLong(0));
    }

    @Test
    public void testNextLongWithNegativeBound() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextLong(-1));
    }

    @Test
    public void testNextLongWithLargeBound() {
        final long bound = Long.MAX_VALUE;
        final long result = RandomUtils.nextLong(bound);
        assertTrue(result >= 0 && result < bound);
    }
}