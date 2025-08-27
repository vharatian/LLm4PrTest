package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileRandomAccessOutputStreamLLM_Test {

    private Path tempFile;
    private FileRandomAccessOutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("test", ".tmp");
        outputStream = new FileRandomAccessOutputStream(tempFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        outputStream.close();
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteAndPosition() throws IOException {
        byte[] data = "Hello, World!".getBytes();
        outputStream.write(data, 0, data.length);
        assertEquals(data.length, outputStream.position());
    }

    @Test
    void testWriteFullyAt() throws IOException {
        byte[] data = "Hello, World!".getBytes();
        outputStream.writeFullyAt(data, 0, data.length, 5);
        ByteBuffer buffer = ByteBuffer.allocate(data.length + 5);
        try (FileChannel channel = FileChannel.open(tempFile, StandardOpenOption.READ)) {
            channel.read(buffer);
        }
        buffer.flip();
        byte[] expected = new byte[5 + data.length];
        System.arraycopy(data, 0, expected, 5, data.length);
        assertEquals(ByteBuffer.wrap(expected), buffer);
    }

    @Test
    void testWriteFullyAtThrowsIOException() throws IOException {
        byte[] data = "Hello, World!".getBytes();
        outputStream.close();
        assertThrows(IOException.class, () -> outputStream.writeFullyAt(data, 0, data.length, 5));
    }

    @Test
    void testClose() throws IOException {
        outputStream.close();
        assertThrows(IOException.class, () -> outputStream.write("test".getBytes(), 0, 4));
    }
}