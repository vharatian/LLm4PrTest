package org.apache.commons.collections4.bloomfilter.hasher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HashFunctionLLM_Test {

    @Test
    public void testApply() {
        HashFunction hashFunction = mock(HashFunction.class);
        byte[] buffer = "test".getBytes();
        int seed = 1234;
        long expectedHash = 5678L;

        when(hashFunction.apply(buffer, seed)).thenReturn(expectedHash);

        long actualHash = hashFunction.apply(buffer, seed);
        assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testGetSignature() throws Exception {
        HashFunction hashFunction = mock(HashFunction.class);
        String name = "TestFunction";
        String signedness = "signed";
        String process = "process";
        byte[] buffer = String.format("%s-%s-%s", name.toUpperCase(Locale.ROOT), signedness, process).getBytes("UTF-8");
        int seed = 0;
        long expectedSignature = 123456789L;

        when(hashFunction.getName()).thenReturn(name);
        when(hashFunction.getSignedness()).thenReturn(signedness);
        when(hashFunction.getProcess()).thenReturn(process);
        when(hashFunction.apply(buffer, seed)).thenReturn(expectedSignature);

        long actualSignature = hashFunction.getSignature();
        assertEquals(expectedSignature, actualSignature);
    }
}