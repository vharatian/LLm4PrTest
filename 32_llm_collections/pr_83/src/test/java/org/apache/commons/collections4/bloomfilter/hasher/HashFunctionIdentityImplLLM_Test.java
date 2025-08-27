package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashFunctionIdentityImplLLM_Test {

    @Test
    public void testConstructorWithIdentity() {
        HashFunctionIdentity identity = new HashFunctionIdentity() {
            @Override
            public String getName() {
                return "testName";
            }

            @Override
            public String getProvider() {
                return "testProvider";
            }

            @Override
            public Signedness getSignedness() {
                return Signedness.SIGNED;
            }

            @Override
            public ProcessType getProcessType() {
                return ProcessType.CYCLIC;
            }

            @Override
            public long getSignature() {
                return 12345L;
            }
        };

        HashFunctionIdentityImpl impl = new HashFunctionIdentityImpl(identity);

        assertEquals("testName", impl.getName());
        assertEquals("testProvider", impl.getProvider());
        assertEquals(Signedness.SIGNED, impl.getSignedness());
        assertEquals(ProcessType.CYCLIC, impl.getProcessType());
        assertEquals(12345L, impl.getSignature());
    }

    @Test
    public void testConstructorWithComponents() {
        HashFunctionIdentityImpl impl = new HashFunctionIdentityImpl("testProvider", "testName", Signedness.SIGNED, ProcessType.CYCLIC, 12345L);

        assertEquals("testName", impl.getName());
        assertEquals("testProvider", impl.getProvider());
        assertEquals(Signedness.SIGNED, impl.getSignedness());
        assertEquals(ProcessType.CYCLIC, impl.getProcessType());
        assertEquals(12345L, impl.getSignature());
    }
}