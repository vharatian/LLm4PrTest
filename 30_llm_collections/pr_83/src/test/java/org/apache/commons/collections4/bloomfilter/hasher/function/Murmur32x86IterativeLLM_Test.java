package org.apache.commons.collections4.bloomfilter.hasher.function;

import org.apache.commons.codec.digest.MurmurHash3;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunctionIdentity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Murmur32x86IterativeLLM_Test {

    @Test
    public void testApply() {
        Murmur32x86Iterative hashFunction = new Murmur32x86Iterative();
        byte[] data = "test".getBytes();
        int seed = 0;
        long expectedHash = MurmurHash3.hash32x86(data, 0, data.length, seed);
        assertEquals(expectedHash, hashFunction.apply(data, seed));
    }

    @Test
    public void testGetName() {
        Murmur32x86Iterative hashFunction = new Murmur32x86Iterative();
        assertEquals("Murmur3_x86_32", hashFunction.getName());
    }

    @Test
    public void testGetProvider() {
        Murmur32x86Iterative hashFunction = new Murmur32x86Iterative();
        assertEquals("Apache Commons Collections", hashFunction.getProvider());
    }

    @Test
    public void testGetSignedness() {
        Murmur32x86Iterative hashFunction = new Murmur32x86Iterative();
        assertEquals(HashFunction.Signedness.SIGNED, hashFunction.getSignedness());
    }

    @Test
    public void testGetProcessType() {
        Murmur32x86Iterative hashFunction = new Murmur32x86Iterative();
        assertEquals(HashFunction.ProcessType.ITERATIVE, hashFunction.getProcessType());
    }

    @Test
    public void testGetSignature() {
        Murmur32x86Iterative hashFunction = new Murmur32x86Iterative();
        long expectedSignature = hashFunction.apply(HashFunctionIdentity.prepareSignatureBuffer(hashFunction), 0);
        assertEquals(expectedSignature, hashFunction.getSignature());
    }
}