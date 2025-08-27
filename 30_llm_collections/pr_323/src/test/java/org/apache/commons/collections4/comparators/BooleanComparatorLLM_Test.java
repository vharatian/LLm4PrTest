package org.apache.commons.collections4.comparators;

import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanComparatorLLM_Test extends AbstractComparatorTest<Boolean> {

    public BooleanComparatorTest2() {
        super(BooleanComparatorTest2.class.getSimpleName());
    }

    @Override
    public Comparator<Boolean> makeObject() {
        return new BooleanComparator();
    }

    @Override
    public List<Boolean> getComparableObjectsOrdered() {
        return new ArrayList<>(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    /**
     * Test to ensure that the equals method works correctly after the documentation change.
     */
    @Test
    public void testEqualsMethodAfterDocChange() {
        BooleanComparator comparator1 = new BooleanComparator(true);
        BooleanComparator comparator2 = new BooleanComparator(true);
        BooleanComparator comparator3 = new BooleanComparator(false);

        assertEquals(comparator1, comparator2, "Comparators with the same trueFirst value should be equal");
        assertNotEquals(comparator1, comparator3, "Comparators with different trueFirst values should not be equal");
    }
}