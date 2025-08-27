package org.apache.commons.io.filefilter;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class FileFilterUtilsLLM_Test {

    @Test
    public void testFilterListWithIterable() {
        IOFileFilter filter = file -> file.getName().endsWith(".txt");
        List<File> files = new ArrayList<>();
        files.add(new File("test1.txt"));
        files.add(new File("test2.jpg"));
        files.add(new File("test3.txt"));

        List<File> filteredFiles = FileFilterUtils.filterList(filter, files);
        assertEquals(2, filteredFiles.size());
        assertTrue(filteredFiles.contains(new File("test1.txt")));
        assertTrue(filteredFiles.contains(new File("test3.txt")));
    }

    @Test
    public void testFilterSetWithIterable() {
        IOFileFilter filter = file -> file.getName().endsWith(".txt");
        Set<File> files = new HashSet<>();
        files.add(new File("test1.txt"));
        files.add(new File("test2.jpg"));
        files.add(new File("test3.txt"));

        Set<File> filteredFiles = FileFilterUtils.filterSet(filter, files);
        assertEquals(2, filteredFiles.size());
        assertTrue(filteredFiles.contains(new File("test1.txt")));
        assertTrue(filteredFiles.contains(new File("test3.txt")));
    }
}