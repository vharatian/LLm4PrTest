package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.Test;

public class MD5CyclicLLM_Test extends AbstractHashFunctionTest {

    @Test
    public void applyWithFinalHashTest() {
        final MD5Cyclic md5 = new MD5Cyclic();
        final long l1 = 0x8b1a9953c4611296L;
        final byte[] buffer = "Hello".getBytes();
        long l = md5.apply(buffer, 0);
        assertEquals(l1, l);
    }

    @Override
    protected HashFunction createHashFunction() {
        return new MD5Cyclic();
    }
}