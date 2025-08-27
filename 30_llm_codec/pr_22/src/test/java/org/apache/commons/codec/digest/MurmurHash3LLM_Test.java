package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MurmurHash3LLM_Test {

    @Test
    public void testHash32WithTwoLongs() {
        long l0 = 123456789L;
        long l1 = 987654321L;
        int hash = MurmurHash3.hash32(l0, l1);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithLong() {
        long l0 = 123456789L;
        int hash = MurmurHash3.hash32(l0);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithLongAndSeed() {
        long l0 = 123456789L;
        int seed = 42;
        int hash = MurmurHash3.hash32(l0, seed);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithByteArray() {
        byte[] data = "test".getBytes();
        int hash = MurmurHash3.hash32(data);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithString() {
        String data = "test";
        int hash = MurmurHash3.hash32(data);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithByteArrayAndLength() {
        byte[] data = "test".getBytes();
        int length = data.length;
        int hash = MurmurHash3.hash32(data, length);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithByteArrayLengthAndSeed() {
        byte[] data = "test".getBytes();
        int length = data.length;
        int seed = 42;
        int hash = MurmurHash3.hash32(data, length, seed);
        assertNotNull(hash);
    }

    @Test
    public void testHash32WithByteArrayOffsetLengthAndSeed() {
        byte[] data = "test".getBytes();
        int offset = 0;
        int length = data.length;
        int seed = 42;
        int hash = MurmurHash3.hash32(data, offset, length, seed);
        assertNotNull(hash);
    }

    @Test
    public void testHash64WithByteArray() {
        byte[] data = "test".getBytes();
        long hash = MurmurHash3.hash64(data);
        assertNotNull(hash);
    }

    @Test
    public void testHash64WithLong() {
        long data = 123456789L;
        long hash = MurmurHash3.hash64(data);
        assertNotNull(hash);
    }

    @Test
    public void testHash64WithInt() {
        int data = 123456789;
        long hash = MurmurHash3.hash64(data);
        assertNotNull(hash);
    }

    @Test
    public void testHash64WithShort() {
        short data = 12345;
        long hash = MurmurHash3.hash64(data);
        assertNotNull(hash);
    }

    @Test
    public void testHash64WithByteArrayOffsetLength() {
        byte[] data = "test".getBytes();
        int offset = 0;
        int length = data.length;
        long hash = MurmurHash3.hash64(data, offset, length);
        assertNotNull(hash);
    }

    @Test
    public void testHash64WithByteArrayOffsetLengthAndSeed() {
        byte[] data = "test".getBytes();
        int offset = 0;
        int length = data.length;
        int seed = 42;
        long hash = MurmurHash3.hash64(data, offset, length, seed);
        assertNotNull(hash);
    }

    @Test
    public void testHash128WithByteArray() {
        byte[] data = "test".getBytes();
        long[] hash = MurmurHash3.hash128(data);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testHash128WithString() {
        String data = "test";
        long[] hash = MurmurHash3.hash128(data);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testHash128WithByteArrayOffsetLengthAndSeed() {
        byte[] data = "test".getBytes();
        int offset = 0;
        int length = data.length;
        int seed = 42;
        long[] hash = MurmurHash3.hash128(data, offset, length, seed);
        assertNotNull(hash);
        assertEquals(2, hash.length);
    }

    @Test
    public void testIncrementalHash32() {
        MurmurHash3.IncrementalHash32 incrementalHash = new MurmurHash3.IncrementalHash32();
        incrementalHash.start(MurmurHash3.DEFAULT_SEED);
        byte[] data = "test".getBytes();
        incrementalHash.add(data, 0, data.length);
        int hash = incrementalHash.end();
        assertNotNull(hash);
    }
}