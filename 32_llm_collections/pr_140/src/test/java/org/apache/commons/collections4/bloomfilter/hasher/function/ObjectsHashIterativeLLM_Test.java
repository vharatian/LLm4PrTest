package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.Test;

public class ObjectsHashIterativeLLM_Test extends AbstractHashFunctionTest {

    @Test
    public void testSignatureConsistency() {
        // Create two instances of ObjectsHashIterative
        ObjectsHashIterative obj1 = new ObjectsHashIterative();
        ObjectsHashIterative obj2 = new ObjectsHashIterative();

        // Verify that the signature is consistent across instances
        assertEquals(obj1.getSignature(), obj2.getSignature());
    }

    @Test
    public void testSignatureAfterApply() {
        // Create an instance of ObjectsHashIterative
        ObjectsHashIterative obj = new ObjectsHashIterative();
        final byte[] buffer = "Test buffer".getBytes();

        // Capture the signature before apply
        long initialSignature = obj.getSignature();

        // Apply the hash function
        obj.apply(buffer, 0);

        // Verify that the signature remains unchanged after apply
        assertEquals(initialSignature, obj.getSignature());
    }

    @Override
    protected HashFunction createHashFunction() {
        return new ObjectsHashIterative();
    }
}