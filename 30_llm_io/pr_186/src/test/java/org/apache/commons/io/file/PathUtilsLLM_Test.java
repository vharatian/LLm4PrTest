package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.util.List;
import org.apache.commons.io.IOExceptionList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class PathUtilsLLM_Test {

    @TempDir
    public Path tempDir;

    @Test
    public void testSetReadOnlyWithDosFileAttributeView() throws IOException {
        Path file = Files.createFile(tempDir.resolve("testFile.txt"));
        DosFileAttributeView dosView = Files.getFileAttributeView(file, DosFileAttributeView.class);
        if (dosView != null) {
            PathUtils.setReadOnly(file, true);
            assertTrue(dosView.readAttributes().isReadOnly());
        }
    }

    @Test
    public void testSetReadOnlyWithPosixFileAttributeView() throws IOException {
        Path file = Files.createFile(tempDir.resolve("testFile.txt"));
        PosixFileAttributeView posixView = Files.getFileAttributeView(file, PosixFileAttributeView.class);
        if (posixView != null) {
            PathUtils.setReadOnly(file, true);
            assertTrue(Files.getPosixFilePermissions(file).contains(PosixFilePermission.OWNER_WRITE) == false);
        }
    }

    @Test
    public void testSetReadOnlyWithIOExceptionList() throws IOException {
        Path file = Files.createFile(tempDir.resolve("testFile.txt"));
        // Simulate failure by using a path that does not support DosFileAttributeView or PosixFileAttributeView
        Path invalidPath = tempDir.resolve("invalidFile.txt");
        Files.createFile(invalidPath);
        assertThrows(IOExceptionList.class, () -> PathUtils.setReadOnly(invalidPath, true));
    }
}