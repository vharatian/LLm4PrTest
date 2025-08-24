package org.apache.commons.compress.compressors.bzip2;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class BlockSortLLM_Test {

    @Test
    public void testMed3WithBytes() {
        assertEquals(2, BlockSort.med3(1, 2, 3));
        assertEquals(2, BlockSort.med3(3, 2, 1));
        assertEquals(2, BlockSort.med3(2, 3, 1));
        assertEquals(2, BlockSort.med3(2, 1, 3));
        assertEquals(2, BlockSort.med3(3, 1, 2));
        assertEquals(2, BlockSort.med3(1, 3, 2));
    }

    @Test
    public void testMainQSort3WithModifiedMed3() {
        final BZip2CompressorOutputStream.Data data = new BZip2CompressorOutputStream.Data(1);
        final BlockSort s = new BlockSort(data);
        final int[] fmap = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        final byte[] block = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        data.fmap = fmap;
        data.block = block;

        s.mainQSort3(data, 0, fmap.length - 1, 0, fmap.length - 1);

        // Check if the array is sorted correctly
        for (int i = 0; i < fmap.length - 1; i++) {
            assertEquals(true, data.block[data.fmap[i]] <= data.block[data.fmap[i + 1]]);
        }
    }
}