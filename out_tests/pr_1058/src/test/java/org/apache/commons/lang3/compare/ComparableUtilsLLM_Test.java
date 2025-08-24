package org.apache.commons.lang3.compare;

import static org.apache.commons.lang3.compare.ComparableUtils.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Instant;

import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ComparableUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void testMin() {
        assertEquals(Instant.MAX, ComparableUtils.min(Instant.MAX, Instant.MAX));
        assertEquals(Instant.MIN, ComparableUtils.min(Instant.MIN, Instant.MIN));
        assertEquals(Instant.MIN, ComparableUtils.min(Instant.MIN, Instant.MAX));
        assertEquals(Instant.MIN, ComparableUtils.min(Instant.MAX, Instant.MIN));
        assertEquals(Integer.MIN_VALUE, ComparableUtils.min(Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MIN_VALUE)));
        assertEquals(Integer.MAX_VALUE, ComparableUtils.min(Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(Integer.MAX_VALUE)));
        assertEquals(Integer.MIN_VALUE, ComparableUtils.min(Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MAX_VALUE)));
        assertEquals(Integer.MIN_VALUE, ComparableUtils.min(Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(Integer.MIN_VALUE)));
        assertEquals(Instant.MAX, ComparableUtils.min(null, Instant.MAX));
        assertEquals(Instant.MAX, ComparableUtils.min(Instant.MAX, null));
    }
}