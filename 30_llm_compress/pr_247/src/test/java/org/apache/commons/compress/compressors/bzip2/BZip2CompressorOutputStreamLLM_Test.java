package org.apache.commons.compress.compressors.bzip2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BZip2CompressorOutputStreamLLM_Test {

    @Test
    public void testHbMakeCodeLengths() {
        // Setup
        byte[] len = new byte[10];
        int[] freq = {5, 9, 12, 13, 16, 45, 0, 0, 0, 0};
        BZip2CompressorOutputStream.Data data = new BZip2CompressorOutputStream.Data(1);
        int alphaSize = 6;
        int maxLen = 5;

        // Call the method
        BZip2CompressorOutputStream.hbMakeCodeLengths(len, freq, data, alphaSize, maxLen);

        // Validate the results
        // The exact expected values will depend on the implementation details of hbMakeCodeLengths
        // Here we check that the lengths are within the expected range and the array is not empty
        for (int i = 0; i < alphaSize; i++) {
            assertTrue(len[i] > 0 && len[i] <= maxLen, "Length out of range: " + len[i]);
        }
    }

    @Test
    public void testChooseBlockSize() {
        assertEquals(1, BZip2CompressorOutputStream.chooseBlockSize(132000));
        assertEquals(2, BZip2CompressorOutputStream.chooseBlockSize(264000));
        assertEquals(9, BZip2CompressorOutputStream.chooseBlockSize(1188000));
        assertEquals(9, BZip2CompressorOutputStream.chooseBlockSize(Long.MAX_VALUE));
    }

    @Test
    public void testConstructorWithInvalidBlockSize() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BZip2CompressorOutputStream(System.out, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new BZip2CompressorOutputStream(System.out, 10);
        });
    }

    @Test
    public void testWriteAfterClose() throws IOException {
        BZip2CompressorOutputStream stream = new BZip2CompressorOutputStream(System.out);
        stream.close();
        assertThrows(IOException.class, () -> {
            stream.write(0);
        });
    }

    @Test
    public void testWriteByteArrayWithInvalidOffsets() throws IOException {
        BZip2CompressorOutputStream stream = new BZip2CompressorOutputStream(System.out);
        byte[] buffer = new byte[10];
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(buffer, -1, 10);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(buffer, 0, -1);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(buffer, 5, 6);
        });
    }
}