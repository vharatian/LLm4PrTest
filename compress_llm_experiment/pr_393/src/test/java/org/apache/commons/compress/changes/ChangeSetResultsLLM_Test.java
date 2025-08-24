package org.apache.commons.compress.changes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangeSetResultsLLM_Test {

    @Test
    public void testAddedFromChangeSet() {
        ChangeSetResults results = new ChangeSetResults();
        results.addedFromChangeSet("file1.txt");
        assertTrue(results.getAddedFromChangeSet().contains("file1.txt"));
    }

    @Test
    public void testAddedFromStream() {
        ChangeSetResults results = new ChangeSetResults();
        results.addedFromStream("file2.txt");
        assertTrue(results.getAddedFromStream().contains("file2.txt"));
    }

    @Test
    public void testDeleted() {
        ChangeSetResults results = new ChangeSetResults();
        results.deleted("file3.txt");
        assertTrue(results.getDeleted().contains("file3.txt"));
    }

    @Test
    public void testHasBeenAdded() {
        ChangeSetResults results = new ChangeSetResults();
        results.addedFromChangeSet("file4.txt");
        results.addedFromStream("file5.txt");
        assertTrue(results.hasBeenAdded("file4.txt"));
        assertTrue(results.hasBeenAdded("file5.txt"));
        assertFalse(results.hasBeenAdded("file6.txt"));
    }
}