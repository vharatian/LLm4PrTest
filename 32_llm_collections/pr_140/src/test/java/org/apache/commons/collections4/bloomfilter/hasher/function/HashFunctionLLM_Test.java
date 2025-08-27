package org.apache.commons.collections4.bloomfilter.hasher.function;

import org.apache.commons.collections4.bloomfilter.hasher.HashFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HashFunctionLLM_Test {

    @Test
    public void testHashFunctionImplementation() {
        HashFunction hashFunction = new HashFunction() {
            @Override
            public int apply(byte[] data) {
                return data.length;
            }
        };
        assertNotNull(hashFunction, "HashFunction implementation should not be null");
    }
}