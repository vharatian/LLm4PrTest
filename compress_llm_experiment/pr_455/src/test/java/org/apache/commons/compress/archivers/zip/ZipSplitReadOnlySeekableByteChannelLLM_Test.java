package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.nio.file.OpenOption;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipSplitReadOnlySeekableByteChannelLLM_Test {

    @Test
    public void testForPathsWithOpenOptions() throws IOException {
        Path tempFile1 = Files.createTempFile("testfile1", ".z01");
        Path tempFile2 = Files.createTempFile("testfile2", ".zip");

        List<Path> paths = Arrays.asList(tempFile1, tempFile2);
        OpenOption[] openOptions = new OpenOption[]{StandardOpenOption.READ};

        try (SeekableByteChannel channel = ZipSplitReadOnlySeekableByteChannel.forPaths(paths, openOptions)) {
            assertTrue(channel.isOpen());
        } finally {
            Files.deleteIfExists(tempFile1);
            Files.deleteIfExists(tempFile2);
        }
    }

    @Test
    public void testForPathsWithNullPaths() {
        OpenOption[] openOptions = new OpenOption[]{StandardOpenOption.READ};
        assertThrows(NullPointerException.class, () -> {
            ZipSplitReadOnlySeekableByteChannel.forPaths(null, openOptions);
        });
    }

    @Test
    public void testForPathsWithEmptyPaths() throws IOException {
        List<Path> paths = Arrays.asList();
        OpenOption[] openOptions = new OpenOption[]{StandardOpenOption.READ};

        assertThrows(IllegalArgumentException.class, () -> {
            ZipSplitReadOnlySeekableByteChannel.forPaths(paths, openOptions);
        });
    }

    @Test
    public void testForPathsWithInvalidOpenOption() throws IOException {
        Path tempFile1 = Files.createTempFile("testfile1", ".z01");
        Path tempFile2 = Files.createTempFile("testfile2", ".zip");

        List<Path> paths = Arrays.asList(tempFile1, tempFile2);
        OpenOption[] openOptions = new OpenOption[]{StandardOpenOption.WRITE};

        assertThrows(IOException.class, () -> {
            ZipSplitReadOnlySeekableByteChannel.forPaths(paths, openOptions);
        });

        Files.deleteIfExists(tempFile1);
        Files.deleteIfExists(tempFile2);
    }
}