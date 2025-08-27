package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayCountingBloomFilterLLM_Test extends AbstractCountingBloomFilterTest<ArrayCountingBloomFilter> {

    @Override
    protected ArrayCountingBloomFilter createEmptyFilter(final Shape shape) {
        return new ArrayCountingBloomFilter(shape);
    }

    @Test
    public void testGetMaxCell() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        assertEquals(Integer.MAX_VALUE, filter.getMaxCell());
    }

    @Test
    public void testAddWithIndexOutOfBounds() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            filter.add(101, 1);
        });
        assertTrue(exception.getMessage().contains("Filter only accepts values in the [0,100) range"));
    }

    @Test
    public void testSubtractWithIndexOutOfBounds() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            filter.subtract(101, 1);
        });
        assertTrue(exception.getMessage().contains("Filter only accepts values in the [0,100) range"));
    }

    @Test
    public void testGetMaxInsert() {
        Shape shape = new Shape(3, 100);
        ArrayCountingBloomFilter filter = createEmptyFilter(shape);
        filter.add(0, 10);
        filter.add(1, 20);
        filter.add(2, 30);

        CellProducer cellProducer = (consumer) -> {
            consumer.test(0, 5);
            consumer.test(1, 4);
            consumer.test(2, 3);
            return true;
        };

        assertEquals(2, filter.getMaxInsert(cellProducer));
    }
}