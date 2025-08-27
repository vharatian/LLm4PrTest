package org.apache.commons.compress.compressors.lz4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class BlockLZ4CompressorOutputStreamLLM_Test {

    @Test
    public void testLengthsMethodWithLiteralLengthLessThan15() {
        int litLength = 10;
        int brLength = 5;
        int result = BlockLZ4CompressorOutputStream.Pair.lengths(litLength, brLength);
        Assert.assertEquals((10 << BlockLZ4CompressorInputStream.SIZE_BITS) | 1, result);
    }

    @Test
    public void testLengthsMethodWithLiteralLengthEqualTo15() {
        int litLength = 15;
        int brLength = 5;
        int result = BlockLZ4CompressorOutputStream.Pair.lengths(litLength, brLength);
        Assert.assertEquals((15 << BlockLZ4CompressorInputStream.SIZE_BITS) | 1, result);
    }

    @Test
    public void testLengthsMethodWithLiteralLengthGreaterThan15() {
        int litLength = 20;
        int brLength = 5;
        int result = BlockLZ4CompressorOutputStream.Pair.lengths(litLength, brLength);
        Assert.assertEquals((15 << BlockLZ4CompressorInputStream.SIZE_BITS) | 1, result);
    }

    @Test
    public void testLengthsMethodWithBackReferenceLengthLessThan4() {
        int litLength = 10;
        int brLength = 3;
        int result = BlockLZ4CompressorOutputStream.Pair.lengths(litLength, brLength);
        Assert.assertEquals((10 << BlockLZ4CompressorInputStream.SIZE_BITS), result);
    }

    @Test
    public void testLengthsMethodWithBackReferenceLengthEqualTo4() {
        int litLength = 10;
        int brLength = 4;
        int result = BlockLZ4CompressorOutputStream.Pair.lengths(litLength, brLength);
        Assert.assertEquals((10 << BlockLZ4CompressorInputStream.SIZE_BITS) | 0, result);
    }

    @Test
    public void testLengthsMethodWithBackReferenceLengthGreaterThan18() {
        int litLength = 10;
        int brLength = 19;
        int result = BlockLZ4CompressorOutputStream.Pair.lengths(litLength, brLength);
        Assert.assertEquals((10 << BlockLZ4CompressorInputStream.SIZE_BITS) | 15, result);
    }
}