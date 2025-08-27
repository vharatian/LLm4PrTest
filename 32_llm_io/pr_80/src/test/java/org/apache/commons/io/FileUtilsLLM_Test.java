package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testSizeOfDirectory0() throws IOException {
        File directory = new File("testDir");
        File file1 = new File(directory, "file1.txt");
        File file2 = new File(directory, "file2.txt");
        File symlink = new File(directory, "symlink");

        try {
            directory.mkdir();
            file1.createNewFile();
            file2.createNewFile();
            Files.createSymbolicLink(symlink.toPath(), file1.toPath());

            long size = FileUtils.sizeOfDirectory(directory);
            assertEquals(file1.length() + file2.length(), size);
        } finally {
            file1.delete();
            file2.delete();
            symlink.delete();
            directory.delete();
        }
    }

    @Test
    public void testSizeOfDirectoryBig0() throws IOException {
        File directory = new File("testDir");
        File file1 = new File(directory, "file1.txt");
        File file2 = new File(directory, "file2.txt");
        File symlink = new File(directory, "symlink");

        try {
            directory.mkdir();
            file1.createNewFile();
            file2.createNewFile();
            Files.createSymbolicLink(symlink.toPath(), file1.toPath());

            BigInteger size = FileUtils.sizeOfDirectoryAsBigInteger(directory);
            assertEquals(BigInteger.valueOf(file1.length() + file2.length()), size);
        } finally {
            file1.delete();
            file2.delete();
            symlink.delete();
            directory.delete();
        }
    }

    @Test
    public void testIsSymlink() throws IOException {
        File file = new File("testFile.txt");
        File symlink = new File("symlink");

        try {
            file.createNewFile();
            Files.createSymbolicLink(symlink.toPath(), file.toPath());

            assertFalse(FileUtils.isSymlink(file));
            assertTrue(FileUtils.isSymlink(symlink));
        } finally {
            file.delete();
            symlink.delete();
        }
    }
}