package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.junit.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testReadFolderWithPackedStreamsArray() throws IOException {
        ByteBuffer header = ByteBuffer.allocate(1024);
        // Simulate the header data for the test
        header.put((byte) 1); // numCoders
        header.put((byte) 1); // idSize
        header.put((byte) 0); // isSimple
        header.put((byte) 0); // hasAttributes
        header.put((byte) 0); // moreAlternativeMethods
        header.put(new byte[] { 0x21 }); // decompressionMethodId
        header.put((byte) 1); // numInStreams
        header.put((byte) 1); // numOutStreams
        header.put((byte) 0); // numBindPairs
        header.put((byte) 1); // numPackedStreams
        header.put((byte) 0); // packedStreams

        header.flip();
        SevenZFile sevenZFile = new SevenZFile(null); // Use a mock or null for the channel
        SevenZFile.Folder folder = sevenZFile.readFolder(header);

        assertNotNull(folder);
        assertEquals(1, folder.totalInputStreams);
        assertEquals(1, folder.totalOutputStreams);
        assertEquals(1, folder.packedStreams.length);
        assertEquals(0, folder.packedStreams[0]);
    }
}