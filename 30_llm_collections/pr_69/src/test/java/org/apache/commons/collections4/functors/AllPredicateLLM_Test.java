package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import static org.apache.commons.collections4.functors.AllPredicate.allPredicate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("boxing")
public class AllPredicateLLM_Test extends AbstractAnyAllOnePredicateTest<Integer> {

    public AllPredicateTest2() {
        super(42);
    }

    @Override
    protected final Predicate<Integer> getPredicateInstance(final Predicate<? super Integer> ... predicates) {
        return AllPredicate.allPredicate(predicates);
    }

    @Override
    protected final Predicate<Integer> getPredicateInstance(final Collection<Predicate<Integer>> predicates) {
        return AllPredicate.allPredicate(predicates);
    }

    @SuppressWarnings({"unchecked"})
    @Test
    public void testValidateMethodCalledWithArray() {
        Predicate<Integer> mockPredicate = createMockPredicate(true);
        Predicate<Integer>[] predicates = new Predicate[] { mockPredicate };
        Predicate<Integer> allPredicate = getPredicateInstance(predicates);
        assertTrue("validate method not called correctly with array", allPredicate.evaluate(getTestValue()));
    }

    @Test
    public void testValidateMethodCalledWithCollection() {
        Predicate<Integer> mockPredicate = createMockPredicate(true);
        Collection<Predicate<Integer>> predicates = Collections.singletonList(mockPredicate);
        Predicate<Integer> allPredicate = getPredicateInstance(predicates);
        assertTrue("validate method not called correctly with collection", allPredicate.evaluate(getTestValue()));
    }
}