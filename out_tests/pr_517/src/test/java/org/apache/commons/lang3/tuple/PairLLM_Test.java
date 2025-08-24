package org.apache.commons.lang3.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;
import java.util.Objects;

public class PairLLM_Test {

    @Test
    public void testHashCodeWithNullValues() {
        final Pair<String, String> pair = Pair.of(null, null);
        assertEquals(Objects.hashCode(null) ^ Objects.hashCode(null), pair.hashCode());
    }

    @Test
    public void testHashCodeWithNonNullValues() {
        final Pair<String, String> pair = Pair.of("Key", "Value");
        assertEquals(Objects.hashCode("Key") ^ Objects.hashCode("Value"), pair.hashCode());
    }

    @Test
    public void testHashCodeWithMixedValues() {
        final Pair<String, String> pair1 = Pair.of(null, "Value");
        final Pair<String, String> pair2 = Pair.of("Key", null);
        assertEquals(Objects.hashCode(null) ^ Objects.hashCode("Value"), pair1.hashCode());
        assertEquals(Objects.hashCode("Key") ^ Objects.hashCode(null), pair2.hashCode());
    }
}