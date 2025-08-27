package org.apache.commons.collections4;

import static org.apache.commons.collections4.functors.NullPredicate.nullPredicate;
import static org.apache.commons.collections4.functors.TruePredicate.truePredicate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.functors.AbstractPredicateTest;
import org.apache.commons.collections4.functors.AllPredicate;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.functors.ExceptionPredicate;
import org.apache.commons.collections4.functors.FalsePredicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.functors.NullPredicate;
import org.apache.commons.collections4.functors.TruePredicate;
import org.junit.jupiter.api.Test;

@SuppressWarnings("boxing")
public class PredicateUtilsLLM_Test extends AbstractPredicateTest {

    @Test
    public void testAsPredicateTransformer() {
        assertFalse(PredicateUtils.asPredicate(TransformerUtils.nopTransformer()).evaluate(false));
        assertTrue(PredicateUtils.asPredicate(TransformerUtils.nopTransformer()).evaluate(true));
    }

    @Test
    public void testAsPredicateTransformerEx1() {
        assertThrows(NullPointerException.class, () -> PredicateUtils.asPredicate(null));
    }

    @Test
    public void testAsPredicateTransformerEx2() {
        assertThrows(FunctorException.class, () -> PredicateUtils.asPredicate(TransformerUtils.nopTransformer()).evaluate(null));
    }
}