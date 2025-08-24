package org.apache.commons.compress.archivers.sevenz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class FolderLLM_Test {

    // No new functionality was added in the diff, only a comment typo was fixed.
    // Therefore, no new tests are required for the functionality.
    // However, we can add a test to ensure the toString method works as expected.

    @Test
    public void testToString() {
        final Folder folder = new Folder();
        folder.coders = new Coder[2];
        folder.totalInputStreams = 3L;
        folder.totalOutputStreams = 2L;
        folder.bindPairs = new BindPair[1];
        folder.packedStreams = new long[1];
        folder.unpackSizes = new long[2];
        folder.hasCrc = true;
        folder.crc = 12345L;
        folder.numUnpackSubStreams = 1;

        String expected = "Folder with 2 coders, 3 input streams, 2 output streams, 1 bind pairs, 1 packed streams, 2 unpack sizes, with CRC 12345 and 1 unpack streams";
        assertEquals(expected, folder.toString());
    }
}