package org.apache.commons.collections4;

import static org.junit.Assert.*;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.bag.PredicatedSortedBag;
import org.apache.commons.collections4.bag.SynchronizedBag;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.collections4.bag.TransformedBag;
import org.apache.commons.collections4.bag.TransformedSortedBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
import org.apache.commons.collections4.bag.UnmodifiableSortedBag;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.Test;

public class BagUtilsLLM_Test {

    protected Predicate<Object> truePredicate = TruePredicate.truePredicate();
    protected Transformer<Object, Object> nopTransformer = TransformerUtils.nopTransformer();

    @Test
    public void testPrivateConstructor() {
        try {
            BagUtils.class.getDeclaredConstructor().setAccessible(true);
            BagUtils.class.getDeclaredConstructor().newInstance();
            fail("Expecting UnsupportedOperationException for private constructor.");
        } catch (UnsupportedOperationException ex) {
            // expected
        } catch (Exception ex) {
            fail("Unexpected exception: " + ex);
        }
    }
}