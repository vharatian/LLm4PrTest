package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ZipSplitReadOnlySeekableByteChannelLLM_Test {

    @Test
    public void testBuildFromLastSplitSegment() throws IOException {
        // Setup temporary files to simulate split zip segments
        Path tempDir = Files.createTempDirectory("zipSplitTest");
        File lastSegmentFile = Files.createFile(tempDir.resolve("test.zip")).toFile();
        File segment1 = Files.createFile(tempDir.resolve("test.z01")).toFile();
        File segment2 = Files.createFile(tempDir.resolve("test.z02")).toFile();

        // Ensure the files are created
        assertTrue(lastSegmentFile.exists());
        assertTrue(segment1.exists());
        assertTrue(segment2.exists());

        // Call the method under test
        SeekableByteChannel channel = ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(lastSegmentFile);

        // Verify the channel is not null
        assertNotNull(channel);

        // Clean up temporary files
        Files.deleteIfExists(lastSegmentFile.toPath());
        Files.deleteIfExists(segment1.toPath());
        Files.deleteIfExists(segment2.toPath());
        Files.deleteIfExists(tempDir);
    }

    @Test
    public void testZipSplitSegmentComparator() {
        // Create dummy files with names that simulate split zip segments
        File file1 = new File("test.z01");
        File file2 = new File("test.z02");
        File file3 = new File("test.z03");

        List<File> files = new ArrayList<>();
        files.add(file2);
        files.add(file3);
        files.add(file1);

        // Sort using the comparator
        files.sort(new ZipSplitReadOnlySeekableByteChannel.ZipSplitSegmentComparator());

        // Verify the order
        assertEquals("test.z01", files.get(0).getName());
        assertEquals("test.z02", files.get(1).getName());
        assertEquals("test.z03", files.get(2).getName());
    }
}