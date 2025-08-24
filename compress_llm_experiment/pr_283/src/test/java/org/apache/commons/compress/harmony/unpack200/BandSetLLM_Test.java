package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BandSetLLM_Test {

    private BandSet bandSet;
    private Segment segment;
    private SegmentHeader header;

    @BeforeEach
    public void setUp() {
        segment = new Segment();
        header = new SegmentHeader();
        bandSet = new BandSet(segment) {
            @Override
            public void read(InputStream inputStream) throws IOException, Pack200Exception {
                // Mock implementation
            }

            @Override
            public void unpack() throws IOException, Pack200Exception {
                // Mock implementation
            }
        };
    }

    @Test
    public void testParseReferences() throws IOException, Pack200Exception {
        String name = "test";
        BHSDCodec codec = new BHSDCodec(1, 1, 1, 1);
        int count = 3;
        String[] reference = {"ref1", "ref2", "ref3"};
        InputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2});

        String[][] result = bandSet.parseReferences(name, in, codec, new int[]{count}, reference);

        assertArrayEquals(new String[][]{{"ref1", "ref2", "ref3"}}, result);
    }

    @Test
    public void testParseCPUTF8References() throws IOException, Pack200Exception {
        String name = "test";
        BHSDCodec codec = new BHSDCodec(1, 1, 1, 1);
        int[] counts = {3};
        InputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2});
        CPUTF8[] cpUTF8Values = {new CPUTF8("value1"), new CPUTF8("value2"), new CPUTF8("value3")};

        segment.getCpBands().setCpUTF8Values(cpUTF8Values);

        CPUTF8[][] result = bandSet.parseCPUTF8References(name, in, codec, counts);

        assertArrayEquals(new CPUTF8[][]{{cpUTF8Values[0], cpUTF8Values[1], cpUTF8Values[2]}}, result);
    }

    @Test
    public void testParseCPSignatureReferences() throws IOException, Pack200Exception {
        String name = "test";
        BHSDCodec codec = new BHSDCodec(1, 1, 1, 1);
        int[] counts = {3};
        InputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2});
        CPUTF8[] cpSignatureValues = {new CPUTF8("sig1"), new CPUTF8("sig2"), new CPUTF8("sig3")};

        segment.getCpBands().setCpSignatureValues(cpSignatureValues);

        CPUTF8[][] result = bandSet.parseCPSignatureReferences(name, in, codec, counts);

        assertArrayEquals(new CPUTF8[][]{{cpSignatureValues[0], cpSignatureValues[1], cpSignatureValues[2]}}, result);
    }

    @Test
    public void testParseReferencesWithInvalidIndex() {
        String name = "test";
        BHSDCodec codec = new BHSDCodec(1, 1, 1, 1);
        int count = 3;
        String[] reference = {"ref1", "ref2"};
        InputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2});

        assertThrows(Pack200Exception.class, () -> {
            bandSet.parseReferences(name, in, codec, new int[]{count}, reference);
        });
    }
}