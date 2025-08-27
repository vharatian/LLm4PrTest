package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.FileVisitOption;
import java.util.EnumSet;

import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test extends TestArguments {

    @Test
    public void testDirectoryAndFileContentEquals() throws IOException {
        final Path dir1 = Paths.get("src/test/resources/org/apache/commons/io/dirs-1");
        final Path dir2 = Paths.get("src/test/resources/org/apache/commons/io/dirs-2");
        assertTrue(PathUtils.directoryAndFileContentEquals(dir1, dir2));
    }

    @Test
    public void testDirectoryContentEquals() throws IOException {
        final Path dir1 = Paths.get("src/test/resources/org/apache/commons/io/dirs-1");
        final Path dir2 = Paths.get("src/test/resources/org/apache/commons/io/dirs-2");
        assertTrue(PathUtils.directoryContentEquals(dir1, dir2));
    }

    @Test
    public void testFileContentEquals() throws IOException {
        final Path file1 = Paths.get("src/test/resources/org/apache/commons/io/dirs-1-file-size-1/file-size-1.bin");
        final Path file2 = Paths.get("src/test/resources/org/apache/commons/io/dirs-2-file-size-1/file-size-1.bin");
        assertTrue(PathUtils.fileContentEquals(file1, file2));
    }

    @Test
    public void testFileContentEqualsWithOptions() throws IOException {
        final Path file1 = Paths.get("src/test/resources/org/apache/commons/io/dirs-1-file-size-1/file-size-1.bin");
        final Path file2 = Paths.get("src/test/resources/org/apache/commons/io/dirs-2-file-size-1/file-size-1.bin");
        LinkOption[] linkOptions = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};
        OpenOption[] openOptions = new OpenOption[]{};
        assertTrue(PathUtils.fileContentEquals(file1, file2, linkOptions, openOptions));
    }

    @Test
    public void testVisitFileTreeWithOptions() throws IOException {
        final Path startDir = Paths.get("src/test/resources/org/apache/commons/io/dirs-1");
        final AccumulatorPathVisitor visitor = AccumulatorPathVisitor.withLongCounters();
        PathUtils.visitFileTree(visitor, startDir, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE);
        assertFalse(visitor.getFileList().isEmpty());
    }

    @Test
    public void testRelativize() {
        final Path parent = Paths.get("src/test/resources/org/apache/commons/io/dirs-1");
        final Path file = parent.resolve("file-size-1.bin");
        assertEquals(Paths.get("file-size-1.bin"), PathUtils.relativize(List.of(file), parent, false, null).get(0));
    }

    @Test
    public void testToFileVisitOptionSet() {
        FileVisitOption[] options = new FileVisitOption[]{FileVisitOption.FOLLOW_LINKS};
        assertEquals(EnumSet.of(FileVisitOption.FOLLOW_LINKS), PathUtils.toFileVisitOptionSet(options));
    }
}