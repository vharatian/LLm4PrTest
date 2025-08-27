package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testChecksumFileNotFoundException() {
        File nonExistentFile = new File("non-existent-file.txt");
        assertThrows(FileNotFoundException.class, () -> FileUtils.checksum(nonExistentFile, new CRC32()));
    }

    @Test
    public void testContentEqualsFileNotFoundException() {
        File nonExistentFile = new File("non-existent-file.txt");
        File existentFile = new File("existent-file.txt");
        try {
            Files.createFile(existentFile.toPath());
            assertThrows(FileNotFoundException.class, () -> FileUtils.contentEquals(nonExistentFile, existentFile));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            existentFile.delete();
        }
    }

    @Test
    public void testContentEqualsIllegalArgumentException() {
        File directory = new File("test-directory");
        directory.mkdir();
        try {
            assertThrows(IllegalArgumentException.class, () -> FileUtils.contentEquals(directory, directory));
        } finally {
            directory.delete();
        }
    }

    @Test
    public void testCopyDirectoryNullDestination() {
        File srcDir = new File("src-directory");
        srcDir.mkdir();
        try {
            assertThrows(NullPointerException.class, () -> FileUtils.copyDirectory(srcDir, null));
        } finally {
            srcDir.delete();
        }
    }

    @Test
    public void testCopyDirectoryToDirectoryNullSource() {
        File destDir = new File("dest-directory");
        destDir.mkdir();
        try {
            assertThrows(NullPointerException.class, () -> FileUtils.copyDirectoryToDirectory(null, destDir));
        } finally {
            destDir.delete();
        }
    }

    @Test
    public void testCopyFileNullDestination() {
        File srcFile = new File("src-file.txt");
        try {
            Files.createFile(srcFile.toPath());
            assertThrows(NullPointerException.class, () -> FileUtils.copyFile(srcFile, null));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            srcFile.delete();
        }
    }

    @Test
    public void testDirectoryContainsChildNotExists() {
        File directory = new File("test-directory");
        File child = new File("child-file.txt");
        directory.mkdir();
        try {
            assertFalse(FileUtils.directoryContains(directory, child));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            directory.delete();
        }
    }

    @Test
    public void testForceDeleteFileNotFoundException() {
        File nonExistentFile = new File("non-existent-file.txt");
        assertThrows(FileNotFoundException.class, () -> FileUtils.forceDelete(nonExistentFile));
    }

    @Test
    public void testIsFileNewerUncheckedIOException() {
        File file = new File("test-file.txt");
        try {
            Files.createFile(file.toPath());
            assertThrows(UncheckedIOException.class, () -> FileUtils.isFileNewer(file, new Date(Long.MAX_VALUE)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }

    @Test
    public void testIsFileOlderUncheckedIOException() {
        File file = new File("test-file.txt");
        try {
            Files.createFile(file.toPath());
            assertThrows(UncheckedIOException.class, () -> FileUtils.isFileOlder(file, new Date(Long.MAX_VALUE)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.delete();
        }
    }

    @Test
    public void testMoveDirectoryNullDestination() {
        File srcDir = new File("src-directory");
        srcDir.mkdir();
        try {
            assertThrows(NullPointerException.class, () -> FileUtils.moveDirectory(srcDir, null));
        } finally {
            srcDir.delete();
        }
    }

    @Test
    public void testMoveFileNullDestination() {
        File srcFile = new File("src-file.txt");
        try {
            Files.createFile(srcFile.toPath());
            assertThrows(NullPointerException.class, () -> FileUtils.moveFile(srcFile, null));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            srcFile.delete();
        }
    }

    @Test
    public void testOpenOutputStreamIllegalArgumentException() {
        File directory = new File("test-directory");
        directory.mkdir();
        try {
            assertThrows(IllegalArgumentException.class, () -> FileUtils.openOutputStream(directory));
        } finally {
            directory.delete();
        }
    }
}