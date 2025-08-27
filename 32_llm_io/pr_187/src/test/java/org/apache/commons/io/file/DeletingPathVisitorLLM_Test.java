package org.apache.commons.io.file;

import static org.apache.commons.io.file.CounterAssertions.assertCounts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collections;
import org.apache.commons.io.file.Counters.PathCounters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class DeletingPathVisitorLLM_Test extends TestArguments {
    private Path tempDir;

    @AfterEach
    public void afterEach() throws IOException {
        if (Files.exists(tempDir) && PathUtils.isEmptyDirectory(tempDir)) {
            Files.deleteIfExists(tempDir);
        }
    }

    @BeforeEach
    public void beforeEach() throws IOException {
        tempDir = Files.createTempDirectory(getClass().getCanonicalName());
    }

    @ParameterizedTest
    @MethodSource("deletingPathVisitors")
    public void testDeleteSymbolicLink(final DeletingPathVisitor visitor) throws IOException {
        Path targetFile = tempDir.resolve("targetFile.txt");
        Files.createFile(targetFile);
        Path symbolicLink = tempDir.resolve("symbolicLink");
        Files.createSymbolicLink(symbolicLink, targetFile);

        assertCounts(1, 1, 0, PathUtils.visitFileTree(visitor, tempDir));
        Assertions.assertFalse(Files.exists(symbolicLink));
        Assertions.assertFalse(Files.exists(targetFile));
    }

    @ParameterizedTest
    @MethodSource("deletingPathVisitors")
    public void testDeleteBrokenSymbolicLink(final DeletingPathVisitor visitor) throws IOException {
        Path targetFile = tempDir.resolve("targetFile.txt");
        Path symbolicLink = tempDir.resolve("symbolicLink");
        Files.createSymbolicLink(symbolicLink, targetFile);

        assertCounts(1, 1, 0, PathUtils.visitFileTree(visitor, tempDir));
        Assertions.assertFalse(Files.exists(symbolicLink));
    }

    @ParameterizedTest
    @MethodSource("deletingPathVisitors")
    public void testDeleteReadOnlyFile(final DeletingPathVisitor visitor) throws IOException {
        Path readOnlyFile = tempDir.resolve("readOnlyFile.txt");
        Files.createFile(readOnlyFile);
        Files.setPosixFilePermissions(readOnlyFile, PosixFilePermissions.fromString("r--r--r--"));

        assertCounts(1, 1, 0, PathUtils.visitFileTree(visitor, tempDir));
        Assertions.assertFalse(Files.exists(readOnlyFile));
    }
}