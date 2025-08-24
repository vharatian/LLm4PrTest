package org.apache.commons.compress.compressors.lz77support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LZ77CompressorLLM_Test {

    private static final byte[] BLA, SAM, ONE_TO_TEN;

    static {
        try {
            BLA = "Blah blah blah blah blah!".getBytes("ASCII");
            SAM = ("I am Sam\n"
                    + "\n"
                    + "Sam I am\n"
                    + "\n"
                    + "That Sam-I-am!\n"
                    + "That Sam-I-am!\n"
                    + "I do not like\n"
                    + "that Sam-I-am!\n"
                    + "\n"
                    + "Do you like green eggs and ham?\n"
                    + "\n"
                    + "I do not like them, Sam-I-am.\n"
                    + "I do not like green eggs and ham.").getBytes("ASCII");
        } catch (IOException ex) {
            throw new RuntimeException("ASCII not supported");
        }
        ONE_TO_TEN = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    private List<LZ77Compressor.Block> compress(Parameters params, byte[]... chunks) throws IOException {
        final List<LZ77Compressor.Block> blocks = new ArrayList<>();
        LZ77Compressor c = new LZ77Compressor(params, new LZ77Compressor.Callback() {
            @Override
            public void accept(LZ77Compressor.Block block) {
                if (block instanceof LZ77Compressor.LiteralBlock) {
                    LZ77Compressor.LiteralBlock b = (LZ77Compressor.LiteralBlock) block;
                    int len = b.getLength();
                    block = new LZ77Compressor.LiteralBlock(
                            Arrays.copyOfRange(b.getData(), b.getOffset(), b.getOffset() + len),
                            0, len);
                }
                blocks.add(block);
            }
        });
        for (byte[] chunk : chunks) {
            c.compress(chunk);
        }
        c.finish();
        return blocks;
    }

    @Test
    public void testMinBackReferenceLengthDocumentation() throws IOException {
        // This test ensures that the documentation change regarding minBackReferenceLength does not affect functionality
        List<LZ77Compressor.Block> blocks = compress(newParameters(128, 3, 5, 0, 0), BLA);
        assertSize(7, blocks);
        assertLiteralBlock("Blah b", blocks.get(0));
        assertBackReference(5, 5, blocks.get(1));
        assertBackReference(5, 5, blocks.get(2));
        assertBackReference(5, 5, blocks.get(3));
        assertBackReference(5, 3, blocks.get(4));
        assertLiteralBlock("!", blocks.get(5));
    }

    private static final void assertSize(int expectedSize, List<LZ77Compressor.Block> blocks) {
        assertEquals(expectedSize, blocks.size());
        assertEquals(LZ77Compressor.Block.BlockType.EOD, blocks.get(expectedSize - 1).getType());
    }

    private static final void assertLiteralBlock(String expectedContent, LZ77Compressor.Block block)
            throws IOException {
        assertLiteralBlock(expectedContent.getBytes("ASCII"), block);
    }

    private static final void assertLiteralBlock(byte[] expectedContent, LZ77Compressor.Block block) {
        assertEquals(LZ77Compressor.LiteralBlock.class, block.getClass());
        assertArrayEquals(expectedContent, ((LZ77Compressor.LiteralBlock) block).getData());
    }

    private static final void assertBackReference(int expectedOffset, int expectedLength, LZ77Compressor.Block block) {
        assertEquals(LZ77Compressor.BackReference.class, block.getClass());
        LZ77Compressor.BackReference b = (LZ77Compressor.BackReference) block;
        assertEquals(expectedOffset, b.getOffset());
        assertEquals(expectedLength, b.getLength());
    }

    private static Parameters newParameters(int windowSize) {
        return Parameters.builder(windowSize).build();
    }

    private static Parameters newParameters(int windowSize, int minBackReferenceLength, int maxBackReferenceLength,
                                            int maxOffset, int maxLiteralLength) {
        return Parameters.builder(windowSize)
                .withMinBackReferenceLength(minBackReferenceLength)
                .withMaxBackReferenceLength(maxBackReferenceLength)
                .withMaxOffset(maxOffset)
                .withMaxLiteralLength(maxLiteralLength)
                .tunedForCompressionRatio()
                .build();
    }
}