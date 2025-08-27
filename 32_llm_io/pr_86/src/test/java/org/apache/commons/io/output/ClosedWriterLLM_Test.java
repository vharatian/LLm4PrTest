package org.apache.commons.io.output;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class ClosedWriterLLM_Test {

    @Test(expected = IOException.class)
    public void testWriteThrowsIOException() throws IOException {
        ClosedWriter writer = ClosedWriter.CLOSED_WRITER;
        writer.write(new char[]{'a', 'b', 'c'}, 0, 3);
    }

    @Test(expected = IOException.class)
    public void testFlushThrowsIOException() throws IOException {
        ClosedWriter writer = ClosedWriter.CLOSED_WRITER;
        writer.flush();
    }

    @Test
    public void testCloseDoesNotThrowException() throws IOException {
        ClosedWriter writer = ClosedWriter.CLOSED_WRITER;
        writer.close();
    }
}