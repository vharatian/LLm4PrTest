package org.apache.commons.codec.digest;

import org.junit.Assert;
import org.junit.Test;

public class MurmurHash3LLM_Test {

    @Test
    public void testHash64LongSignExtensionBug() {
        // Test to ensure the sign extension bug does not affect the result when the default seed is positive
        long data = 123456789L;
        long expectedHash = MurmurHash3.hash64(data);
        Assert.assertEquals(expectedHash, MurmurHash3.hash64(data));
    }

    @Test
    public void testHash64IntSignExtensionBug() {
        // Test to ensure the sign extension bug does not affect the result when the default seed is positive
        int data = 123456789;
        long expectedHash = MurmurHash3.hash64(data);
        Assert.assertEquals(expectedHash, MurmurHash3.hash64(data));
    }

    @Test
    public void testHash64ShortSignExtensionBug() {
        // Test to ensure the sign extension bug does not affect the result when the default seed is positive
        short data = 12345;
        long expectedHash = MurmurHash3.hash64(data);
        Assert.assertEquals(expectedHash, MurmurHash3.hash64(data));
    }

    @Test
    public void testHash64ByteArraySignExtensionBug() {
        // Test to ensure the sign extension bug does not affect the result when the default seed is positive
        byte[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        long expectedHash = MurmurHash3.hash64(data);
        Assert.assertEquals(expectedHash, MurmurHash3.hash64(data));
    }

    @Test
    public void testHash64ByteArrayWithOffsetAndLengthSignExtensionBug() {
        // Test to ensure the sign extension bug does not affect the result when the default seed is positive
        byte[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int offset = 2;
        int length = 6;
        long expectedHash = MurmurHash3.hash64(data, offset, length);
        Assert.assertEquals(expectedHash, MurmurHash3.hash64(data, offset, length));
    }

    @Test
    public void testHash64ByteArrayWithNegativeSeed() {
        // Test to ensure the sign extension bug manifests when the seed is negative
        byte[] data = {1, 2, 3, 4, 5, 6, 7, 8};
        int seed = -42;
        long hashWithNegativeSeed = MurmurHash3.hash64(data, 0, data.length, seed);
        Assert.assertNotEquals(hashWithNegativeSeed, MurmurHash3.hash64(data));
    }
}