package org.apache.commons.codec.digest;

import org.junit.Assert;
import org.junit.Test;
import org.apache.commons.codec.binary.StringUtils;

public class MurmurHash3LLM_Test {

    @Test
    public void testHash64Long() {
        final long[] data = {0L, Long.MIN_VALUE, Long.MAX_VALUE, -1L, 123456789L, -987654321L};
        for (long datum : data) {
            long expected = MurmurHash3.hash64(datum);
            long actual = MurmurHash3.hash64(datum);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testHash64Int() {
        final int[] data = {0, Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 123456789, -987654321};
        for (int datum : data) {
            long expected = MurmurHash3.hash64(datum);
            long actual = MurmurHash3.hash64(datum);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testHash64LongWithLength() {
        final long[] data = {0L, Long.MIN_VALUE, Long.MAX_VALUE, -1L, 123456789L, -987654321L};
        for (long datum : data) {
            long expected = MurmurHash3.hash64(datum);
            long actual = MurmurHash3.hash64(datum);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testHash64IntWithLength() {
        final int[] data = {0, Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 123456789, -987654321};
        for (int datum : data) {
            long expected = MurmurHash3.hash64(datum);
            long actual = MurmurHash3.hash64(datum);
            Assert.assertEquals(expected, actual);
        }
    }
}