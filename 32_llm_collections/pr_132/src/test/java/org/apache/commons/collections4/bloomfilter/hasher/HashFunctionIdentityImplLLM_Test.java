package org.apache.commons.collections4.bloomfilter.hasher;

import static org.junit.Assert.assertEquals;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity.Signedness;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity.ProcessType;
import org.junit.Test;

public class HashFunctionIdentityImplLLM_Test {

    @Test
    public void testLicenseHeaderFormatting() {
        // This test is to ensure that the license header formatting changes do not affect functionality.
        final HashFunctionIdentityImpl impl = new HashFunctionIdentityImpl("Provider", "NAME", Signedness.UNSIGNED, ProcessType.ITERATIVE, -2L);
        assertEquals("NAME", impl.getName());
        assertEquals("Provider", impl.getProvider());
        assertEquals(Signedness.UNSIGNED, impl.getSignedness());
        assertEquals(ProcessType.ITERATIVE, impl.getProcessType());
        assertEquals(-2L, impl.getSignature());
    }
}