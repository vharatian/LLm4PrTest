package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashFunctionIdentityLLM_Test {

    // Mock implementation of HashFunctionIdentity for testing
    private static class MockHashFunctionIdentity implements HashFunctionIdentity {
        private final String name;
        private final ProcessType processType;
        private final String provider;
        private final long signature;
        private final Signedness signedness;

        MockHashFunctionIdentity(String name, ProcessType processType, String provider, long signature, Signedness signedness) {
            this.name = name;
            this.processType = processType;
            this.provider = provider;
            this.signature = signature;
            this.signedness = signedness;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public ProcessType getProcessType() {
            return processType;
        }

        @Override
        public String getProvider() {
            return provider;
        }

        @Override
        public long getSignature() {
            return signature;
        }

        @Override
        public Signedness getSignedness() {
            return signedness;
        }
    }

    @Test
    public void testSignednessEnumDocumentation() {
        // Test SIGNED enum
        HashFunctionIdentity.Signedness signed = HashFunctionIdentity.Signedness.SIGNED;
        assertNotNull(signed);
        assertEquals("SIGNED", signed.name());

        // Test UNSIGNED enum
        HashFunctionIdentity.Signedness unsigned = HashFunctionIdentity.Signedness.UNSIGNED;
        assertNotNull(unsigned);
        assertEquals("UNSIGNED", unsigned.name());
    }

    @Test
    public void testAsCommonString() {
        HashFunctionIdentity identity = new MockHashFunctionIdentity("TestName", HashFunctionIdentity.ProcessType.CYCLIC, "TestProvider", 12345L, HashFunctionIdentity.Signedness.SIGNED);
        String commonString = HashFunctionIdentity.asCommonString(identity);
        assertEquals("TestName-SIGNED-CYCLIC", commonString);
    }

    @Test
    public void testPrepareSignatureBuffer() {
        HashFunctionIdentity identity = new MockHashFunctionIdentity("TestName", HashFunctionIdentity.ProcessType.ITERATIVE, "TestProvider", 12345L, HashFunctionIdentity.Signedness.UNSIGNED);
        byte[] signatureBuffer = HashFunctionIdentity.prepareSignatureBuffer(identity);
        assertArrayEquals("TESTNAME-UNSIGNED-ITERATIVE".getBytes(StandardCharsets.UTF_8), signatureBuffer);
    }
}