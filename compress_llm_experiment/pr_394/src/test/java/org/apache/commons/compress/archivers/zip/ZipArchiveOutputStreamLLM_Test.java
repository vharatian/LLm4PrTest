package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import static org.junit.Assert.*;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testZipArchiveOutputStreamWithPathAndOptions() throws IOException {
        Path tempFile = Files.createTempFile("testZip", ".zip");
        tempFile.toFile().deleteOnExit();
        
        // Test with valid options
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            assertNotNull(zos);
            assertTrue(zos.isSeekable());
        }

        // Test with invalid options
        try {
            new ZipArchiveOutputStream(tempFile, StandardOpenOption.READ);
            fail("Expected an IOException to be thrown");
        } catch (IOException e) {
            // Expected exception
        }
    }

    @Test
    public void testZipArchiveOutputStreamWithFile() throws IOException {
        File tempFile = File.createTempFile("testZip", ".zip");
        tempFile.deleteOnExit();
        
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile)) {
            assertNotNull(zos);
            assertFalse(zos.isSeekable());
        }
    }

    @Test
    public void testZipArchiveOutputStreamWithOutputStream() throws IOException {
        OutputStream os = Files.newOutputStream(Files.createTempFile("testZip", ".zip"));
        
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(os)) {
            assertNotNull(zos);
            assertFalse(zos.isSeekable());
        }
    }

    @Test
    public void testZipArchiveOutputStreamWithSeekableByteChannel() throws IOException {
        Path tempFile = Files.createTempFile("testZip", ".zip");
        tempFile.toFile().deleteOnExit();
        SeekableByteChannel channel = Files.newByteChannel(tempFile, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE));
        
        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(channel)) {
            assertNotNull(zos);
            assertTrue(zos.isSeekable());
        }
    }
}