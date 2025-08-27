package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.function.IntPredicate;

public class CellProducerLLM_Test {

    @Test
    public void testForEachCell() {
        CellProducer producer = CellProducer.from(() -> new int[]{1, 2, 2, 3, 3, 3});
        assertTrue(producer.forEachCell((index, count) -> {
            if (index == 1) {
                assertEquals(1, count);
            } else if (index == 2) {
                assertEquals(2, count);
            } else if (index == 3) {
                assertEquals(3, count);
            } else {
                fail("Unexpected index: " + index);
            }
            return true;
        }));
    }

    @Test
    public void testForEachIndex() {
        CellProducer producer = CellProducer.from(() -> new int[]{1, 2, 2, 3, 3, 3});
        assertTrue(producer.forEachIndex(index -> index >= 1 && index <= 3));
    }

    @Test
    public void testAsIndexArray() {
        CellProducer producer = CellProducer.from(() -> new int[]{1, 2, 2, 3, 3, 3});
        int[] indices = producer.asIndexArray();
        assertArrayEquals(new int[]{1, 2, 3}, indices);
    }

    @Test
    public void testCellConsumer() {
        CellProducer.CellConsumer consumer = (index, count) -> {
            assertTrue(index >= 1 && index <= 3);
            assertTrue(count >= 1 && count <= 3);
            return true;
        };
        assertTrue(consumer.test(1, 1));
        assertTrue(consumer.test(2, 2));
        assertTrue(consumer.test(3, 3));
    }

    @Test
    public void testCounterCell() {
        CellProducer producer = CellProducer.from(() -> new int[]{1, 2, 2, 3, 3, 3});
        producer.forEachCell((index, count) -> {
            CellProducer.CounterCell cell1 = producer.new CounterCell(index, count);
            CellProducer.CounterCell cell2 = producer.new CounterCell(index, count);
            assertEquals(0, cell1.compareTo(cell2));
            return true;
        });
    }
}