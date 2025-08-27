package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

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

    private List<SeekableByteChannel> channels;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        channels = new ArrayList<>();
        tempFile = Files.createTempFile("test", ".zip");
        SeekableByteChannel channel = Files.newByteChannel(tempFile, StandardOpenOption.READ, StandardOpenOption.WRITE);
        channels.add(channel);
    }

    @AfterEach
    public void tearDown() throws IOException {
        for (SeekableByteChannel channel : channels) {
            channel.close();
        }
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testAssertSplitSignatureWithValidSignature() throws IOException {
        // Write the valid split signature to the temp file
        byte[] validSignature = new byte[]{0x08, 0x07, 0x4B, 0x50};
        Files.write(tempFile, validSignature);

        // Create the ZipSplitReadOnlySeekableByteChannel instance
        ZipSplitReadOnlySeekableByteChannel zipChannel = new ZipSplitReadOnlySeekableByteChannel(channels);

        // No exception should be thrown, indicating the signature is valid
        assertNotNull(zipChannel);
    }

    @Test
    public void testAssertSplitSignatureWithInvalidSignature() {
        // Write an invalid split signature to the temp file
        byte[] invalidSignature = new byte[]{0x00, 0x00, 0x00, 0x00};
        try {
            Files.write(tempFile, invalidSignature);
        } catch (IOException e) {
            fail("Failed to write to temp file");
        }

        // Expect an IOException to be thrown due to invalid signature
        IOException exception = assertThrows(IOException.class, () -> {
            new ZipSplitReadOnlySeekableByteChannel(channels);
        });

        assertEquals("The first zip split segment does not begin with split zip file signature", exception.getMessage());
    }
}