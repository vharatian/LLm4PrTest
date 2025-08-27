package org.apache.commons.compress.compressors.lz77support;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LZ77CompressorLLM_Test {

    private static final byte[] BLA, SAM, ONE_TO_TEN;

    static {
        BLA = "Blah blah blah blah blah!".getBytes(US_ASCII);
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
                + "I do not like green eggs and ham.").getBytes(US_ASCII);
        ONE_TO_TEN = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    private static final void assertBackReference(final int expectedOffset, final int expectedLength, final LZ77Compressor.Block block) {
        assertEquals(LZ77Compressor.BackReference.class, block.getClass());
        final LZ77Compressor.BackReference b = (LZ77Compressor.BackReference) block;
        assertEquals(expectedOffset, b.getOffset());
        assertEquals(expectedLength, b.getLength());
    }

    private static final void assertLiteralBlock(final byte[] expectedContent, final LZ77Compressor.Block block) {
        assertEquals(LZ77Compressor.LiteralBlock.class, block.getClass());
        assertArrayEquals(expectedContent, ((LZ77Compressor.LiteralBlock) block).getData());
    }

    private static final void assertLiteralBlock(final String expectedContent, final LZ77Compressor.Block block) {
        assertLiteralBlock(expectedContent.getBytes(US_ASCII), block);
    }

    private static final void assertSize(final int expectedSize, final List<LZ77Compressor.Block> blocks) {
        assertEquals(expectedSize, blocks.size());
        assertEquals(LZ77Compressor.Block.BlockType.EOD, blocks.get(expectedSize - 1).getType());
    }

    private static Parameters newParameters(final int windowSize) {
        return Parameters.builder(windowSize).build();
    }

    private static Parameters newParameters(final int windowSize, final int minBackReferenceLength, final int maxBackReferenceLength,
                                            final int maxOffset, final int maxLiteralLength) {
        return Parameters.builder(windowSize)
                .withMinBackReferenceLength(minBackReferenceLength)
                .withMaxBackReferenceLength(maxBackReferenceLength)
                .withMaxOffset(maxOffset)
                .withMaxLiteralLength(maxLiteralLength)
                .tunedForCompressionRatio()
                .build();
    }

    private static final byte[][] stagger(final byte[] data) {
        final byte[][] r = new byte[data.length][1];
        for (int i = 0; i < data.length; i++) {
            r[i][0] = data[i];
        }
        return r;
    }

    @Test
    public void testCommentChanges() throws IOException {
        final List<LZ77Compressor.Block> blocks = compress(newParameters(128), BLA);
        assertSize(4, blocks);
        assertLiteralBlock("Blah b", blocks.get(0));
        assertBackReference(5, 18, blocks.get(1));
        assertLiteralBlock("!", blocks.get(2));
    }

    private List<LZ77Compressor.Block> compress(final Parameters params, final byte[]... chunks) throws IOException {
        final List<LZ77Compressor.Block> blocks = new ArrayList<>();
        final LZ77Compressor c = new LZ77Compressor(params, block -> {
            if (block instanceof LZ77Compressor.LiteralBlock) {
                final LZ77Compressor.LiteralBlock b = (LZ77Compressor.LiteralBlock) block;
                final int len = b.getLength();
                block = new LZ77Compressor.LiteralBlock(
                        Arrays.copyOfRange(b.getData(), b.getOffset(), b.getOffset() + len),
                        0, len);
            }
            blocks.add(block);
        });
        for (final byte[] chunk : chunks) {
            c.compress(chunk);
        }
        c.finish();
        return blocks;
    }
}