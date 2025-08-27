package org.apache.commons.io.comparator;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractFileComparatorLLM_Test {

    private static class TestFileComparator extends AbstractFileComparator {
        @Override
        public int compare(File o1, File o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    @Test
    public void testSortArray() {
        File file1 = new File("b.txt");
        File file2 = new File("a.txt");
        File file3 = new File("c.txt");

        File[] files = {file1, file2, file3};
        File[] expected = {file2, file1, file3};

        TestFileComparator comparator = new TestFileComparator();
        File[] sortedFiles = comparator.sort(files);

        assertArrayEquals(expected, sortedFiles);
    }

    @Test
    public void testSortList() {
        File file1 = new File("b.txt");
        File file2 = new File("a.txt");
        File file3 = new File("c.txt");

        List<File> files = new ArrayList<>();
        files.add(file1);
        files.add(file2);
        files.add(file3);

        List<File> expected = new ArrayList<>();
        expected.add(file2);
        expected.add(file1);
        expected.add(file3);

        TestFileComparator comparator = new TestFileComparator();
        List<File> sortedFiles = comparator.sort(files);

        assertEquals(expected, sortedFiles);
    }

    @Test
    public void testSortListWithNull() {
        List<File> files = null;

        TestFileComparator comparator = new TestFileComparator();
        List<File> sortedFiles = comparator.sort(files);

        assertEquals(null, sortedFiles);
    }
}