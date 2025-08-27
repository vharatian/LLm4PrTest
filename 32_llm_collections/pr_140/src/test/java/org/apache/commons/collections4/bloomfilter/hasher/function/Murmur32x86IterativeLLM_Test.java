package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Murmur32x86IterativeLLM_Test {

    @Test
    public void testGetName() {
        Murmur32x86Iterative murmur = new Murmur32x86Iterative();
        assertEquals("Murmur3_x86_32", murmur.getName());
    }

    @Test
    public void testGetProcessType() {
        Murmur32x86Iterative murmur = new Murmur32x86Iterative();
        assertEquals(HashFunction.ProcessType.ITERATIVE, murmur.getProcessType());
    }

    @Test
    public void testGetProvider() {
        Murmur32x86Iterative murmur = new Murmur32x86Iterative();
        assertEquals("Apache Commons Collections", murmur.getProvider());
    }

    @Test
    public void testGetSignature() {
        Murmur32x86Iterative murmur = new Murmur32x86Iterative();
        long expectedSignature = Signatures.getSignature(murmur);
        assertEquals(expectedSignature, murmur.getSignature());
    }

    @Test
    public void testGetSignedness() {
        Murmur32x86Iterative murmur = new Murmur32x86Iterative();
        assertEquals(HashFunction.Signedness.SIGNED, murmur.getSignedness());
    }
}