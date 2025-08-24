package org.apache.commons.compress.compressors.bzip2;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BlockSortLLM_Test {

    private static final byte[] FIXTURE = { 0, 1, (byte) 252, (byte) 253, (byte) 255,
            (byte) 254, 3, 2, (byte) 128 };
    private static final int[] FIXTURE_SORTED = {
            0, 1, 7, 6, 8, 2, 3, 5, 4
    };

    /**
     * Test the fallbackSort method to ensure it correctly sorts the fmap array.
     */
    @Test
    public void testFallbackSort() {
        final BZip2CompressorOutputStream.Data data = new BZip2CompressorOutputStream.Data(1);
        final BlockSort s = new BlockSort(data);
        final int[] fmap = new int[FIXTURE.length];
        s.fallbackSort(fmap, FIXTURE, FIXTURE.length);
        assertArrayEquals(FIXTURE_SORTED, fmap);
    }
}