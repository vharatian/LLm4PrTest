package org.apache.commons.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileCleaningTrackerLLM_Test {

    private FileCleaningTracker tracker;

    @BeforeEach
    public void setUp() {
        tracker = new FileCleaningTracker();
    }

    @Test
    public void testTrackFile() {
        File file = new File("testFile.txt");
        tracker.track(file, new Object());
        assertEquals(1, tracker.getTrackCount());
    }

    @Test
    public void testTrackFileWithDeleteStrategy() {
        File file = new File("testFile.txt");
        tracker.track(file, new Object(), FileDeleteStrategy.NORMAL);
        assertEquals(1, tracker.getTrackCount());
    }

    @Test
    public void testTrackPath() {
        String path = "testFile.txt";
        tracker.track(path, new Object());
        assertEquals(1, tracker.getTrackCount());
    }

    @Test
    public void testTrackPathWithDeleteStrategy() {
        String path = "testFile.txt";
        tracker.track(path, new Object(), FileDeleteStrategy.NORMAL);
        assertEquals(1, tracker.getTrackCount());
    }

    @Test
    public void testGetDeleteFailures() {
        List<String> deleteFailures = tracker.getDeleteFailures();
        assertNotNull(deleteFailures);
        assertTrue(deleteFailures.isEmpty());
    }

    @Test
    public void testExitWhenFinished() {
        tracker.exitWhenFinished();
        assertThrows(IllegalStateException.class, () -> tracker.track(new File("testFile.txt"), new Object()));
    }
}