package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerRangeLLM_Test extends AbstractLangTest {

    private IntegerRange range1;

    @BeforeEach
    public void setUp() {
        range1 = IntegerRange.of(10, 20);
    }

    @Test
    public void testConstructorWithNullComparator() {
        // Ensure that the constructor without the comparator parameter works as expected
        IntegerRange range = new IntegerRange(10, 20);
        assertEquals(10, range.getMinimum());
        assertEquals(20, range.getMaximum());
    }

    @Test
    public void testConstructorWithNullValues() {
        // Ensure that the constructor throws NullPointerException when null values are passed
        assertThrows(NullPointerException.class, () -> new IntegerRange(null, 20));
        assertThrows(NullPointerException.class, () -> new IntegerRange(10, null));
    }
}