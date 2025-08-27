package org.apache.commons.io.output;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FilterCollectionWriterLLM_Test {

    private Writer mockWriter1;
    private Writer mockWriter2;
    private FilterCollectionWriter filterCollectionWriter;

    @BeforeEach
    public void setUp() {
        mockWriter1 = mock(Writer.class);
        mockWriter2 = mock(Writer.class);
        filterCollectionWriter = new FilterCollectionWriter(mockWriter1, mockWriter2);
    }

    @Test
    public void testAppendChar() throws IOException {
        filterCollectionWriter.append('a');
        verify(mockWriter1).append('a');
        verify(mockWriter2).append('a');
    }

    @Test
    public void testAppendCharSequence() throws IOException {
        CharSequence csq = "test";
        filterCollectionWriter.append(csq);
        verify(mockWriter1).append(csq);
        verify(mockWriter2).append(csq);
    }

    @Test
    public void testAppendCharSequenceWithStartEnd() throws IOException {
        CharSequence csq = "test";
        filterCollectionWriter.append(csq, 1, 3);
        verify(mockWriter1).append(csq, 1, 3);
        verify(mockWriter2).append(csq, 1, 3);
    }

    @Test
    public void testClose() throws IOException {
        filterCollectionWriter.close();
        verify(mockWriter1).close();
        verify(mockWriter2).close();
    }

    @Test
    public void testFlush() throws IOException {
        filterCollectionWriter.flush();
        verify(mockWriter1).flush();
        verify(mockWriter2).flush();
    }

    @Test
    public void testWriteCharArray() throws IOException {
        char[] cbuf = "test".toCharArray();
        filterCollectionWriter.write(cbuf);
        verify(mockWriter1).write(cbuf);
        verify(mockWriter2).write(cbuf);
    }

    @Test
    public void testWriteCharArrayWithOffsetAndLength() throws IOException {
        char[] cbuf = "test".toCharArray();
        filterCollectionWriter.write(cbuf, 1, 2);
        verify(mockWriter1).write(cbuf, 1, 2);
        verify(mockWriter2).write(cbuf, 1, 2);
    }

    @Test
    public void testWriteInt() throws IOException {
        filterCollectionWriter.write(97);
        verify(mockWriter1).write(97);
        verify(mockWriter2).write(97);
    }

    @Test
    public void testWriteString() throws IOException {
        String str = "test";
        filterCollectionWriter.write(str);
        verify(mockWriter1).write(str);
        verify(mockWriter2).write(str);
    }

    @Test
    public void testWriteStringWithOffsetAndLength() throws IOException {
        String str = "test";
        filterCollectionWriter.write(str, 1, 2);
        verify(mockWriter1).write(str, 1, 2);
        verify(mockWriter2).write(str, 1, 2);
    }

    @Test
    public void testNullWriters() throws IOException {
        FilterCollectionWriter writerWithNulls = new FilterCollectionWriter((Writer) null);
        writerWithNulls.write("test");
        // No exception should be thrown, and no interactions should occur
    }
}