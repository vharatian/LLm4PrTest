package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class HashFunctionIdentityLLM_Test {

    private static class MockHashFunctionIdentity implements HashFunctionIdentity {
        @Override
        public String getName() {
            return "mockName";
        }

        @Override
        public ProcessType getProcessType() {
            return ProcessType.CYCLIC;
        }

        @Override
        public String getProvider() {
            return "mockProvider";
        }

        @Override
        public long getSignature() {
            return 123456789L;
        }

        @Override
        public Signedness getSignedness() {
            return Signedness.SIGNED;
        }
    }

    @Test
    public void testPrepareSignatureBuffer() {
        HashFunctionIdentity identity = new MockHashFunctionIdentity();
        byte[] expected = "MOCKNAME-SIGNED-CYCLIC".getBytes(StandardCharsets.UTF_8);
        byte[] actual = HashFunctionIdentity.prepareSignatureBuffer(identity);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testAsCommonString() {
        HashFunctionIdentity identity = new MockHashFunctionIdentity();
        String expected = "mockName-SIGNED-CYCLIC";
        String actual = HashFunctionIdentity.asCommonString(identity);
        assertEquals(expected, actual);
    }
}