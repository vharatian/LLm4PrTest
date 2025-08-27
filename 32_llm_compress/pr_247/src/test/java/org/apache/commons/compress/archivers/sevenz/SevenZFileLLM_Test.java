package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;

import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.jupiter.api.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testSanityCheckFilesInfoWithNegativeEmptyStreams() throws IOException {
        // Create a mock ByteBuffer with the necessary data to simulate the scenario
        ByteBuffer buffer = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put((byte) NID.kEmptyStream);
        buffer.putLong(1); // size
        buffer.put((byte) 0); // end
        buffer.flip();

        // Create a mock ArchiveStatistics object
        SevenZFile.ArchiveStatistics stats = new SevenZFile.ArchiveStatistics();
        stats.numberOfEntries = 1;

        // Call the method with the mock data
        SevenZFile sevenZFile = new SevenZFile(new SeekableInMemoryByteChannel(new byte[0]));
        sevenZFile.sanityCheckFilesInfo(buffer, stats);

        // Assert the expected behavior
        assertEquals(1, stats.numberOfEntriesWithStream);
    }

    @Test
    public void testSanityCheckFilesInfoWithPositiveEmptyStreams() throws IOException {
        // Create a mock ByteBuffer with the necessary data to simulate the scenario
        ByteBuffer buffer = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put((byte) NID.kEmptyStream);
        buffer.putLong(1); // size
        buffer.put((byte) 1); // one empty stream
        buffer.put((byte) 0); // end
        buffer.flip();

        // Create a mock ArchiveStatistics object
        SevenZFile.ArchiveStatistics stats = new SevenZFile.ArchiveStatistics();
        stats.numberOfEntries = 1;

        // Call the method with the mock data
        SevenZFile sevenZFile = new SevenZFile(new SeekableInMemoryByteChannel(new byte[0]));
        sevenZFile.sanityCheckFilesInfo(buffer, stats);

        // Assert the expected behavior
        assertEquals(0, stats.numberOfEntriesWithStream);
    }
}