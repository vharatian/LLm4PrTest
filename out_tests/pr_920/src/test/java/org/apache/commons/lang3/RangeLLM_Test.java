package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("boxing")
public class RangeLLM_Test extends AbstractLangTest {

    private Range<Byte> byteRange;
    private Range<Byte> byteRange2;
    private Range<Byte> byteRange3;
    private Range<Double> doubleRange;
    private Range<Float> floatRange;
    private Range<Integer> intRange;
    private Range<Long> longRange;

    @BeforeEach
    public void setUp() {
        byteRange = Range.between((byte) 0, (byte) 5);
        byteRange2 = Range.between((byte) 0, (byte) 5);
        byteRange3 = Range.between((byte) 0, (byte) 10);
        intRange = Range.between(10, 20);
        longRange = Range.between(10L, 20L);
        floatRange = Range.between((float) 10, (float) 20);
        doubleRange = Range.between((double) 10, (double) 20);
    }

    @Test
    public void testComparableComparatorJavadoc() {
        // This test ensures that the ComparableComparator's compare method works as documented
        Comparator<Comparable> comparator = Range.ComparableComparator.INSTANCE;
        assertEquals(0, comparator.compare(10, 10), "ComparableComparator should return 0 for equal values");
        assertTrue(comparator.compare(5, 10) < 0, "ComparableComparator should return negative for lesser value");
        assertTrue(comparator.compare(10, 5) > 0, "ComparableComparator should return positive for greater value");
    }
}