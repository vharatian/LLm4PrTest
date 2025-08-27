package org.apache.commons.compress.compressors.bzip2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class BlockSortLLM_Test {

    private static final byte[] FIXTURE = { 0, 1, (byte) 252, (byte) 253, (byte) 255,
            (byte) 254, 3, 2, (byte) 128 };
    private static final byte[] FIXTURE_BWT = { (byte) 128, 0, 3, (byte) 254, 2, 1,
            (byte) 252, (byte) 255, (byte) 253 };
    private static final int[] FIXTURE_SORTED = {
            0, 1, 7, 6, 8, 2, 3, 5, 4
    };
    private static final byte[] FIXTURE2 = {
            'C', 'o', 'm', 'm', 'o', 'n', 's', ' ', 'C', 'o', 'm', 'p', 'r', 'e', 's', 's',
    };
    private static final byte[] FIXTURE2_BWT = {
            's', 's', ' ', 'r', 'o', 'm', 'o', 'o', 'C', 'C', 'm', 'm', 'p', 'n', 's', 'e',
    };

    @Test
    public void testMainQSort3StackPointerIncrement() {
        final DS ds = setUpFixture();
        ds.s.mainSort(ds.data, FIXTURE.length - 1);
        assertFixtureSorted(ds.data);
    }

    private DS setUpFixture() {
        return setUpFixture(FIXTURE);
    }

    private void assertFixtureSorted(final BZip2CompressorOutputStream.Data data) {
        assertFixtureSorted(data, FIXTURE, FIXTURE_BWT);
    }

    private DS setUpFixture2() {
        return setUpFixture(FIXTURE2);
    }

    private void assertFixture2Sorted(final BZip2CompressorOutputStream.Data data) {
        assertFixtureSorted(data, FIXTURE2, FIXTURE2_BWT);
    }

    private DS setUpFixture(final byte[] fixture) {
        final BZip2CompressorOutputStream.Data data = new BZip2CompressorOutputStream.Data(1);
        System.arraycopy(fixture, 0, data.block, 1, fixture.length);
        return new DS(data, new BlockSort(data));
    }

    private void assertFixtureSorted(final BZip2CompressorOutputStream.Data data,
                                     final byte[] fixture, final byte[] fixtureBwt) {
        assertEquals(fixture[fixture.length - 1], data.block[0]);
        for (int i = 0; i < fixture.length; i++) {
            assertEquals(fixtureBwt[i], data.block[data.fmap[i]]);
        }
    }

    private static class DS {
        private final BZip2CompressorOutputStream.Data data;
        private final BlockSort s;

        DS(final BZip2CompressorOutputStream.Data data, final BlockSort s) {
            this.data = data;
            this.s = s;
        }
    }
}