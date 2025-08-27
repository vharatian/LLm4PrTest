package org.apache.commons.collections4.bloomfilter.hasher.function;

import org.apache.commons.codec.digest.MurmurHash3;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Murmur128x86CyclicLLM_Test {

    @Test
    public void testApplyWithSeedZero() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        byte[] buffer = "test".getBytes();
        long result = hashFunction.apply(buffer, 0);
        long[] expectedParts = MurmurHash3.hash128x64(buffer, 0, buffer.length, 0);
        assertEquals(expectedParts[0], result);
    }

    @Test
    public void testApplyWithNonZeroSeed() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        byte[] buffer = "test".getBytes();
        hashFunction.apply(buffer, 0); // Initialize parts
        long result = hashFunction.apply(buffer, 1);
        long[] expectedParts = MurmurHash3.hash128x64(buffer, 0, buffer.length, 0);
        expectedParts[0] += expectedParts[1];
        assertEquals(expectedParts[0], result);
    }

    @Test
    public void testGetName() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        assertEquals("Murmur3_x64_128", hashFunction.getName());
    }

    @Test
    public void testGetProvider() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        assertEquals("Apache Commons Collections", hashFunction.getProvider());
    }

    @Test
    public void testGetSignedness() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        assertEquals(HashFunction.Signedness.SIGNED, hashFunction.getSignedness());
    }

    @Test
    public void testGetProcessType() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        assertEquals(HashFunction.ProcessType.CYCLIC, hashFunction.getProcessType());
    }

    @Test
    public void testGetSignature() {
        Murmur128x86Cyclic hashFunction = new Murmur128x86Cyclic();
        long expectedSignature = hashFunction.apply(HashFunctionIdentity.prepareSignatureBuffer(hashFunction), 0);
        assertEquals(expectedSignature, hashFunction.getSignature());
    }
}