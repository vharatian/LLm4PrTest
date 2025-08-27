package org.apache.commons.compress.harmony.unpack200;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SegmentHeaderLLM_Test {

    private Segment segment;
    private SegmentHeader segmentHeader;

    @BeforeEach
    public void setUp() {
        segment = new Segment();
        segmentHeader = new SegmentHeader(segment);
    }

    @Test
    public void testReadWithInvalidMagicWord() {
        byte[] invalidMagicWord = {0x00, 0x00, 0x00, 0x00};
        InputStream in = new ByteArrayInputStream(invalidMagicWord);

        assertThrows(Error.class, () -> segmentHeader.read(in));
    }

    @Test
    public void testReadWithValidMagicWord() throws IOException, Pack200Exception {
        byte[] validMagicWord = { (byte) 0xCA, (byte) 0xFE, (byte) 0xD0, (byte) 0x0D };
        InputStream in = new ByteArrayInputStream(validMagicWord);

        segmentHeader.read(in);

        assertEquals(0, in.available());
    }

    @Test
    public void testDecodeScalarArray() throws IOException, Pack200Exception {
        byte[] input = { 0x01, 0x02, 0x03, 0x04 };
        InputStream in = new ByteArrayInputStream(input);
        int[] expected = { 1, 2, 3, 4 };

        int[] result = segmentHeader.decodeScalar("test", in, Codec.BYTE1, 4);

        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }
}