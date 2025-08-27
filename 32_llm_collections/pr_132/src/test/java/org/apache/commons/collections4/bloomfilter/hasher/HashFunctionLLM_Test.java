package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashFunctionLLM_Test {

    @Test
    public void testApplyWithValidInput() {
        HashFunction hashFunction = new HashFunctionImpl();
        byte[] buffer = {1, 2, 3, 4};
        int seed = 12345;
        long result = hashFunction.apply(buffer, seed);
        assertNotNull(result);
    }

    @Test
    public void testApplyWithEmptyBuffer() {
        HashFunction hashFunction = new HashFunctionImpl();
        byte[] buffer = {};
        int seed = 12345;
        long result = hashFunction.apply(buffer, seed);
        assertNotNull(result);
    }

    @Test
    public void testApplyWithNullBuffer() {
        HashFunction hashFunction = new HashFunctionImpl();
        byte[] buffer = null;
        int seed = 12345;
        assertThrows(NullPointerException.class, () -> {
            hashFunction.apply(buffer, seed);
        });
    }

    @Test
    public void testApplyWithNegativeSeed() {
        HashFunction hashFunction = new HashFunctionImpl();
        byte[] buffer = {1, 2, 3, 4};
        int seed = -12345;
        long result = hashFunction.apply(buffer, seed);
        assertNotNull(result);
    }
}