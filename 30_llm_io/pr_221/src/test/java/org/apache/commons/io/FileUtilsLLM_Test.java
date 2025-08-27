package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testChecksum() throws IOException {
        File file = new File("testFile.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("test content");
        }
        Checksum checksum = new CRC32();
        Checksum result = FileUtils.checksum(file, checksum);
        assertNotNull(result);
        assertEquals(checksum.getValue(), result.getValue());
        file.delete();
    }

    @Test
    public void testContentEquals() throws IOException {
        File file1 = new File("testFile1.txt");
        File file2 = new File("testFile2.txt");
        try (FileWriter writer1 = new FileWriter(file1); FileWriter writer2 = new FileWriter(file2)) {
            writer1.write("test content");
            writer2.write("test content");
        }
        boolean result = FileUtils.contentEquals(file1, file2);
        assertTrue(result);
        file1.delete();
        file2.delete();
    }

    @Test
    public void testContentEqualsIgnoreEOL() throws IOException {
        File file1 = new File("testFile1.txt");
        File file2 = new File("testFile2.txt");
        try (FileWriter writer1 = new FileWriter(file1); FileWriter writer2 = new FileWriter(file2)) {
            writer1.write("test content\n");
            writer2.write("test content\r\n");
        }
        boolean result = FileUtils.contentEqualsIgnoreEOL(file1, file2, "UTF-8");
        assertTrue(result);
        file1.delete();
        file2.delete();
    }

    @Test
    public void testCopyFile() throws IOException {
        File srcFile = new File("srcFile.txt");
        File destFile = new File("destFile.txt");
        try (FileWriter writer = new FileWriter(srcFile)) {
            writer.write("test content");
        }
        FileUtils.copyFile(srcFile, destFile);
        assertTrue(destFile.exists());
        assertEquals(srcFile.length(), destFile.length());
        srcFile.delete();
        destFile.delete();
    }
}