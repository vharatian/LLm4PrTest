package org.apache.commons.io.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BufferedFileChannelInputStreamLLM_Test extends AbstractInputStreamTest {

    private Path tempFile;

    @SuppressWarnings("resource")
    @Override
    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        inputStreams = new InputStream[]{
            new BufferedFileChannelInputStream(inputFile),
            new BufferedFileChannelInputStream(inputFile, 123)
        };
        tempFile = Files.createTempFile("test", ".tmp");
        Files.write(tempFile, "Test data for BufferedFileChannelInputStream".getBytes());
    }

    @Test
    public void testRefillReturnsTrueWhenDataIsAvailable() throws IOException {
        try (BufferedFileChannelInputStream stream = new BufferedFileChannelInputStream(tempFile.toFile())) {
            assertTrue(stream.read() != -1, "Stream should have data available");
        }
    }

    @Test
    public void testRefillReturnsFalseWhenNoDataIsAvailable() throws IOException {
        File emptyFile = Files.createTempFile("empty", ".tmp").toFile();
        try (BufferedFileChannelInputStream stream = new BufferedFileChannelInputStream(emptyFile)) {
            assertEquals(-1, stream.read(), "Stream should not have data available");
        }
    }
}