package org.apache.commons.io.file;

import static org.apache.commons.io.file.CounterAssertions.assertCounts;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private void applyDeleteEmptyDirectory(final DeletingPathVisitor visitor) throws IOException {
        Files.walkFileTree(tempDir, visitor);
        assertCounts(1, 0, 0, visitor);
    }

    @BeforeEach
    public void beforeEach() throws IOException {
        tempDir = Files.createTempDirectory(getClass().getCanonicalName());
    }

    @ParameterizedTest
    @MethodSource("deletingPathVisitors")
    public void testDeleteSymbolicLink(final DeletingPathVisitor visitor) throws IOException {
        // Create a symbolic link and a target file
        Path targetFile = tempDir.resolve("targetFile.txt");
        Files.createFile(targetFile);
        Path symLink = tempDir.resolve("symLink");
        Files.createSymbolicLink(symLink, targetFile);

        // Ensure the symbolic link and target file exist
        Assertions.assertTrue(Files.exists(symLink));
        Assertions.assertTrue(Files.exists(targetFile));

        // Apply the visitor
        PathUtils.visitFileTree(visitor, tempDir);

        // Ensure the symbolic link is deleted but the target file remains
        Assertions.assertFalse(Files.exists(symLink));
        Assertions.assertTrue(Files.exists(targetFile));

        // Clean up
        Files.deleteIfExists(targetFile);
        Files.deleteIfExists(tempDir);
    }
}