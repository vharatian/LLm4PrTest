package org.apache.commons.collections4.comparators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ComparatorChainLLM_Test extends AbstractComparatorTest<ComparatorChainTest2.PseudoRow> {

    public ComparatorChainTest2() {
        super(ComparatorChainTest2.class.getSimpleName());
    }

    @Override
    public Comparator<PseudoRow> makeObject() {
        final ComparatorChain<PseudoRow> chain = new ComparatorChain<>(new ColumnComparator(0));
        chain.addComparator(new ColumnComparator(1), true);
        chain.addComparator(new ColumnComparator(2), false);
        return chain;
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Test
    public void testNoopComparatorChain() {
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        final Integer i1 = 4;
        final Integer i2 = 6;
        chain.addComparator(new ComparableComparator<>());
        final int correctValue = i1.compareTo(i2);
        assertEquals("Comparison returns the right order", chain.compare(i1, i2), correctValue);
    }

    @Test
    public void testBadNoopComparatorChain() {
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        final Integer i1 = 4;
        final Integer i2 = 6;
        assertThrows(UnsupportedOperationException.class, () -> chain.compare(i1, i2), "An exception should be thrown when a chain contains zero comparators.");
    }

    @Test
    public void testListComparatorChain() {
        final List<Comparator<Integer>> list = new LinkedList<>();
        list.add(new ComparableComparator<>());
        final ComparatorChain<Integer> chain = new ComparatorChain<>(list);
        final Integer i1 = 4;
        final Integer i2 = 6;
        final int correctValue = i1.compareTo(i2);
        assertEquals("Comparison returns the right order", chain.compare(i1, i2), correctValue);
    }

    @Test
    public void testBadListComparatorChain() {
        final List<Comparator<Integer>> list = new LinkedList<>();
        final ComparatorChain<Integer> chain = new ComparatorChain<>(list);
        final Integer i1 = 4;
        final Integer i2 = 6;
        assertThrows(UnsupportedOperationException.class, () -> chain.compare(i1, i2));
    }

    @Test
    public void testComparatorChainOnMinvaluedCompatator() {
        final ComparatorChain<Integer> chain = new ComparatorChain<>();
        chain.addComparator((a, b) -> {
            final int result = a.compareTo(b);
            if (result < 0) {
                return Integer.MIN_VALUE;
            }
            if (result > 0) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }, true);
        assertTrue(chain.compare(4, 5) > 0);
        assertTrue(chain.compare(5, 4) < 0);
        assertEquals(0, chain.compare(4, 4));
    }

    @Override
    public List<PseudoRow> getComparableObjectsOrdered() {
        return new LinkedList<>(Arrays.asList(new PseudoRow(1, 2, 3), new PseudoRow(2, 3, 5),
                new PseudoRow(2, 2, 4), new PseudoRow(2, 2, 8), new PseudoRow(3, 1, 0),
                new PseudoRow(4, 4, 4), new PseudoRow(4, 4, 7)));
    }

    public static class PseudoRow implements Serializable {
        private static final long serialVersionUID = 8085570439751032499L;
        public int[] cols = new int[3];

        public PseudoRow(final int col1, final int col2, final int col3) {
            cols[0] = col1;
            cols[1] = col2;
            cols[2] = col3;
        }

        public int getColumn(final int colIndex) {
            return cols[colIndex];
        }

        @Override
        public String toString() {
            return "[" + cols[0] + "," + cols[1] + "," + cols[2] + "]";
        }

        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof PseudoRow)) {
                return false;
            }
            final PseudoRow row = (PseudoRow) o;
            return getColumn(0) == row.getColumn(0) && getColumn(1) == row.getColumn(1) && getColumn(2) == row.getColumn(2);
        }
    }

    public static class ColumnComparator implements Comparator<PseudoRow>, Serializable {
        private static final long serialVersionUID = -2284880866328872105L;
        protected int colIndex = 0;

        public ColumnComparator(final int colIndex) {
            this.colIndex = colIndex;
        }

        @Override
        public int compare(final PseudoRow o1, final PseudoRow o2) {
            return Integer.compare(o1.getColumn(colIndex), o2.getColumn(colIndex));
        }

        @Override
        public int hashCode() {
            return colIndex;
        }

        @Override
        public boolean equals(final Object that) {
            return that instanceof ColumnComparator && colIndex == ((ColumnComparator) that).colIndex;
        }
    }
}