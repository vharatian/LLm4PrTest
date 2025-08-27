package org.apache.commons.io.filefilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.AccumulatorPathVisitor;
import org.apache.commons.io.file.CounterAssertions;
import org.apache.commons.io.file.Counters;
import org.junit.jupiter.api.Test;

public class NameFileFilterLLM_Test {

    @Test
    public void testCaseSensitiveFilter() {
        NameFileFilter filter = new NameFileFilter("testFile.txt", IOCase.SENSITIVE);
        assertTrue(filter.accept(new File("testFile.txt")));
        assertFalse(filter.accept(new File("TestFile.txt")));
    }

    @Test
    public void testCaseInsensitiveFilter() {
        NameFileFilter filter = new NameFileFilter("testFile.txt", IOCase.INSENSITIVE);
        assertTrue(filter.accept(new File("testFile.txt")));
        assertTrue(filter.accept(new File("TestFile.txt")));
    }

    @Test
    public void testMultipleNamesFilter() {
        NameFileFilter filter = new NameFileFilter(new String[]{"testFile1.txt", "testFile2.txt"}, IOCase.SENSITIVE);
        assertTrue(filter.accept(new File("testFile1.txt")));
        assertTrue(filter.accept(new File("testFile2.txt")));
        assertFalse(filter.accept(new File("testFile3.txt")));
    }

    @Test
    public void testToStringMethod() {
        NameFileFilter filter = new NameFileFilter("testFile.txt", IOCase.SENSITIVE);
        String expected = "NameFileFilter(testFile.txt)";
        assertEquals(expected, filter.toString());
    }
}