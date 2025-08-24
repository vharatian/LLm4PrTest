package org.apache.commons.compress.archivers.sevenz;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FolderLLM_Test {

    @Test
    public void testToStringWithCrc() {
        Folder folder = new Folder();
        folder.coders = new Coder[2];
        folder.totalInputStreams = 3L;
        folder.totalOutputStreams = 4L;
        folder.bindPairs = new BindPair[1];
        folder.packedStreams = new long[1];
        folder.unpackSizes = new long[1];
        folder.hasCrc = true;
        folder.crc = 12345L;
        folder.numUnpackSubStreams = 2;
        String expected = "Folder with 2 coders, 3 input streams, 4 output streams, 1 bind pairs, 1 packed streams, 1 unpack sizes, with CRC 12345 and 2 unpack streams";
        assertEquals(expected, folder.toString());
    }

    @Test
    public void testToStringWithoutCrc() {
        Folder folder = new Folder();
        folder.coders = new Coder[2];
        folder.totalInputStreams = 3L;
        folder.totalOutputStreams = 4L;
        folder.bindPairs = new BindPair[1];
        folder.packedStreams = new long[1];
        folder.unpackSizes = new long[1];
        folder.hasCrc = false;
        folder.numUnpackSubStreams = 2;
        String expected = "Folder with 2 coders, 3 input streams, 4 output streams, 1 bind pairs, 1 packed streams, 1 unpack sizes, without CRC and 2 unpack streams";
        assertEquals(expected, folder.toString());
    }
}