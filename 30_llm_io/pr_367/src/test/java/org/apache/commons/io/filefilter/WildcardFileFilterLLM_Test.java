package org.apache.commons.io.filefilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOCase;
import org.junit.jupiter.api.Test;

public class WildcardFileFilterLLM_Test extends AbstractFilterTest {
    @Test
    public void testCaseSensitiveComment() {
        // Testing the case-sensitive functionality with a single wildcard
        IOFileFilter filter = new WildcardFileFilter("*.txt", IOCase.SENSITIVE);
        assertFiltering(filter, new File("log.txt"), true);
        assertFiltering(filter, new File("log.TXT"), false);
        assertFiltering(filter, new File("log.txt").toPath(), true);
        assertFiltering(filter, new File("log.TXT").toPath(), false);

        // Testing the case-sensitive functionality with multiple wildcards
        filter = new WildcardFileFilter(new String[] {"*.java", "*.class"}, IOCase.SENSITIVE);
        assertFiltering(filter, new File("Test.java"), true);
        assertFiltering(filter, new File("Test.JAVA"), false);
        assertFiltering(filter, new File("Test.java").toPath(), true);
        assertFiltering(filter, new File("Test.JAVA").toPath(), false);
    }

    @Test
    public void testCaseInsensitiveComment() {
        // Testing the case-insensitive functionality with a single wildcard
        IOFileFilter filter = new WildcardFileFilter("*.txt", IOCase.INSENSITIVE);
        assertFiltering(filter, new File("log.txt"), true);
        assertFiltering(filter, new File("log.TXT"), true);
        assertFiltering(filter, new File("log.txt").toPath(), true);
        assertFiltering(filter, new File("log.TXT").toPath(), true);

        // Testing the case-insensitive functionality with multiple wildcards
        filter = new WildcardFileFilter(new String[] {"*.java", "*.class"}, IOCase.INSENSITIVE);
        assertFiltering(filter, new File("Test.java"), true);
        assertFiltering(filter, new File("Test.JAVA"), true);
        assertFiltering(filter, new File("Test.java").toPath(), true);
        assertFiltering(filter, new File("Test.JAVA").toPath(), true);
    }

    @Test
    public void testSystemCaseComment() {
        // Testing the system case functionality with a single wildcard
        IOFileFilter filter = new WildcardFileFilter("*.txt", IOCase.SYSTEM);
        assertFiltering(filter, new File("log.txt"), true);
        assertFiltering(filter, new File("log.TXT"), WINDOWS);
        assertFiltering(filter, new File("log.txt").toPath(), true);
        assertFiltering(filter, new File("log.TXT").toPath(), WINDOWS);

        // Testing the system case functionality with multiple wildcards
        filter = new WildcardFileFilter(new String[] {"*.java", "*.class"}, IOCase.SYSTEM);
        assertFiltering(filter, new File("Test.java"), true);
        assertFiltering(filter, new File("Test.JAVA"), WINDOWS);
        assertFiltering(filter, new File("Test.java").toPath(), true);
        assertFiltering(filter, new File("Test.JAVA").toPath(), WINDOWS);
    }
}