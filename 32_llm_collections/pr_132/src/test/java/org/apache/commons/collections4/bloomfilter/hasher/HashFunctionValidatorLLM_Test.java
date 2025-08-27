package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashFunctionValidatorLLM_Test {

    @Test
    void testAreEqual() {
        HashFunctionIdentity identity1 = new HashFunctionIdentity("name", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC);
        HashFunctionIdentity identity2 = new HashFunctionIdentity("name", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC);
        assertTrue(HashFunctionValidator.areEqual(identity1, identity2));

        HashFunctionIdentity identity3 = new HashFunctionIdentity("name", HashFunctionIdentity.Signedness.UNSIGNED, HashFunctionIdentity.ProcessType.CYCLIC);
        assertFalse(HashFunctionValidator.areEqual(identity1, identity3));
    }

    @Test
    void testCheckAreEqual() {
        HashFunctionIdentity identity1 = new HashFunctionIdentity("name", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC);
        HashFunctionIdentity identity2 = new HashFunctionIdentity("name", HashFunctionIdentity.Signedness.SIGNED, HashFunctionIdentity.ProcessType.CYCLIC);
        assertDoesNotThrow(() -> HashFunctionValidator.checkAreEqual(identity1, identity2));

        HashFunctionIdentity identity3 = new HashFunctionIdentity("name", HashFunctionIdentity.Signedness.UNSIGNED, HashFunctionIdentity.ProcessType.CYCLIC);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> HashFunctionValidator.checkAreEqual(identity1, identity3));
        assertEquals("Hash functions are not equal: (" + HashFunctionIdentity.asCommonString(identity1) + ") != (" + HashFunctionIdentity.asCommonString(identity3) + ")", exception.getMessage());
    }
}