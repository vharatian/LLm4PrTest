package org.apache.commons.io.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NameFileComparatorLLM_Test extends ComparatorAbstractTest {

    @BeforeEach
    public void setUp() {
        comparator = (AbstractFileComparator) NameFileComparator.NAME_INSENSITIVE_COMPARATOR;
        reverse = NameFileComparator.NAME_REVERSE;
        equalFile1 = new File("a/foo.txt");
        equalFile2 = new File("b/foo.txt");
        lessFile = new File("c/ABC.txt");
        moreFile = new File("d/XYZ.txt");
    }

    @Test
    public void testSystemCaseSensitivity() {
        final File file3 = new File("a/FOO.txt");
        final Comparator<File> systemSensitive = NameFileComparator.NAME_SYSTEM_COMPARATOR;
        assertEquals(0, systemSensitive.compare(equalFile1, equalFile2), "system sensitive file1 & file2 = 0");
        assertTrue(systemSensitive.compare(equalFile1, file3) > 0, "system sensitive file1 & file3 > 0");
        assertTrue(systemSensitive.compare(equalFile1, lessFile) > 0, "system sensitive file1 & less > 0");
    }

    @Test
    public void testSystemCaseInsensitive() {
        final File file3 = new File("a/FOO.txt");
        final Comparator<File> systemInsensitive = NameFileComparator.NAME_SYSTEM_REVERSE;
        assertEquals(0, systemInsensitive.compare(equalFile1, equalFile2), "system insensitive file1 & file2 = 0");
        assertEquals(0, systemInsensitive.compare(equalFile1, file3), "system insensitive file1 & file3 = 0");
        assertTrue(systemInsensitive.compare(equalFile1, lessFile) > 0, "system insensitive file1 & less > 0");
    }
}