package org.apache.commons.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LineIteratorLLM_Test {

    private Reader mockReader;
    private BufferedReader mockBufferedReader;
    private LineIterator lineIterator;

    @BeforeEach
    public void setUp() {
        mockReader = mock(Reader.class);
        mockBufferedReader = mock(BufferedReader.class);
    }

    @Test
    public void testHasNextWithIOException() throws IOException {
        when(mockBufferedReader.readLine()).thenThrow(new IOException("Test IOException"));
        lineIterator = new LineIterator(mockBufferedReader);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            lineIterator.hasNext();
        });

        assertTrue(exception.getCause() instanceof IOException);
        assertEquals("Test IOException", exception.getCause().getMessage());
    }

    @Test
    public void testCloseQuietlySuppressedException() throws IOException {
        IOException ioException = new IOException("Test IOException");
        doThrow(ioException).when(mockBufferedReader).close();
        lineIterator = new LineIterator(mockBufferedReader);

        lineIterator.close();

        assertTrue(ioException.getSuppressed().length > 0);
    }
}