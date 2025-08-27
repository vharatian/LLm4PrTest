package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.Test;

public class MD5CyclicLLM_Test extends AbstractHashFunctionTest {

    @Test
    public void signatureTest() {
        final MD5Cyclic md5 = new MD5Cyclic();
        long expectedSignature = Signatures.getSignature(md5);
        assertEquals(expectedSignature, md5.getSignature());
    }

    @Override
    protected HashFunction createHashFunction() {
        return new MD5Cyclic();
    }
}