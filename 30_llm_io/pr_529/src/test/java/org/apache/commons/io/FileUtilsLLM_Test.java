package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testChecksumFileNotFound() {
        File nonExistentFile = new File("non-existent-file.txt");
        Checksum checksum = new CRC32();
        assertThrows(FileNotFoundException.class, () -> FileUtils.checksum(nonExistentFile, checksum));
    }

    @Test
    public void testContentEqualsFileNotFound() {
        File nonExistentFile1 = new File("non-existent-file1.txt");
        File nonExistentFile2 = new File("non-existent-file2.txt");
        assertThrows(FileNotFoundException.class, () -> FileUtils.contentEquals(nonExistentFile1, nonExistentFile2));
    }

    @Test
    public void testContentEqualsIgnoreEOLFileNotFound() {
        File nonExistentFile1 = new File("non-existent-file1.txt");
        File nonExistentFile2 = new File("non-existent-file2.txt");
        assertThrows(FileNotFoundException.class, () -> FileUtils.contentEqualsIgnoreEOL(nonExistentFile1, nonExistentFile2, "UTF-8"));
    }

    @Test
    public void testCopyDirectoryRequireDirectoryExists() {
        File nonExistentDir = new File("non-existent-dir");
        File destDir = new File("dest-dir");
        assertThrows(FileNotFoundException.class, () -> FileUtils.copyDirectory(nonExistentDir, destDir));
    }

    @Test
    public void testCopyFileRequireFileExists() {
        File nonExistentFile = new File("non-existent-file.txt");
        File destFile = new File("dest-file.txt");
        assertThrows(FileNotFoundException.class, () -> FileUtils.copyFile(nonExistentFile, destFile));
    }

    @Test
    public void testIsFileNewerUncheckedIOException() {
        File file = new File("file.txt");
        File nonExistentReference = new File("non-existent-reference.txt");
        assertThrows(UncheckedIOException.class, () -> FileUtils.isFileNewer(file, nonExistentReference));
    }

    @Test
    public void testIsFileOlderUncheckedIOException() {
        File file = new File("file.txt");
        File nonExistentReference = new File("non-existent-reference.txt");
        assertThrows(UncheckedIOException.class, () -> FileUtils.isFileOlder(file, nonExistentReference));
    }

    @Test
    public void testMoveDirectoryRequireDirectoryExists() {
        File nonExistentDir = new File("non-existent-dir");
        File destDir = new File("dest-dir");
        assertThrows(FileNotFoundException.class, () -> FileUtils.moveDirectory(nonExistentDir, destDir));
    }

    @Test
    public void testMoveFileRequireFileExists() {
        File nonExistentFile = new File("non-existent-file.txt");
        File destFile = new File("dest-file.txt");
        assertThrows(FileNotFoundException.class, () -> FileUtils.moveFile(nonExistentFile, destFile));
    }

    @Test
    public void testSizeOfDirectoryUncheckedIOException() {
        File nonExistentDir = new File("non-existent-dir");
        assertThrows(UncheckedIOException.class, () -> FileUtils.sizeOfDirectory(nonExistentDir));
    }

    @Test
    public void testSizeOfDirectoryAsBigIntegerUncheckedIOException() {
        File nonExistentDir = new File("non-existent-dir");
        assertThrows(UncheckedIOException.class, () -> FileUtils.sizeOfDirectoryAsBigInteger(nonExistentDir));
    }

    @Test
    public void testRequireDirectoryExists() {
        File nonExistentDir = new File("non-existent-dir");
        assertThrows(FileNotFoundException.class, () -> {
            FileUtils.class.getDeclaredMethod("requireDirectoryExists", File.class, String.class)
                .setAccessible(true);
            FileUtils.class.getDeclaredMethod("requireDirectoryExists", File.class, String.class)
                .invoke(null, nonExistentDir, "nonExistentDir");
        });
    }

    @Test
    public void testRequireDirectoryIfExists() {
        File nonExistentDir = new File("non-existent-dir");
        assertThrows(FileNotFoundException.class, () -> {
            FileUtils.class.getDeclaredMethod("requireDirectoryIfExists", File.class, String.class)
                .setAccessible(true);
            FileUtils.class.getDeclaredMethod("requireDirectoryIfExists", File.class, String.class)
                .invoke(null, nonExistentDir, "nonExistentDir");
        });
    }

    @Test
    public void testCheckFileObjectExists() {
        File nonExistentFile = new File("non-existent-file.txt");
        assertThrows(FileNotFoundException.class, () -> {
            FileUtils.class.getDeclaredMethod("checkFileObjectExists", File.class, String.class)
                .setAccessible(true);
            FileUtils.class.getDeclaredMethod("checkFileObjectExists", File.class, String.class)
                .invoke(null, nonExistentFile, "nonExistentFile");
        });
    }

    @Test
    public void testCheckFileExists() {
        File nonExistentFile = new File("non-existent-file.txt");
        assertThrows(FileNotFoundException.class, () -> {
            FileUtils.class.getDeclaredMethod("checkFileExists", File.class, String.class)
                .setAccessible(true);
            FileUtils.class.getDeclaredMethod("checkFileExists", File.class, String.class)
                .invoke(null, nonExistentFile, "nonExistentFile");
        });
    }
}