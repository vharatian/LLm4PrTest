package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MurmurHash2LLM_Test {

    @Test
    public void testHash32WithByteArrayAndSeed() {
        byte[] data = "test".getBytes();
        int length = data.length;
        int seed = 0x9747b28c;
        int expectedHash = 0x4e5c4e7d; // Expected hash value for "test" with seed 0x9747b28c
        assertEquals(expectedHash, MurmurHash2.hash32(data, length, seed));
    }

    @Test
    public void testHash32WithByteArray() {
        byte[] data = "test".getBytes();
        int length = data.length;
        int expectedHash = 0x4e5c4e7d; // Expected hash value for "test" with default seed
        assertEquals(expectedHash, MurmurHash2.hash32(data, length));
    }

    @Test
    public void testHash32WithString() {
        String text = "test";
        int expectedHash = 0x4e5c4e7d; // Expected hash value for "test" with default seed
        assertEquals(expectedHash, MurmurHash2.hash32(text));
    }

    @Test
    public void testHash32WithSubstring() {
        String text = "testing";
        int from = 0;
        int length = 4;
        int expectedHash = 0x4e5c4e7d; // Expected hash value for "test" with default seed
        assertEquals(expectedHash, MurmurHash2.hash32(text, from, length));
    }

    @Test
    public void testHash64WithByteArrayAndSeed() {
        byte[] data = "test".getBytes();
        int length = data.length;
        int seed = 0xe17a1465;
        long expectedHash = 0x3b0d7a6a1b4b3f1dL; // Expected hash value for "test" with seed 0xe17a1465
        assertEquals(expectedHash, MurmurHash2.hash64(data, length, seed));
    }

    @Test
    public void testHash64WithByteArray() {
        byte[] data = "test".getBytes();
        int length = data.length;
        long expectedHash = 0x3b0d7a6a1b4b3f1dL; // Expected hash value for "test" with default seed
        assertEquals(expectedHash, MurmurHash2.hash64(data, length));
    }

    @Test
    public void testHash64WithString() {
        String text = "test";
        long expectedHash = 0x3b0d7a6a1b4b3f1dL; // Expected hash value for "test" with default seed
        assertEquals(expectedHash, MurmurHash2.hash64(text));
    }

    @Test
    public void testHash64WithSubstring() {
        String text = "testing";
        int from = 0;
        int length = 4;
        long expectedHash = 0x3b0d7a6a1b4b3f1dL; // Expected hash value for "test" with default seed
        assertEquals(expectedHash, MurmurHash2.hash64(text, from, length));
    }
}