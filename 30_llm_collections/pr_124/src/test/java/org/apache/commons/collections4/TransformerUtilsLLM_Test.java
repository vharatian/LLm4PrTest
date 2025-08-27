package org.apache.commons.collections4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TransformerUtilsLLM_Test {

    @Test
    public void testSwitchTransformerWithNullArrays() {
        try {
            TransformerUtils.switchTransformer((Predicate[]) null, (Transformer[]) null);
            fail("Expected NullPointerException for null arrays");
        } catch (NullPointerException ex) {
            // Expected exception
        }
    }

    @Test
    public void testSwitchTransformerWithDifferentSizedArrays() {
        try {
            TransformerUtils.switchTransformer(
                new Predicate[] { TruePredicate.truePredicate() },
                new Transformer[] { TransformerUtils.constantTransformer("A"), TransformerUtils.constantTransformer("B") }
            );
            fail("Expected IllegalArgumentException for arrays of different sizes");
        } catch (IllegalArgumentException ex) {
            // Expected exception
        }
    }

    @Test
    public void testSwitchTransformerWithNullElementsInArrays() {
        try {
            TransformerUtils.switchTransformer(
                new Predicate[] { null },
                new Transformer[] { TransformerUtils.constantTransformer("A") }
            );
            fail("Expected NullPointerException for null elements in arrays");
        } catch (NullPointerException ex) {
            // Expected exception
        }

        try {
            TransformerUtils.switchTransformer(
                new Predicate[] { TruePredicate.truePredicate() },
                new Transformer[] { null }
            );
            fail("Expected NullPointerException for null elements in arrays");
        } catch (NullPointerException ex) {
            // Expected exception
        }
    }
}