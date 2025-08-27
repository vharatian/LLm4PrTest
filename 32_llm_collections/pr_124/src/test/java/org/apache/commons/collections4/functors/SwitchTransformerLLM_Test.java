package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwitchTransformerLLM_Test {

    @Test(expected = NullPointerException.class)
    public void testSwitchTransformerWithNullPredicatesArray() {
        Transformer<Object, Object> transformer = SwitchTransformer.switchTransformer(null, new Transformer[]{});
    }

    @Test(expected = NullPointerException.class)
    public void testSwitchTransformerWithNullTransformersArray() {
        Transformer<Object, Object> transformer = SwitchTransformer.switchTransformer(new Predicate[]{}, null);
    }

    @Test(expected = NullPointerException.class)
    public void testSwitchTransformerWithNullElementInPredicatesArray() {
        Transformer<Object, Object> transformer = SwitchTransformer.switchTransformer(new Predicate[]{null}, new Transformer[]{input -> input});
    }

    @Test(expected = NullPointerException.class)
    public void testSwitchTransformerWithNullElementInTransformersArray() {
        Transformer<Object, Object> transformer = SwitchTransformer.switchTransformer(new Predicate[]{input -> true}, new Transformer[]{null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSwitchTransformerWithDifferentArraySizes() {
        Transformer<Object, Object> transformer = SwitchTransformer.switchTransformer(new Predicate[]{input -> true}, new Transformer[]{input -> input, input -> input});
    }
}