package org.apache.commons.io.comparator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.test.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastModifiedFileComparatorLLM_Test extends ComparatorAbstractTest {

    @BeforeEach
    public void setUp() throws Exception {
        comparator = (AbstractFileComparator) LastModifiedFileComparator.LASTMODIFIED_COMPARATOR;
        reverse = LastModifiedFileComparator.LASTMODIFIED_REVERSE;
        final File olderFile = new File(dir, "older.txt");
        if (!olderFile.getParentFile().exists()) {
            throw new IOException("Cannot create file " + olderFile + " as the parent directory does not exist");
        }
        try (BufferedOutputStream output2 = new BufferedOutputStream(Files.newOutputStream(olderFile.toPath()))) {
            TestUtils.generateTestData(output2, 0);
        }
        final File equalFile = new File(dir, "equal.txt");
        if (!equalFile.getParentFile().exists()) {
            throw new IOException("Cannot create file " + equalFile + " as the parent directory does not exist");
        }
        try (BufferedOutputStream output1 = new BufferedOutputStream(Files.newOutputStream(equalFile.toPath()))) {
            TestUtils.generateTestData(output1, 0);
        }
        do {
            TestUtils.sleepQuietly(300);
            equalFile.setLastModified(System.currentTimeMillis());
        } while (FileUtils.lastModified(olderFile) == FileUtils.lastModified(equalFile));
        final File newerFile = new File(dir, "newer.txt");
        if (!newerFile.getParentFile().exists()) {
            throw new IOException("Cannot create file " + newerFile + " as the parent directory does not exist");
        }
        try (BufferedOutputStream output = new BufferedOutputStream(Files.newOutputStream(newerFile.toPath()))) {
            TestUtils.generateTestData(output, 0);
        }
        do {
            TestUtils.sleepQuietly(300);
            newerFile.setLastModified(System.currentTimeMillis());
        } while (FileUtils.lastModified(equalFile) == FileUtils.lastModified(newerFile));
        equalFile1 = equalFile;
        equalFile2 = equalFile;
        lessFile = olderFile;
        moreFile = newerFile;
    }

    @Test
    public void testCompareLastModified() {
        // Test comparing two files with different last modified times
        assertEquals(-1, comparator.compare(lessFile, moreFile));
        assertEquals(1, comparator.compare(moreFile, lessFile));
        assertEquals(0, comparator.compare(equalFile1, equalFile2));
    }

    @Test
    public void testCompareLastModifiedReverse() {
        // Test comparing two files with different last modified times in reverse order
        assertEquals(1, reverse.compare(lessFile, moreFile));
        assertEquals(-1, reverse.compare(moreFile, lessFile));
        assertEquals(0, reverse.compare(equalFile1, equalFile2));
    }
}