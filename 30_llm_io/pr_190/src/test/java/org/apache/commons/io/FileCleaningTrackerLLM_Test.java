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
    public void testExitWhenFinishedDefaultValue() {
        // Verify that the default value of exitWhenFinished is false
        assertFalse(tracker.exitWhenFinished);
    }

    @Test
    public void testExitWhenFinished() {
        // Set exitWhenFinished to true and verify
        tracker.exitWhenFinished();
        assertTrue(tracker.exitWhenFinished);
    }

    @Test
    public void testAddTrackerAfterExitWhenFinished() {
        // Set exitWhenFinished to true
        tracker.exitWhenFinished();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            tracker.track(new File("test.txt"), new Object());
        });
        assertEquals("No new trackers can be added once exitWhenFinished() is called", exception.getMessage());
    }

    @Test
    public void testTrackFile() {
        File file = new File("test.txt");
        tracker.track(file, new Object());
        assertEquals(1, tracker.getTrackCount());
    }

    @Test
    public void testTrackPath() {
        String path = "test.txt";
        tracker.track(path, new Object());
        assertEquals(1, tracker.getTrackCount());
    }

    @Test
    public void testGetDeleteFailures() {
        List<String> deleteFailures = tracker.getDeleteFailures();
        assertNotNull(deleteFailures);
        assertTrue(deleteFailures.isEmpty());
    }
}