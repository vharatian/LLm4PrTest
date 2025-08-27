package org.apache.commons.collections4.comparators;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.TransformerUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testEqualsWithNulls() {
        final Transformer<String, String> t1 = TransformerUtils.nopTransformer();
        final TransformingComparator<String, String> comp1 = new TransformingComparator<>(t1);
        final TransformingComparator<String, String> comp2 = new TransformingComparator<>(t1, null);

        // Test equality with one comparator having null decorated comparator
        assertFalse(comp1.equals(comp2));
        assertFalse(comp2.equals(comp1));
    }

    @Test
    public void testEqualsWithDifferentTransformers() {
        final Transformer<String, String> t1 = TransformerUtils.nopTransformer();
        final Transformer<String, String> t2 = input -> input.toUpperCase();
        final TransformingComparator<String, String> comp1 = new TransformingComparator<>(t1);
        final TransformingComparator<String, String> comp2 = new TransformingComparator<>(t2);

        // Test equality with different transformers
        assertFalse(comp1.equals(comp2));
        assertFalse(comp2.equals(comp1));
    }

    @Test
    public void testEqualsWithObjectsEquals() {
        final Transformer<String, String> t1 = TransformerUtils.nopTransformer();
        final TransformingComparator<String, String> comp1 = new TransformingComparator<>(t1);
        final TransformingComparator<String, String> comp2 = new TransformingComparator<>(t1);

        // Test equality using Objects.equals
        assertTrue(Objects.equals(comp1, comp2));
        assertTrue(Objects.equals(comp2, comp1));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }
}