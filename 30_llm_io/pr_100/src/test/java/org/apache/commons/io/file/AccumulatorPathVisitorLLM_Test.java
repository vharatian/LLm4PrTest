package org.apache.commons.io.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccumulatorPathVisitorLLM_Test {

    private AccumulatorPathVisitor visitor;
    private PathCounters mockPathCounters;

    @BeforeEach
    public void setUp() {
        mockPathCounters = mock(PathCounters.class);
        visitor = new AccumulatorPathVisitor(mockPathCounters);
    }

    @Test
    public void testWithBigIntegerCounters() {
        AccumulatorPathVisitor bigIntegerVisitor = AccumulatorPathVisitor.withBigIntegerCounters();
        assertNotNull(bigIntegerVisitor);
    }

    @Test
    public void testWithLongCounters() {
        AccumulatorPathVisitor longVisitor = AccumulatorPathVisitor.withLongCounters();
        assertNotNull(longVisitor);
    }

    @Test
    public void testVisitFileAddsToFileList() throws IOException {
        Path file = Paths.get("testFile.txt");
        BasicFileAttributes mockAttributes = mock(BasicFileAttributes.class);
        when(mockAttributes.isDirectory()).thenReturn(false);

        FileVisitResult result = visitor.visitFile(file, mockAttributes);

        assertEquals(FileVisitResult.CONTINUE, result);
        assertTrue(visitor.getFileList().contains(file.normalize()));
    }

    @Test
    public void testVisitFileAddsToDirList() throws IOException {
        Path dir = Paths.get("testDir");
        BasicFileAttributes mockAttributes = mock(BasicFileAttributes.class);
        when(mockAttributes.isDirectory()).thenReturn(true);

        FileVisitResult result = visitor.visitFile(dir, mockAttributes);

        assertEquals(FileVisitResult.CONTINUE, result);
        assertTrue(visitor.getDirList().contains(dir.normalize()));
    }

    @Test
    public void testRelativizeDirectories() {
        Path parent = Paths.get("/parent");
        visitor.getDirList().add(Paths.get("/parent/dir1"));
        visitor.getDirList().add(Paths.get("/parent/dir2"));

        List<Path> relativizedDirs = visitor.relativizeDirectories(parent, true, Comparator.naturalOrder());

        assertEquals(2, relativizedDirs.size());
        assertEquals(Paths.get("dir1"), relativizedDirs.get(0));
        assertEquals(Paths.get("dir2"), relativizedDirs.get(1));
    }

    @Test
    public void testRelativizeFiles() {
        Path parent = Paths.get("/parent");
        visitor.getFileList().add(Paths.get("/parent/file1.txt"));
        visitor.getFileList().add(Paths.get("/parent/file2.txt"));

        List<Path> relativizedFiles = visitor.relativizeFiles(parent, true, Comparator.naturalOrder());

        assertEquals(2, relativizedFiles.size());
        assertEquals(Paths.get("file1.txt"), relativizedFiles.get(0));
        assertEquals(Paths.get("file2.txt"), relativizedFiles.get(1));
    }

    @Test
    public void testEqualsAndHashCode() {
        AccumulatorPathVisitor visitor1 = new AccumulatorPathVisitor(mockPathCounters);
        AccumulatorPathVisitor visitor2 = new AccumulatorPathVisitor(mockPathCounters);

        assertEquals(visitor1, visitor2);
        assertEquals(visitor1.hashCode(), visitor2.hashCode());

        visitor1.getDirList().add(Paths.get("dir"));
        assertNotEquals(visitor1, visitor2);
        assertNotEquals(visitor1.hashCode(), visitor2.hashCode());
    }
}