package org.apache.commons.collections4.bloomfilter.hasher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity.ProcessType;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity.Signedness;
import org.junit.Test;

public class HashFunctionValidatorLLM_Test {

    @Test
    public void testHashFunctionValidatorPublicAccess() {
        final HashFunctionIdentityImpl impl1 = new HashFunctionIdentityImpl("Testing Suite", "impl1", Signedness.SIGNED,
                ProcessType.CYCLIC, 300L);
        final HashFunctionIdentityImpl impl2 = new HashFunctionIdentityImpl("Testing Suite", "impl2", Signedness.SIGNED,
                ProcessType.CYCLIC, 300L);

        // Test public access to areEqual method
        assertTrue(HashFunctionValidator.areEqual(impl1, impl1));
        assertFalse(HashFunctionValidator.areEqual(impl1, impl2));

        // Test public access to checkAreEqual method
        try {
            HashFunctionValidator.checkAreEqual(impl1, impl1);
        } catch (IllegalArgumentException e) {
            assertFalse("Exception should not be thrown for equal hash functions", true);
        }

        try {
            HashFunctionValidator.checkAreEqual(impl1, impl2);
            assertFalse("Exception should be thrown for non-equal hash functions", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Exception should be thrown for non-equal hash functions", true);
        }
    }
}