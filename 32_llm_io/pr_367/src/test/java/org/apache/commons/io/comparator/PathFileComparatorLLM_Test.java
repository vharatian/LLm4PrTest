package org.apache.commons.io.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PathFileComparatorLLM_Test extends ComparatorAbstractTest {

    @BeforeEach
    public void setUp() {
        comparator = (AbstractFileComparator) PathFileComparator.PATH_COMPARATOR;
        reverse = PathFileComparator.PATH_REVERSE;
        equalFile1 = new File("foo/file.txt");
        equalFile2 = new File("foo/file.txt");
        lessFile = new File("abc/file.txt");
        moreFile = new File("xyz/file.txt");
    }

    @Test
    public void testSystemComparator() {
        final Comparator<File> systemComparator = PathFileComparator.PATH_SYSTEM_COMPARATOR;
        assertEquals(0, systemComparator.compare(equalFile1, equalFile2), "systemComparator file1 & file2 = 0");
        assertTrue(systemComparator.compare(equalFile1, lessFile) > 0, "systemComparator file1 & less > 0");
        assertTrue(systemComparator.compare(equalFile1, moreFile) < 0, "systemComparator file1 & more < 0");
    }

    @Test
    public void testSystemReverseComparator() {
        final Comparator<File> systemReverseComparator = PathFileComparator.PATH_SYSTEM_REVERSE;
        assertEquals(0, systemReverseComparator.compare(equalFile1, equalFile2), "systemReverseComparator file1 & file2 = 0");
        assertTrue(systemReverseComparator.compare(equalFile1, lessFile) < 0, "systemReverseComparator file1 & less < 0");
        assertTrue(systemReverseComparator.compare(equalFile1, moreFile) > 0, "systemReverseComparator file1 & more > 0");
    }
}