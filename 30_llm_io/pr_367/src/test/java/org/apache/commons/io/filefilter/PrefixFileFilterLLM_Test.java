package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PrefixFileFilterLLM_Test {

    @Test
    public void testAcceptFileWithPrefix() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        File file = new File("testFile.txt");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithoutPrefix() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        File file = new File("exampleFile.txt");
        assertFalse(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithMultiplePrefixes() {
        PrefixFileFilter filter = new PrefixFileFilter("test", "example");
        File file1 = new File("testFile.txt");
        File file2 = new File("exampleFile.txt");
        assertTrue(filter.accept(file1));
        assertTrue(filter.accept(file2));
    }

    @Test
    public void testAcceptFileWithCaseInsensitive() {
        PrefixFileFilter filter = new PrefixFileFilter("test", IOCase.INSENSITIVE);
        File file = new File("TestFile.txt");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptPathWithPrefix() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        Path path = new File("testFile.txt").toPath();
        BasicFileAttributes attrs = null;
        assertEquals(FileVisitResult.CONTINUE, filter.accept(path, attrs));
    }

    @Test
    public void testToString() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        String expected = "PrefixFileFilter(test)";
        assertEquals(expected, filter.toString());
    }

    @Test
    public void testAcceptFileWithNullName() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        assertFalse(filter.accept((File) null));
    }

    @Test
    public void testAcceptFileWithEmptyPrefix() {
        PrefixFileFilter filter = new PrefixFileFilter("");
        File file = new File("anyFile.txt");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithNullPrefix() {
        List<String> prefixes = Arrays.asList(null, "test");
        PrefixFileFilter filter = new PrefixFileFilter(prefixes);
        File file = new File("testFile.txt");
        assertTrue(filter.accept(file));
    }
}