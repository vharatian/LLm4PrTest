package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.filefilter.NameFileFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class PathUtilsLLM_Test extends TestArguments {

    private static final String TEST_JAR_NAME = "test.jar";
    private static final String TEST_JAR_PATH = "src/test/resources/org/apache/commons/io/test.jar";
    private static final String PATH_FIXTURE = "NOTICE.txt";

    @TempDir
    public Path tempDir;

    private FileSystem openArchive(final Path p, final boolean createNew) throws IOException {
        final FileSystem archive;
        if (createNew) {
            final Map<String, String> env = new HashMap<>();
            env.put("create", "true");
            final URI fileUri = p.toAbsolutePath().toUri();
            final URI uri = URI.create("jar:" + fileUri.toASCIIString());
            archive = FileSystems.newFileSystem(uri, env, null);
        } else {
            archive = FileSystems.newFileSystem(p, (ClassLoader) null);
        }
        return archive;
    }

    /**
     * Test for the changes made in RelativeSortedPaths constructor.
     * Specifically, the change from nullable to non-nullable final List<Path> variables.
     */
    @Test
    public void testRelativeSortedPathsConstructor() throws IOException {
        final Path dir1 = Files.createTempDirectory(tempDir, "dir1");
        final Path dir2 = Files.createTempDirectory(tempDir, "dir2");
        Files.createFile(dir1.resolve("file1.txt"));
        Files.createFile(dir2.resolve("file1.txt"));

        PathUtils.RelativeSortedPaths relativeSortedPaths = new PathUtils.RelativeSortedPaths(
                dir1, dir2, Integer.MAX_VALUE, PathUtils.EMPTY_LINK_OPTION_ARRAY, PathUtils.EMPTY_FILE_VISIT_OPTION_ARRAY);

        assertTrue(relativeSortedPaths.equals);
        assertEquals(relativeSortedPaths.relativeFileList1, relativeSortedPaths.relativeFileList2);
    }

    @Test
    public void testRelativeSortedPathsConstructorWithNullDirs() throws IOException {
        PathUtils.RelativeSortedPaths relativeSortedPaths = new PathUtils.RelativeSortedPaths(
                null, null, Integer.MAX_VALUE, PathUtils.EMPTY_LINK_OPTION_ARRAY, PathUtils.EMPTY_FILE_VISIT_OPTION_ARRAY);

        assertTrue(relativeSortedPaths.equals);
        assertEquals(relativeSortedPaths.relativeFileList1, relativeSortedPaths.relativeFileList2);
    }

    @Test
    public void testRelativeSortedPathsConstructorWithOneNullDir() throws IOException {
        final Path dir1 = Files.createTempDirectory(tempDir, "dir1");
        Files.createFile(dir1.resolve("file1.txt"));

        PathUtils.RelativeSortedPaths relativeSortedPaths = new PathUtils.RelativeSortedPaths(
                dir1, null, Integer.MAX_VALUE, PathUtils.EMPTY_LINK_OPTION_ARRAY, PathUtils.EMPTY_FILE_VISIT_OPTION_ARRAY);

        assertFalse(relativeSortedPaths.equals);
    }
}