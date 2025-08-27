package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testCopyDirectoryToDirectory_NullSource() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            FileUtils.copyDirectoryToDirectory(null, new File("destDir"));
        });
        assertEquals("Source must not be null", exception.getMessage());
    }

    @Test
    public void testCopyDirectoryToDirectory_SourceNotDirectory() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FileUtils.copyDirectoryToDirectory(new File("srcFile"), new File("destDir"));
        });
        assertEquals("Source 'srcFile' is not a directory", exception.getMessage());
    }

    @Test
    public void testCopyDirectoryToDirectory_NullDestination() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            FileUtils.copyDirectoryToDirectory(new File("srcDir"), null);
        });
        assertEquals("Destination must not be null", exception.getMessage());
    }

    @Test
    public void testCopyDirectoryToDirectory_DestinationNotDirectory() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            File srcDir = new File("srcDir");
            srcDir.mkdir();
            File destFile = new File("destFile");
            destFile.createNewFile();
            FileUtils.copyDirectoryToDirectory(srcDir, destFile);
        });
        assertEquals("Destination 'destFile' is not a directory", exception.getMessage());
    }

    @Test
    public void testCopyDirectoryToDirectory_ValidDirectories() throws IOException {
        File srcDir = new File("srcDir");
        File destDir = new File("destDir");
        srcDir.mkdir();
        destDir.mkdir();
        FileUtils.copyDirectoryToDirectory(srcDir, destDir);
        File copiedDir = new File(destDir, srcDir.getName());
        assertTrue(copiedDir.exists() && copiedDir.isDirectory());
    }
}