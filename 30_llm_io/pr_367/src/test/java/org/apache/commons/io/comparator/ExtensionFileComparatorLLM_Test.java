package org.apache.commons.io.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExtensionFileComparatorLLM_Test extends ComparatorAbstractTest {

    @BeforeEach
    public void setUp() {
        comparator = (AbstractFileComparator) ExtensionFileComparator.EXTENSION_COMPARATOR;
        reverse = ExtensionFileComparator.EXTENSION_REVERSE;
        equalFile1 = new File("abc.foo");
        equalFile2 = new File("def.foo");
        lessFile = new File("abc.abc");
        moreFile = new File("abc.xyz");
    }

    @Test
    public void testSystemCaseSensitivity() {
        final File file3 = new File("abc.FOO");
        final Comparator<File> systemSensitive = ExtensionFileComparator.EXTENSION_SYSTEM_COMPARATOR;
        assertEquals(0, systemSensitive.compare(equalFile1, equalFile2), "system sensitive file1 & file2 = 0");
        assertTrue(systemSensitive.compare(equalFile1, file3) > 0, "system sensitive file1 & file3 > 0");
        assertTrue(systemSensitive.compare(equalFile1, lessFile) > 0, "system sensitive file1 & less > 0");
    }

    @Test
    public void testSystemCaseSensitivityReverse() {
        final File file3 = new File("abc.FOO");
        final Comparator<File> systemSensitiveReverse = ExtensionFileComparator.EXTENSION_SYSTEM_REVERSE;
        assertEquals(0, systemSensitiveReverse.compare(equalFile1, equalFile2), "system sensitive reverse file1 & file2 = 0");
        assertTrue(systemSensitiveReverse.compare(file3, equalFile1) > 0, "system sensitive reverse file3 & file1 > 0");
        assertTrue(systemSensitiveReverse.compare(lessFile, equalFile1) > 0, "system sensitive reverse less & file1 > 0");
    }
}