package org.apache.commons.collections4.bloomfilter.hasher;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class HashFunctionIdentityLLM_Test {

    private static class TestHashFunctionIdentity implements HashFunctionIdentity {
        @Override
        public String getName() {
            return "testName";
        }

        @Override
        public ProcessType getProcessType() {
            return ProcessType.CYCLIC;
        }

        @Override
        public String getProvider() {
            return "testProvider";
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
    public void testAsCommonString() {
        HashFunctionIdentity identity = new TestHashFunctionIdentity();
        String expected = "testName-SIGNED-CYCLIC";
        String actual = HashFunctionIdentity.asCommonString(identity);
        assertEquals(expected, actual);
    }

    @Test
    public void testPrepareSignatureBuffer() {
        HashFunctionIdentity identity = new TestHashFunctionIdentity();
        byte[] expected = "TESTNAME-SIGNED-CYCLIC".getBytes(StandardCharsets.UTF_8);
        byte[] actual = HashFunctionIdentity.prepareSignatureBuffer(identity);
        assertArrayEquals(expected, actual);
    }
}