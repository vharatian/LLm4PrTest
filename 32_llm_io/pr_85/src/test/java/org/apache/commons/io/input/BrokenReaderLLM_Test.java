package org.apache.commons.io.input;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrokenReaderLLM_Test {

    @Test
    public void testReadThrowsIOException() {
        IOException exception = new IOException("Test exception");
        BrokenReader reader = new BrokenReader(exception);
        assertThrows(IOException.class, () -> reader.read(new char[0], 0, 0));
    }

    @Test
    public void testSkipThrowsIOException() {
        IOException exception = new IOException("Test exception");
        BrokenReader reader = new BrokenReader(exception);
        assertThrows(IOException.class, () -> reader.skip(0));
    }

    @Test
    public void testReadyThrowsIOException() {
        IOException exception = new IOException("Test exception");
        BrokenReader reader = new BrokenReader(exception);
        assertThrows(IOException.class, reader::ready);
    }

    @Test
    public void testMarkThrowsIOException() {
        IOException exception = new IOException("Test exception");
        BrokenReader reader = new BrokenReader(exception);
        assertThrows(IOException.class, () -> reader.mark(0));
    }

    @Test
    public void testResetThrowsIOException() {
        IOException exception = new IOException("Test exception");
        BrokenReader reader = new BrokenReader(exception);
        assertThrows(IOException.class, reader::reset);
    }

    @Test
    public void testCloseThrowsIOException() {
        IOException exception = new IOException("Test exception");
        BrokenReader reader = new BrokenReader(exception);
        assertThrows(IOException.class, reader::close);
    }

    @Test
    public void testDefaultConstructorThrowsIOException() {
        BrokenReader reader = new BrokenReader();
        assertThrows(IOException.class, () -> reader.read(new char[0], 0, 0));
    }
}