package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.File;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ZipSplitReadOnlySeekableByteChannelLLM_Test {

    private File tempDir;
    private File z01;
    private File z02;
    private File zip;

    @BeforeEach
    public void setUp() throws IOException {
        tempDir = Files.createTempDirectory("zipSplitTest").toFile();
        z01 = new File(tempDir, "test.z01");
        z02 = new File(tempDir, "test.z02");
        zip = new File(tempDir, "test.zip");

        Files.write(z01.toPath(), new byte[]{0x08, 0x07, 0x4b, 0x50, 0x01, 0x02, 0x03}, StandardOpenOption.CREATE);
        Files.write(z02.toPath(), new byte[]{0x04, 0x05, 0x06}, StandardOpenOption.CREATE);
        Files.write(zip.toPath(), new byte[]{0x07, 0x08, 0x09}, StandardOpenOption.CREATE);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(z01.toPath());
        Files.deleteIfExists(z02.toPath());
        Files.deleteIfExists(zip.toPath());
        Files.deleteIfExists(tempDir.toPath());
    }

    @Test
    public void testValidSplitSignature() throws IOException {
        List<SeekableByteChannel> channels = Arrays.asList(
                Files.newByteChannel(z01.toPath(), StandardOpenOption.READ),
                Files.newByteChannel(z02.toPath(), StandardOpenOption.READ),
                Files.newByteChannel(zip.toPath(), StandardOpenOption.READ)
        );

        assertDoesNotThrow(() -> new ZipSplitReadOnlySeekableByteChannel(channels));
    }

    @Test
    public void testInvalidSplitSignature() throws IOException {
        Files.write(z01.toPath(), new byte[]{0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03}, StandardOpenOption.CREATE);

        List<SeekableByteChannel> channels = Arrays.asList(
                Files.newByteChannel(z01.toPath(), StandardOpenOption.READ),
                Files.newByteChannel(z02.toPath(), StandardOpenOption.READ),
                Files.newByteChannel(zip.toPath(), StandardOpenOption.READ)
        );

        IOException exception = assertThrows(IOException.class, () -> new ZipSplitReadOnlySeekableByteChannel(channels));
        assertEquals("The first zip split segment is not begin with split zip file signature", exception.getMessage());
    }

    @Test
    public void testForSeekableByteChannelsSingleChannel() throws IOException {
        SeekableByteChannel channel = Files.newByteChannel(zip.toPath(), StandardOpenOption.READ);
        SeekableByteChannel result = ZipSplitReadOnlySeekableByteChannel.forSeekableByteChannels(channel);

        assertEquals(channel, result);
    }

    @Test
    public void testForSeekableByteChannelsMultipleChannels() throws IOException {
        SeekableByteChannel channel1 = Files.newByteChannel(z01.toPath(), StandardOpenOption.READ);
        SeekableByteChannel channel2 = Files.newByteChannel(z02.toPath(), StandardOpenOption.READ);
        SeekableByteChannel channel3 = Files.newByteChannel(zip.toPath(), StandardOpenOption.READ);

        SeekableByteChannel result = ZipSplitReadOnlySeekableByteChannel.forSeekableByteChannels(channel1, channel2, channel3);

        assertNotNull(result);
        assertTrue(result instanceof ZipSplitReadOnlySeekableByteChannel);
    }

    @Test
    public void testBuildFromLastSplitSegment() throws IOException {
        SeekableByteChannel result = ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(zip);

        assertNotNull(result);
        assertTrue(result instanceof ZipSplitReadOnlySeekableByteChannel);
    }

    @Test
    public void testBuildFromLastSplitSegmentInvalidExtension() {
        File invalidFile = new File(tempDir, "test.txt");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(invalidFile));
        assertEquals("The extension of last zip splite segment should be .zip", exception.getMessage());
    }
}