package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;

import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test {

    @Test
    public void testReadAttributes_NullPath() {
        BasicFileAttributes attributes = PathUtils.readAttributes(null, BasicFileAttributes.class);
        assertNull(attributes, "Attributes should be null for null path");
    }

    @Test
    public void testReadAttributes_UnsupportedOperationException() throws IOException {
        Path path = Paths.get("src/test/resources/org/apache/commons/io/unsupported-file");
        Files.createFile(path);
        BasicFileAttributes attributes = PathUtils.readAttributes(path, BasicFileAttributes.class);
        assertNull(attributes, "Attributes should be null for unsupported operation");
        Files.delete(path);
    }

    @Test
    public void testReadAttributes_IOException() throws IOException {
        Path path = Paths.get("src/test/resources/org/apache/commons/io/nonexistent-file");
        BasicFileAttributes attributes = PathUtils.readAttributes(path, BasicFileAttributes.class);
        assertNull(attributes, "Attributes should be null for non-existent file");
    }

    @Test
    public void testReadBasicFileAttributes_NullPath() {
        BasicFileAttributes attributes = PathUtils.readBasicFileAttributes(null, new LinkOption[0]);
        assertNull(attributes, "Attributes should be null for null path");
    }

    @Test
    public void testReadDosFileAttributes_NullPath() {
        DosFileAttributes attributes = PathUtils.readDosFileAttributes(null, new LinkOption[0]);
        assertNull(attributes, "Attributes should be null for null path");
    }

    @Test
    public void testReadPosixFileAttributes_NullPath() {
        PosixFileAttributes attributes = PathUtils.readPosixFileAttributes(null, new LinkOption[0]);
        assertNull(attributes, "Attributes should be null for null path");
    }

    @Test
    public void testReadBasicFileAttributesUnchecked_NullPath() {
        BasicFileAttributes attributes = PathUtils.readBasicFileAttributesUnchecked(null);
        assertNull(attributes, "Attributes should be null for null path");
    }

    @Test
    public void testReadBasicFileAttributesUnchecked_ValidPath() throws IOException {
        Path path = Paths.get("src/test/resources/org/apache/commons/io/test-file-simple-utf8.bin");
        BasicFileAttributes attributes = PathUtils.readBasicFileAttributesUnchecked(path);
        assertNotNull(attributes, "Attributes should not be null for valid path");
    }

    @Test
    public void testReadAttributes_ValidPath() throws IOException {
        Path path = Paths.get("src/test/resources/org/apache/commons/io/test-file-simple-utf8.bin");
        BasicFileAttributes attributes = PathUtils.readAttributes(path, BasicFileAttributes.class);
        assertNotNull(attributes, "Attributes should not be null for valid path");
    }

    @Test
    public void testReadDosFileAttributes_ValidPath() throws IOException {
        Path path = Paths.get("src/test/resources/org/apache/commons/io/test-file-simple-utf8.bin");
        DosFileAttributes attributes = PathUtils.readDosFileAttributes(path, new LinkOption[0]);
        assertNull(attributes, "Attributes should be null for non-DOS file");
    }

    @Test
    public void testReadPosixFileAttributes_ValidPath() throws IOException {
        Path path = Paths.get("src/test/resources/org/apache/commons/io/test-file-simple-utf8.bin");
        PosixFileAttributes attributes = PathUtils.readPosixFileAttributes(path, new LinkOption[0]);
        assertNotNull(attributes, "Attributes should not be null for valid path");
    }

    @Test
    public void testPreventInstantiation() {
        // This test ensures that the PathUtils class cannot be instantiated.
        assertThrows(UnsupportedOperationException.class, () -> {
            PathUtils.class.getDeclaredConstructor().newInstance();
        });
    }
}