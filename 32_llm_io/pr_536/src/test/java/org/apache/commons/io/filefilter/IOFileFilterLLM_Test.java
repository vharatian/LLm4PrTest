package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IOFileFilterLLM_Test {

    @Test
    public void testAcceptFile() {
        IOFileFilter filter = file -> file.getName().endsWith(".txt");
        assertTrue(filter.accept(new File("test.txt")));
        assertFalse(filter.accept(new File("test.jpg")));
    }

    @Test
    public void testAcceptDirAndName() {
        IOFileFilter filter = (dir, name) -> name.endsWith(".txt");
        assertTrue(filter.accept(new File("."), "test.txt"));
        assertFalse(filter.accept(new File("."), "test.jpg"));
    }

    @Test
    public void testAcceptPathAndAttributes() {
        IOFileFilter filter = file -> file.getName().endsWith(".txt");
        Path path = new File("test.txt").toPath();
        BasicFileAttributes attributes = null; // Simplified for the test
        assertEquals(FileVisitResult.CONTINUE, filter.accept(path, attributes));
    }

    @Test
    public void testAndFileFilter() {
        IOFileFilter filter1 = file -> file.getName().endsWith(".txt");
        IOFileFilter filter2 = file -> file.length() > 0;
        IOFileFilter andFilter = filter1.and(filter2);
        assertTrue(andFilter.accept(new File("test.txt")));
        assertFalse(andFilter.accept(new File("test.jpg")));
    }

    @Test
    public void testNegateFileFilter() {
        IOFileFilter filter = file -> file.getName().endsWith(".txt");
        IOFileFilter negateFilter = filter.negate();
        assertFalse(negateFilter.accept(new File("test.txt")));
        assertTrue(negateFilter.accept(new File("test.jpg")));
    }

    @Test
    public void testOrFileFilter() {
        IOFileFilter filter1 = file -> file.getName().endsWith(".txt");
        IOFileFilter filter2 = file -> file.length() > 0;
        IOFileFilter orFilter = filter1.or(filter2);
        assertTrue(orFilter.accept(new File("test.txt")));
        assertTrue(orFilter.accept(new File("test.jpg")));
    }

    @Test
    public void testMatchesPath() {
        IOFileFilter filter = file -> file.getName().endsWith(".txt");
        Path path = new File("test.txt").toPath();
        assertTrue(filter.matches(path));
    }
}