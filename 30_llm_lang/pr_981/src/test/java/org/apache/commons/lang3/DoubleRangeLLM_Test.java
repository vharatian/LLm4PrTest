package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DoubleRangeLLM_Test extends AbstractLangTest {

    @Test
    public void testConstructorWithNullComparator() {
        // The diff file indicates the removal of the comparator parameter from the constructor
        // This test ensures that the constructor works correctly without the comparator parameter
        DoubleRange range = DoubleRange.of(1.0, 2.0);
        assertEquals(1.0, range.getMinimum());
        assertEquals(2.0, range.getMaximum());
    }

    @Test
    public void testConstructorWithNullValues() {
        // Ensuring that the constructor throws NullPointerException when null values are passed
        assertThrows(NullPointerException.class, () -> DoubleRange.of(null, 2.0));
        assertThrows(NullPointerException.class, () -> DoubleRange.of(1.0, null));
        assertThrows(NullPointerException.class, () -> DoubleRange.of(null, null));
    }
}