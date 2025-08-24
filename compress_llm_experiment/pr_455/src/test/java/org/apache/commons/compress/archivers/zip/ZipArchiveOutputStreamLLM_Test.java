package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.Deflater;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZipArchiveOutputStreamLLM_Test {

    private ZipArchiveOutputStream zipArchiveOutputStream;
    private OutputStream mockOutputStream;
    private SeekableByteChannel mockChannel;
    private Path mockPath;

    @BeforeEach
    public void setUp() throws IOException {
        mockOutputStream = mock(OutputStream.class);
        mockChannel = mock(SeekableByteChannel.class);
        mockPath = mock(Path.class);
    }

    @Test
    public void testConstructorWithOutputStream() {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockOutputStream);
        assertNotNull(zipArchiveOutputStream);
    }

    @Test
    public void testConstructorWithPathAndOptions() throws IOException {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockPath, StandardOpenOption.CREATE);
        assertNotNull(zipArchiveOutputStream);
    }

    @Test
    public void testConstructorWithSeekableByteChannel() {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockChannel);
        assertNotNull(zipArchiveOutputStream);
    }

    @Test
    public void testCloseEntryWithRandomAccessOutputStream() throws IOException {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockChannel);
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        zipArchiveOutputStream.putArchiveEntry(entry);
        zipArchiveOutputStream.write(new byte[]{1, 2, 3});
        zipArchiveOutputStream.closeArchiveEntry();
        verify(mockChannel, atLeastOnce()).position(anyLong());
    }

    @Test
    public void testRewriteSizesAndCrc() throws IOException {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockChannel);
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        zipArchiveOutputStream.putArchiveEntry(entry);
        zipArchiveOutputStream.write(new byte[]{1, 2, 3});
        zipArchiveOutputStream.closeArchiveEntry();
        // Assuming RandomAccessOutputStream is a mockable class
        RandomAccessOutputStream mockRandomAccessOutputStream = mock(RandomAccessOutputStream.class);
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockRandomAccessOutputStream);
        zipArchiveOutputStream.putArchiveEntry(entry);
        zipArchiveOutputStream.write(new byte[]{1, 2, 3});
        zipArchiveOutputStream.closeArchiveEntry();
        verify(mockRandomAccessOutputStream, atLeastOnce()).writeFullyAt(any(byte[].class), anyLong());
    }

    @Test
    public void testIsSeekable() throws IOException {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockChannel);
        assertTrue(zipArchiveOutputStream.isSeekable());
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockOutputStream);
        assertFalse(zipArchiveOutputStream.isSeekable());
    }

    @Test
    public void testGetEffectiveZip64Mode() throws IOException {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockChannel);
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setMethod(Deflater.DEFLATED);
        entry.setSize(ArchiveEntry.SIZE_UNKNOWN);
        assertEquals(Zip64Mode.Never, zipArchiveOutputStream.getEffectiveZip64Mode(entry));
    }

    @Test
    public void testValidateSizeInformation() throws IOException {
        zipArchiveOutputStream = new ZipArchiveOutputStream(mockChannel);
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setMethod(ZipArchiveOutputStream.STORED);
        zipArchiveOutputStream.putArchiveEntry(entry);
        assertThrows(ZipException.class, () -> zipArchiveOutputStream.validateSizeInformation(Zip64Mode.Never));
    }
}