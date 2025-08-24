package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LongRangeLLM_Test extends AbstractLangTest {

    private static LongRange of(final int min, final int max) {
        return LongRange.of(min, max);
    }

    private static LongRange of(final Long min, final Long max) {
        return LongRange.of(min, max);
    }

    private LongRange range1;
    private LongRange range2;
    private LongRange range3;
    private LongRange rangeFull;

    @BeforeEach
    public void setUp() {
        range1 = of(10, 20);
        range2 = of(10, 20);
        range3 = of(-2, -1);
        rangeFull = of(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    @Test
    public void testConstructorWithoutComparator() {
        LongRange range = new LongRange(10L, 20L);
        assertNotNull(range);
        assertEquals(10L, range.getMinimum());
        assertEquals(20L, range.getMaximum());
    }

    @Test
    public void testConstructorNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LongRange(null, 20L));
        assertThrows(NullPointerException.class, () -> new LongRange(10L, null));
    }
}