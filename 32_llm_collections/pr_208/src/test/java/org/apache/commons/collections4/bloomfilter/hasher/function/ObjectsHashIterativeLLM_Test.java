package org.apache.commons.collections4.bloomfilter.hasher.function;

import static org.junit.Assert.assertEquals;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.Test;

public class ObjectsHashIterativeLLM_Test extends AbstractHashFunctionTest {

    @Test
    public void applyWithNonZeroSeedTest() {
        final ObjectsHashIterative obj = new ObjectsHashIterative();
        final byte[] buffer = "Now is the time for all good men to come to the aid of their country"
                .getBytes(StandardCharsets.UTF_8);
        long l = obj.apply(buffer, 1);
        long prev = 0;
        assertEquals(Arrays.deepHashCode(new Object[] {prev, buffer}), l);
        for (int i = 2; i <= 5; i++) {
            prev += l;
            l = obj.apply(buffer, i);
            assertEquals(Arrays.deepHashCode(new Object[] {prev, buffer}), l);
        }
    }

    @Override
    protected HashFunction createHashFunction() {
        return new ObjectsHashIterative();
    }
}