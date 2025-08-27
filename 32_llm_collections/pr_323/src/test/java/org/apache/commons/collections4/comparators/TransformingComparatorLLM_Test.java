package org.apache.commons.collections4.comparators;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformingComparatorLLM_Test extends AbstractComparatorTest<Integer> {

    public TransformingComparatorTest2() {
        super(TransformingComparatorTest2.class.getSimpleName());
    }

    @Override
    public Comparator<Integer> makeObject() {
        final Comparator<String> decorated = new ComparableComparator<>();
        return ComparatorUtils.transformedComparator(decorated, TransformerUtils.<Integer>stringValueTransformer());
    }

    @Override
    @SuppressWarnings("boxing")
    public List<Integer> getComparableObjectsOrdered() {
        final List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        return list;
    }

    @Test
    public void testEquals() {
        final Transformer<String, String> t1 = TransformerUtils.nopTransformer();
        final TransformingComparator<String, String> comp1 = new TransformingComparator<>(t1);
        final TransformingComparator<String, String> comp2 = new TransformingComparator<>(t1, comp1);
        assertTrue("Contract failed: equals-hashcode",
            comp1.equals(comp2) ? comp1.hashCode() == comp2.hashCode() : true);
        assertTrue("Contract failed: equals-hashcode",
            comp2.equals(comp1) ? comp2.hashCode() == comp1.hashCode() : true);
    }

    @Test
    public void testEqualsWithDifferentComparator() {
        final Transformer<String, String> t1 = TransformerUtils.nopTransformer();
        final TransformingComparator<String, String> comp1 = new TransformingComparator<>(t1);
        final TransformingComparator<String, String> comp2 = new TransformingComparator<>(t1, ComparatorUtils.NATURAL_COMPARATOR);
        assertTrue("Contract failed: equals-hashcode",
            comp1.equals(comp2) ? comp1.hashCode() == comp2.hashCode() : true);
        assertTrue("Contract failed: equals-hashcode",
            comp2.equals(comp1) ? comp2.hashCode() == comp1.hashCode() : true);
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}