package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
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
            assertTrue(Files.getPosixFilePermissions(file).contains(PosixFilePermission.OWNER_READ));
        }
    }

    @Test
    public void testSetReadOnlyThrowsIOException() throws IOException {
        Path file = Files.createFile(tempDir.resolve("testFile.txt"));
        Files.delete(file); // Ensure the file does not exist
        assertThrows(IOException.class, () -> PathUtils.setReadOnly(file, true));
    }
}