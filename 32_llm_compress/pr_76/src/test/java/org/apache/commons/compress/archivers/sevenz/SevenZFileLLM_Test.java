package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.Test;

public class SevenZFileLLM_Test extends AbstractTestCase {

    @Test
    public void testSevenZFileWithMemoryLimitCharPassword() throws Exception {
        File file = getFile("bla.7z");
        int maxMemoryLimitInKb = 1024; // 1MB limit
        try (SevenZFile sevenZFile = new SevenZFile(file, "password".toCharArray(), maxMemoryLimitInKb)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test1.xml", entry.getName());
        }
    }

    @Test
    public void testSevenZFileWithMemoryLimitBytePassword() throws Exception {
        File file = getFile("bla.7z");
        int maxMemoryLimitInKb = 1024; // 1MB limit
        try (SevenZFile sevenZFile = new SevenZFile(file, "password".getBytes("UTF-16LE"), maxMemoryLimitInKb)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test1.xml", entry.getName());
        }
    }

    @Test
    public void testSevenZFileWithMemoryLimitNoPassword() throws Exception {
        File file = getFile("bla.7z");
        int maxMemoryLimitInKb = 1024; // 1MB limit
        try (SevenZFile sevenZFile = new SevenZFile(file, maxMemoryLimitInKb)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test1.xml", entry.getName());
        }
    }

    @Test
    public void testSevenZFileWithMemoryLimitSeekableByteChannelCharPassword() throws Exception {
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(getFile("bla.7z"))) {
            data = IOUtils.toByteArray(fis);
        }
        int maxMemoryLimitInKb = 1024; // 1MB limit
        try (SevenZFile sevenZFile = new SevenZFile(new SeekableInMemoryByteChannel(data), "password".toCharArray(), maxMemoryLimitInKb)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test1.xml", entry.getName());
        }
    }

    @Test
    public void testSevenZFileWithMemoryLimitSeekableByteChannelBytePassword() throws Exception {
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(getFile("bla.7z"))) {
            data = IOUtils.toByteArray(fis);
        }
        int maxMemoryLimitInKb = 1024; // 1MB limit
        try (SevenZFile sevenZFile = new SevenZFile(new SeekableInMemoryByteChannel(data), "password".getBytes("UTF-16LE"), maxMemoryLimitInKb)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test1.xml", entry.getName());
        }
    }

    @Test
    public void testSevenZFileWithMemoryLimitSeekableByteChannelNoPassword() throws Exception {
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(getFile("bla.7z"))) {
            data = IOUtils.toByteArray(fis);
        }
        int maxMemoryLimitInKb = 1024; // 1MB limit
        try (SevenZFile sevenZFile = new SevenZFile(new SeekableInMemoryByteChannel(data), maxMemoryLimitInKb)) {
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            assertNotNull(entry);
            assertEquals("test1.xml", entry.getName());
        }
    }
}