package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrokenWriterLLM_Test {

    @Test
    public void testWriteThrowsIOException() {
        BrokenWriter writer = new BrokenWriter();
        assertThrows(IOException.class, () -> writer.write(new char[0], 0, 0));
    }

    @Test
    public void testFlushThrowsIOException() {
        BrokenWriter writer = new BrokenWriter();
        assertThrows(IOException.class, writer::flush);
    }

    @Test
    public void testCloseThrowsIOException() {
        BrokenWriter writer = new BrokenWriter();
        assertThrows(IOException.class, writer::close);
    }

    @Test
    public void testCustomException() {
        IOException customException = new IOException("Custom exception");
        BrokenWriter writer = new BrokenWriter(customException);
        assertThrows(IOException.class, () -> writer.write(new char[0], 0, 0), "Custom exception");
        assertThrows(IOException.class, writer::flush, "Custom exception");
        assertThrows(IOException.class, writer::close, "Custom exception");
    }
}