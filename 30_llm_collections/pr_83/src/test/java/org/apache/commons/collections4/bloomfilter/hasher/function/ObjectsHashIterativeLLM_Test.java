package org.apache.commons.collections4.bloomfilter.hasher.function;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjectsHashIterativeLLM_Test {

    @Test
    public void testApplyWithSeedZero() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        byte[] buffer = {1, 2, 3, 4};
        long result = hashFunction.apply(buffer, 0);
        assertNotEquals(0, result);
    }

    @Test
    public void testApplyWithNonZeroSeed() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        byte[] buffer = {1, 2, 3, 4};
        long firstResult = hashFunction.apply(buffer, 1);
        long secondResult = hashFunction.apply(buffer, 1);
        assertNotEquals(firstResult, secondResult);
    }

    @Test
    public void testGetName() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        assertEquals("Objects32", hashFunction.getName());
    }

    @Test
    public void testGetProvider() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        assertEquals("Apache Commons Collections", hashFunction.getProvider());
    }

    @Test
    public void testGetSignedness() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        assertEquals(HashFunction.Signedness.SIGNED, hashFunction.getSignedness());
    }

    @Test
    public void testGetProcessType() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        assertEquals(HashFunction.ProcessType.ITERATIVE, hashFunction.getProcessType());
    }

    @Test
    public void testGetSignature() {
        ObjectsHashIterative hashFunction = new ObjectsHashIterative();
        assertNotEquals(0, hashFunction.getSignature());
    }
}