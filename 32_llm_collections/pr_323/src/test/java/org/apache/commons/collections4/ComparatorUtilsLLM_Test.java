package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class ComparatorUtilsLLM_Test {

    @Test
    public void min() {
        final Comparator<Integer> reversed =
            ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());
        assertEquals(Integer.valueOf(1), ComparatorUtils.min(1, 10, null));
        assertEquals(Integer.valueOf(-10), ComparatorUtils.min(10, -10, null));
        assertEquals(Integer.valueOf(10), ComparatorUtils.min(1, 10, reversed));
        assertEquals(Integer.valueOf(10), ComparatorUtils.min(10, -10, reversed));
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> ComparatorUtils.min(1, null, null),
                "expecting NullPointerException"),
            () -> assertThrows(NullPointerException.class, () -> ComparatorUtils.min(null, 10, null),
                "expecting NullPointerException")
        );
    }

    @Test
    public void max() {
        final Comparator<Integer> reversed =
            ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());
        assertEquals(Integer.valueOf(10), ComparatorUtils.max(1, 10, null));
        assertEquals(Integer.valueOf(10), ComparatorUtils.max(10, -10, null));
        assertEquals(Integer.valueOf(1), ComparatorUtils.max(1, 10, reversed));
        assertEquals(Integer.valueOf(-10), ComparatorUtils.max(10, -10, reversed));
        assertAll(
            () -> assertThrows(NullPointerException.class, () -> ComparatorUtils.max(1, null, null),
                "expecting NullPointerException"),
            () -> assertThrows(NullPointerException.class, () -> ComparatorUtils.max(null, 10, null),
                "expecting NullPointerException")
        );
    }
}