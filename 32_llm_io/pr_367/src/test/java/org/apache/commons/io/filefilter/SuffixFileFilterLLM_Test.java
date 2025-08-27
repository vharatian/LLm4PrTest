package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.io.IOCase;

public class SuffixFileFilterLLM_Test {

    @Test
    public void testAcceptFileWithSuffix() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        File file = new File("example.txt");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithoutSuffix() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        File file = new File("example.doc");
        assertFalse(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithMultipleSuffixes() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt", ".doc");
        File file = new File("example.doc");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithCaseInsensitiveSuffix() {
        SuffixFileFilter filter = new SuffixFileFilter(".TXT", IOCase.INSENSITIVE);
        File file = new File("example.txt");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptPathWithSuffix() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        Path path = new File("example.txt").toPath();
        BasicFileAttributes attrs = null; // Not used in the current implementation
        assertEquals(FileVisitResult.CONTINUE, filter.accept(path, attrs));
    }

    @Test
    public void testToString() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt", ".doc");
        String expected = "org.apache.commons.io.filefilter.SuffixFileFilter(.txt,.doc)";
        assertEquals(expected, filter.toString());
    }

    @Test
    public void testAcceptFileWithListSuffixes() {
        List<String> suffixes = Arrays.asList(".txt", ".doc");
        SuffixFileFilter filter = new SuffixFileFilter(suffixes);
        File file = new File("example.txt");
        assertTrue(filter.accept(file));
    }

    @Test
    public void testAcceptFileWithArraySuffixes() {
        String[] suffixes = {".txt", ".doc"};
        SuffixFileFilter filter = new SuffixFileFilter(suffixes);
        File file = new File("example.doc");
        assertTrue(filter.accept(file));
    }
}