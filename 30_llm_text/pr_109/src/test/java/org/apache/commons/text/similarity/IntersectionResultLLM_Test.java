package org.apache.commons.text.similarity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntersectionResultLLM_Test {

    @Test
    public void testConstructorValidInput() {
        IntersectionResult result = new IntersectionResult(5, 3, 2);
        assertEquals(5, result.getSizeA());
        assertEquals(3, result.getSizeB());
        assertEquals(2, result.getIntersection());
    }

    @Test
    public void testConstructorNegativeSizeA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new IntersectionResult(-1, 3, 2);
        });
        assertEquals("Set size |A| is not positive: -1", exception.getMessage());
    }

    @Test
    public void testConstructorNegativeSizeB() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new IntersectionResult(5, -1, 2);
        });
        assertEquals("Set size |B| is not positive: -1", exception.getMessage());
    }

    @Test
    public void testConstructorNegativeIntersection() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new IntersectionResult(5, 3, -1);
        });
        assertEquals("Invalid intersection of |A| and |B|: -1", exception.getMessage());
    }

    @Test
    public void testConstructorIntersectionGreaterThanMinSize() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new IntersectionResult(5, 3, 4);
        });
        assertEquals("Invalid intersection of |A| and |B|: 4", exception.getMessage());
    }

    @Test
    public void testEqualsAndHashCode() {
        IntersectionResult result1 = new IntersectionResult(5, 3, 2);
        IntersectionResult result2 = new IntersectionResult(5, 3, 2);
        IntersectionResult result3 = new IntersectionResult(4, 3, 2);

        assertEquals(result1, result2);
        assertNotEquals(result1, result3);
        assertEquals(result1.hashCode(), result2.hashCode());
        assertNotEquals(result1.hashCode(), result3.hashCode());
    }

    @Test
    public void testToString() {
        IntersectionResult result = new IntersectionResult(5, 3, 2);
        assertEquals("Size A: 5, Size B: 3, Intersection: 2", result.toString());
    }
}