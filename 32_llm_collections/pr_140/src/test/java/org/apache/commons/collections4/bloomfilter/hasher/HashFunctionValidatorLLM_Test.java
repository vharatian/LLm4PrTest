package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;
import java.util.Objects;

class HashFunctionValidatorLLM_Test {

    static class MockHashFunctionIdentity implements HashFunctionIdentity {
        private final boolean signedness;
        private final String processType;
        private final String name;

        MockHashFunctionIdentity(boolean signedness, String processType, String name) {
            this.signedness = signedness;
            this.processType = processType;
            this.name = name;
        }

        @Override
        public boolean getSignedness() {
            return signedness;
        }

        @Override
        public String getProcessType() {
            return processType;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    @Test
    void testHash() {
        HashFunctionIdentity identity1 = new MockHashFunctionIdentity(true, "type1", "Name");
        HashFunctionIdentity identity2 = new MockHashFunctionIdentity(true, "type1", "name");

        int hash1 = HashFunctionValidator.hash(identity1);
        int hash2 = HashFunctionValidator.hash(identity2);

        assertEquals(hash1, hash2, "Hash codes should be equal for case-insensitive names");
    }

    @Test
    void testAreEqual() {
        HashFunctionIdentity identity1 = new MockHashFunctionIdentity(true, "type1", "Name");
        HashFunctionIdentity identity2 = new MockHashFunctionIdentity(true, "type1", "name");

        assertTrue(HashFunctionValidator.areEqual(identity1, identity2), "Identities should be equal for case-insensitive names");
    }

    @Test
    void testCheckAreEqual() {
        HashFunctionIdentity identity1 = new MockHashFunctionIdentity(true, "type1", "Name");
        HashFunctionIdentity identity2 = new MockHashFunctionIdentity(true, "type1", "name");

        assertDoesNotThrow(() -> HashFunctionValidator.checkAreEqual(identity1, identity2), "No exception should be thrown for equal identities");

        HashFunctionIdentity identity3 = new MockHashFunctionIdentity(false, "type1", "name");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> HashFunctionValidator.checkAreEqual(identity1, identity3));
        assertTrue(exception.getMessage().contains("Hash functions are not equal"), "Exception message should indicate inequality");
    }
}