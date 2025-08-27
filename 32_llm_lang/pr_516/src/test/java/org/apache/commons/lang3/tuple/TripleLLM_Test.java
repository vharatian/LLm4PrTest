package org.apache.commons.lang3.tuple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class TripleLLM_Test {

    @Test
    public void testHashCodeConsistency() {
        final Triple<String, String, String> triple1 = Triple.of("A", "B", "C");
        final Triple<String, String, String> triple2 = Triple.of("A", "B", "C");
        final Triple<String, String, String> triple3 = Triple.of("X", "Y", "Z");

        // Test that equal objects have the same hash code
        assertEquals(triple1.hashCode(), triple2.hashCode());

        // Test that different objects have different hash codes
        assertNotEquals(triple1.hashCode(), triple3.hashCode());
    }

    @Test
    public void testHashCodeWithNulls() {
        final Triple<String, String, String> triple1 = Triple.of(null, "B", "C");
        final Triple<String, String, String> triple2 = Triple.of("A", null, "C");
        final Triple<String, String, String> triple3 = Triple.of("A", "B", null);

        // Test hash code generation with null values
        assertEquals(Objects.hashCode(null) ^ Objects.hashCode("B") ^ Objects.hashCode("C"), triple1.hashCode());
        assertEquals(Objects.hashCode("A") ^ Objects.hashCode(null) ^ Objects.hashCode("C"), triple2.hashCode());
        assertEquals(Objects.hashCode("A") ^ Objects.hashCode("B") ^ Objects.hashCode(null), triple3.hashCode());
    }
}