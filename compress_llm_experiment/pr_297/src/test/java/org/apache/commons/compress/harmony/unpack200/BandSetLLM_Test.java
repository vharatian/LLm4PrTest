package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BandSetLLM_Test {

    private BandSet bandSet;
    private Segment segment;
    private SegmentHeader header;
    private BHSDCodec hiCodec;
    private BHSDCodec loCodec;

    @BeforeEach
    public void setUp() {
        segment = mock(Segment.class);
        header = mock(SegmentHeader.class);
        when(segment.getSegmentHeader()).thenReturn(header);
        bandSet = new BandSet(segment) {
            @Override
            public void read(InputStream inputStream) throws IOException, Pack200Exception {
                // No implementation needed for this test
            }

            @Override
            public void unpack() throws IOException, Pack200Exception {
                // No implementation needed for this test
            }
        };
        hiCodec = mock(BHSDCodec.class);
        loCodec = mock(BHSDCodec.class);
    }

    @Test
    public void testParseFlagsWithHiCodec() throws IOException, Pack200Exception {
        int[] counts = {2, 3};
        int[] hiValues = {1, 2, 3, 4, 5};
        int[] loValues = {6, 7, 8, 9, 10};
        InputStream in = new ByteArrayInputStream(new byte[0]);

        when(hiCodec.decodeInts(anyInt(), eq(in))).thenReturn(hiValues);
        when(loCodec.decodeInts(anyInt(), eq(in))).thenReturn(loValues);

        long[][] expected = {
                {((long) 1 << 32) | 6, ((long) 2 << 32) | 7},
                {((long) 3 << 32) | 8, ((long) 4 << 32) | 9, ((long) 5 << 32) | 10}
        };

        long[][] result = bandSet.parseFlags("test", in, counts, hiCodec, loCodec);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testParseFlagsWithoutHiCodec() throws IOException, Pack200Exception {
        int[] counts = {2, 3};
        int[] loValues = {6, 7, 8, 9, 10};
        InputStream in = new ByteArrayInputStream(new byte[0]);

        when(loCodec.decodeInts(anyInt(), eq(in))).thenReturn(loValues);

        long[][] expected = {
                {6, 7},
                {8, 9, 10}
        };

        long[][] result = bandSet.parseFlags("test", in, counts, null, loCodec);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testParseFlagsThrowsException() throws IOException, Pack200Exception {
        int[] counts = {2, 3};
        InputStream in = new ByteArrayInputStream(new byte[0]);

        when(loCodec.decodeInts(anyInt(), eq(in))).thenThrow(new IOException("Test exception"));

        assertThrows(IOException.class, () -> bandSet.parseFlags("test", in, counts, null, loCodec));
    }
}