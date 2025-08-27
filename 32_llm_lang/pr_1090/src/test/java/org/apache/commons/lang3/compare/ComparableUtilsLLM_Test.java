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
    public void testMax_withNullValues() {
        // Test cases where one or both arguments are null
        assertEquals(Instant.MAX, ComparableUtils.max(null, Instant.MAX));
        assertEquals(Instant.MAX, ComparableUtils.max(Instant.MAX, null));
        assertEquals(null, ComparableUtils.max(null, null));
    }

    @Test
    public void testMin_withNullValues() {
        // Test cases where one or both arguments are null
        assertEquals(Instant.MAX, ComparableUtils.min(null, Instant.MAX));
        assertEquals(Instant.MAX, ComparableUtils.min(Instant.MAX, null));
        assertEquals(null, ComparableUtils.min(null, null));
    }
}