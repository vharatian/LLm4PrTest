package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testConstructorWithFileAndPassword() throws IOException {
        File file = new File("test.7z");
        char[] password = "password".toCharArray();
        try (SevenZFile sevenZFile = new SevenZFile(file, password)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithFileAndPasswordAndOptions() throws IOException {
        File file = new File("test.7z");
        char[] password = "password".toCharArray();
        SevenZFileOptions options = SevenZFileOptions.builder().build();
        try (SevenZFile sevenZFile = new SevenZFile(file, password, options)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndPassword() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        char[] password = "password".toCharArray();
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), password)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndPasswordAndOptions() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        char[] password = "password".toCharArray();
        SevenZFileOptions options = SevenZFileOptions.builder().build();
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), password, options)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndFileNameAndPassword() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        char[] password = "password".toCharArray();
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), "test.7z", password)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndFileNameAndPasswordAndOptions() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        char[] password = "password".toCharArray();
        SevenZFileOptions options = SevenZFileOptions.builder().build();
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), "test.7z", password, options)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndFileName() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), "test.7z")) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndFileNameAndOptions() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        SevenZFileOptions options = SevenZFileOptions.builder().build();
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), "test.7z", options)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndDeprecatedPassword() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        byte[] password = "password".getBytes(StandardCharsets.UTF_8);
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), password)) {
            assertNotNull(sevenZFile);
        }
    }

    @Test
    public void testConstructorWithSeekableByteChannelAndFileNameAndDeprecatedPassword() throws IOException {
        Path path = Files.createTempFile("test", ".7z");
        byte[] password = "password".getBytes(StandardCharsets.UTF_8);
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(path), "test.7z", password)) {
            assertNotNull(sevenZFile);
        }
    }
}