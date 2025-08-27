package org.apache.commons.collections4.comparators;

import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotEquals;

public class BooleanComparatorLLM_Test extends AbstractComparatorTest<Boolean> {

    public BooleanComparatorTest2(final String testName) {
        super(testName);
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

    @Test
    public void testFinalTrueFirstField() {
        BooleanComparator trueFirstComparator = new BooleanComparator(true);
        BooleanComparator falseFirstComparator = new BooleanComparator(false);

        // Ensure the trueFirst field is correctly set and final
        assertTrue(trueFirstComparator.sortsTrueFirst());
        assertTrue(!falseFirstComparator.sortsTrueFirst());
    }

    @Test
    public void testFinalTrueFirstFieldInStaticFactoryMethods() {
        BooleanComparator trueFirstComparator = BooleanComparator.getTrueFirstComparator();
        BooleanComparator falseFirstComparator = BooleanComparator.getFalseFirstComparator();

        // Ensure the trueFirst field is correctly set and final in static factory methods
        assertTrue(trueFirstComparator.sortsTrueFirst());
        assertTrue(!falseFirstComparator.sortsTrueFirst());
    }
}