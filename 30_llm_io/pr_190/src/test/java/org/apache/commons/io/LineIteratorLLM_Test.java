package org.apache.commons.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.*;

public class LineIteratorLLM_Test {

    private Reader reader;
    private LineIterator lineIterator;

    @BeforeEach
    public void setUp() {
        reader = new StringReader("line1\nline2\nline3");
    }

    @Test
    public void testConstructorInitializesFinishedFlag() {
        lineIterator = new LineIterator(reader);
        assertFalse(lineIterator.finished, "Finished flag should be initialized to false");
    }

    @Test
    public void testHasNextWhenFinished() throws IOException {
        lineIterator = new LineIterator(reader);
        while (lineIterator.hasNext()) {
            lineIterator.next();
        }
        assertFalse(lineIterator.hasNext(), "HasNext should return false when finished");
    }

    @Test
    public void testNextLineWhenFinished() throws IOException {
        lineIterator = new LineIterator(reader);
        while (lineIterator.hasNext()) {
            lineIterator.next();
        }
        assertThrows(NoSuchElementException.class, () -> lineIterator.nextLine(), "NextLine should throw NoSuchElementException when finished");
    }

    @Test
    public void testCloseSetsFinishedFlag() throws IOException {
        lineIterator = new LineIterator(reader);
        lineIterator.close();
        assertTrue(lineIterator.finished, "Finished flag should be set to true after close");
    }
}