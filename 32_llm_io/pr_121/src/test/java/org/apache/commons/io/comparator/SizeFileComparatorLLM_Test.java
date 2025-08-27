package org.apache.commons.io.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.io.testtools.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SizeFileComparatorLLM_Test extends ComparatorAbstractTestCase {
    private File smallerDir;
    private File largerDir;
    private File smallerFile;
    private File largerFile;

    @BeforeEach
    public void setUp() throws Exception {
        comparator = (AbstractFileComparator) SizeFileComparator.SIZE_COMPARATOR;
        reverse = SizeFileComparator.SIZE_REVERSE;
        smallerDir = new File(dir, "smallerdir");
        largerDir = new File(dir, "largerdir");
        smallerFile = new File(smallerDir, "smaller.txt");
        final File equalFile = new File(dir, "equal.txt");
        largerFile = new File(largerDir, "larger.txt");
        smallerDir.mkdir();
        largerDir.mkdir();
        if (!smallerFile.getParentFile().exists()) {
            throw new IOException("Cannot create file " + smallerFile + " as the parent directory does not exist");
        }
        try (final BufferedOutputStream output2 = new BufferedOutputStream(new FileOutputStream(smallerFile))) {
            TestUtils.generateTestData(output2, 32);
        }
        if (!equalFile.getParentFile().exists()) {
            throw new IOException("Cannot create file " + equalFile + " as the parent directory does not exist");
        }
        try (final BufferedOutputStream output1 = new BufferedOutputStream(new FileOutputStream(equalFile))) {
            TestUtils.generateTestData(output1, 48);
        }
        if (!largerFile.getParentFile().exists()) {
            throw new IOException("Cannot create file " + largerFile + " as the parent directory does not exist");
        }
        try (final BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(largerFile))) {
            TestUtils.generateTestData(output, 64);
        }
        equalFile1 = equalFile;
        equalFile2 = equalFile;
        lessFile = smallerFile;
        moreFile = largerFile;
    }

    @Test
    public void testNonexistantFile() {
        final File nonexistantFile = new File(new File("."), "nonexistant.txt");
        assertFalse(nonexistantFile.exists());
        assertTrue(comparator.compare(nonexistantFile, moreFile) < 0, "less");
    }

    @Test
    public void testCompareDirectorySizes() {
        assertEquals(0, comparator.compare(smallerDir, largerDir), "sumDirectoryContents=false");
        assertEquals(-1, SizeFileComparator.SIZE_SUMDIR_COMPARATOR.compare(smallerDir, largerDir), "less");
        assertEquals(1, SizeFileComparator.SIZE_SUMDIR_REVERSE.compare(smallerDir, largerDir), "less");
    }

    @Test
    public void testCompareFileSizes() {
        // Test comparing two files directly
        assertEquals(-1, comparator.compare(smallerFile, largerFile), "smallerFile should be less than largerFile");
        assertEquals(1, comparator.compare(largerFile, smallerFile), "largerFile should be greater than smallerFile");
        assertEquals(0, comparator.compare(equalFile1, equalFile2), "equalFile1 should be equal to equalFile2");
    }

    @Test
    public void testCompareWithNonExistentFiles() {
        final File nonExistentFile1 = new File(new File("."), "nonExistent1.txt");
        final File nonExistentFile2 = new File(new File("."), "nonExistent2.txt");
        assertFalse(nonExistentFile1.exists());
        assertFalse(nonExistentFile2.exists());
        assertEquals(0, comparator.compare(nonExistentFile1, nonExistentFile2), "Both non-existent files should be equal");
    }
}