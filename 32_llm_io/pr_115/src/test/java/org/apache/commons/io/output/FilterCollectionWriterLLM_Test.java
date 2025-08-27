package org.apache.commons.io.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterCollectionWriterLLM_Test {

    private StringWriter writer1;
    private StringWriter writer2;
    private FilterCollectionWriter filterCollectionWriter;

    @BeforeEach
    public void setUp() {
        writer1 = new StringWriter();
        writer2 = new StringWriter();
        Collection<Writer> writers = Arrays.asList(writer1, writer2);
        filterCollectionWriter = new FilterCollectionWriter(writers);
    }

    @Test
    public void testWriteCharArrayWithOffsetAndLength() throws IOException {
        char[] cbuf = "Hello, World!".toCharArray();
        filterCollectionWriter.write(cbuf, 7, 5);
        filterCollectionWriter.flush();
        assertTrue(writer1.toString().equals("World"));
        assertTrue(writer2.toString().equals("World"));
    }

    @Test
    public void testWriteCharArrayWithOffsetAndLengthThrowsIOException() {
        char[] cbuf = "Hello, World!".toCharArray();
        filterCollectionWriter = new FilterCollectionWriter(new StringWriter(), new FaultyWriter());
        assertThrows(IOException.class, () -> filterCollectionWriter.write(cbuf, 7, 5));
    }

    private static class FaultyWriter extends StringWriter {
        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            throw new IOException("Simulated IO Exception");
        }
    }
}