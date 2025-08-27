package org.apache.commons.io.filefilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import org.apache.commons.io.IOCase;
import org.junit.jupiter.api.Test;

public class RegexFileFilterLLM_Test {

    public void assertFiltering(final IOFileFilter filter, final File file, final boolean expected) {
        assertEquals(expected, filter.accept(file),
                "Filter(File) " + filter.getClass().getName() + " not " + expected + " for " + file);
        if (file != null && file.getParentFile() != null) {
            assertEquals(expected, filter.accept(file.getParentFile(), file.getName()),
                    "Filter(File, String) " + filter.getClass().getName() + " not " + expected + " for " + file);
        } else if (file == null) {
            assertEquals(expected, filter.accept(file),
                    "Filter(File, String) " + filter.getClass().getName() + " not " + expected + " for null");
        }
    }

    public void assertFiltering(final IOFileFilter filter, final Path path, final boolean expected) {
        final FileVisitResult expectedFileVisitResult = AbstractFileFilter.toDefaultFileVisitResult(expected);
        assertEquals(expectedFileVisitResult, filter.accept(path, null),
                "Filter(Path) " + filter.getClass().getName() + " not " + expectedFileVisitResult + " for " + path);
        if (path != null && path.getParent() != null) {
            assertEquals(expectedFileVisitResult, filter.accept(path, null),
                    "Filter(Path, Path) " + filter.getClass().getName() + " not " + expectedFileVisitResult + " for "
                            + path);
        } else if (path == null) {
            assertEquals(expectedFileVisitResult, filter.accept(path, null),
                    "Filter(Path, Path) " + filter.getClass().getName() + " not " + expectedFileVisitResult + " for null");
        }
    }

    @Test
    public void testRegexCaseSensitivity() {
        // Test case sensitivity with IOCase.SENSITIVE
        IOFileFilter filter = new RegexFileFilter("^test.java$", IOCase.SENSITIVE);
        assertFiltering(filter, new File("Test.java"), false);
        assertFiltering(filter, new File("test.java"), true);
        assertFiltering(filter, new File("tEST.java"), false);
        assertFiltering(filter, new File("Test.java").toPath(), false);
        assertFiltering(filter, new File("test.java").toPath(), true);
        assertFiltering(filter, new File("tEST.java").toPath(), false);

        // Test case insensitivity with IOCase.INSENSITIVE
        filter = new RegexFileFilter("^test.java$", IOCase.INSENSITIVE);
        assertFiltering(filter, new File("Test.java"), true);
        assertFiltering(filter, new File("test.java"), true);
        assertFiltering(filter, new File("tEST.java"), true);
        assertFiltering(filter, new File("Test.java").toPath(), true);
        assertFiltering(filter, new File("test.java").toPath(), true);
        assertFiltering(filter, new File("tEST.java").toPath(), true);
    }
}