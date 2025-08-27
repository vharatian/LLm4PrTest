package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HashFunctionLLM_Test {

    @Test
    public void testApply() {
        HashFunction hashFunction = new HashFunction() {
            @Override
            public long apply(byte[] buffer, int seed) {
                // Simple hash function for testing
                long hash = seed;
                for (byte b : buffer) {
                    hash = 31 * hash + b;
                }
                return hash;
            }

            @Override
            public String getName() {
                return "TestHashFunction";
            }

            @Override
            public String getProvider() {
                return "TestProvider";
            }

            @Override
            public int getSignature() {
                return 0;
            }

            @Override
            public ProcessType getProcessType() {
                return ProcessType.CYCLIC;
            }
        };

        byte[] buffer = {1, 2, 3, 4, 5};
        int seed = 7;
        long expectedHash = 31 * (31 * (31 * (31 * (31 * (31 * seed + 1) + 2) + 3) + 4) + 5);
        long actualHash = hashFunction.apply(buffer, seed);

        assertEquals(expectedHash, actualHash);
    }
}