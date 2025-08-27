package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;

public class HashFunctionIdentityLLM_Test {

    private static class MockHashFunctionIdentity implements HashFunctionIdentity {
        private final String name;
        private final String provider;
        private final Signedness signedness;
        private final ProcessType processType;
        private final long signature;

        MockHashFunctionIdentity(String name, String provider, Signedness signedness, ProcessType processType, long signature) {
            this.name = name;
            this.provider = provider;
            this.signedness = signedness;
            this.processType = processType;
            this.signature = signature;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getProvider() {
            return provider;
        }

        @Override
        public Signedness getSignedness() {
            return signedness;
        }

        @Override
        public ProcessType getProcessType() {
            return processType;
        }

        @Override
        public long getSignature() {
            return signature;
        }
    }

    @Test
    public void testCommonComparator() {
        HashFunctionIdentity identity1 = new MockHashFunctionIdentity("MD5", "Provider1", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC, 123L);
        HashFunctionIdentity identity2 = new MockHashFunctionIdentity("md5", "Provider2", HashFunctionIdentity.Signedness.UNSIGNED, HashFunctionIdentity.ProcessType.ITERATIVE, 456L);

        Comparator<HashFunctionIdentity> comparator = HashFunctionIdentity.COMMON_COMPARATOR;
        assertEquals(0, comparator.compare(identity1, identity2));
    }

    @Test
    public void testDeepComparator() {
        HashFunctionIdentity identity1 = new MockHashFunctionIdentity("MD5", "Provider1", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC, 123L);
        HashFunctionIdentity identity2 = new MockHashFunctionIdentity("md5", "provider1", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC, 123L);

        Comparator<HashFunctionIdentity> comparator = HashFunctionIdentity.DEEP_COMPARATOR;
        assertEquals(0, comparator.compare(identity1, identity2));
    }

    @Test
    public void testAsCommonString() {
        HashFunctionIdentity identity = new MockHashFunctionIdentity("MD5", "Provider1", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC, 123L);
        String expected = "MD5-SIGNED-CYCLIC";
        assertEquals(expected, HashFunctionIdentity.asCommonString(identity));
    }

    @Test
    public void testPrepareSignatureBuffer() {
        HashFunctionIdentity identity = new MockHashFunctionIdentity("MD5", "Provider1", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC, 123L);
        byte[] expected = "MD5-SIGNED-CYCLIC".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, HashFunctionIdentity.prepareSignatureBuffer(identity));
    }
}