package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.lang.StringBuilder;
import static org.junit.jupiter.api.Assertions.*;

public class AppendableWriterLLM_Test {

    @Test
    public void testWriteSingleCharacter() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        writer.write('a');
        assertEquals("a", sb.toString());
    }

    @Test
    public void testWriteCharArray() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        char[] chars = {'a', 'b', 'c'};
        writer.write(chars, 0, 3);
        assertEquals("abc", sb.toString());
    }

    @Test
    public void testWriteCharArrayWithOffsetAndLength() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        writer.write(chars, 1, 3);
        assertEquals("bcd", sb.toString());
    }

    @Test
    public void testWriteString() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        writer.write("hello", 0, 5);
        assertEquals("hello", sb.toString());
    }

    @Test
    public void testAppendCharSequence() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        writer.append("hello");
        assertEquals("hello", sb.toString());
    }

    @Test
    public void testAppendCharSequenceWithStartAndEnd() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        writer.append("hello world", 0, 5);
        assertEquals("hello", sb.toString());
    }

    @Test
    public void testAppendChar() throws IOException {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        writer.append('a');
        assertEquals("a", sb.toString());
    }

    @Test
    public void testGetAppendable() {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        assertEquals(sb, writer.getAppendable());
    }

    @Test
    public void testWriteCharArrayNull() {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        assertThrows(NullPointerException.class, () -> writer.write((char[]) null, 0, 1));
    }

    @Test
    public void testWriteStringNull() {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        assertThrows(NullPointerException.class, () -> writer.write((String) null, 0, 1));
    }

    @Test
    public void testWriteCharArrayIndexOutOfBounds() {
        StringBuilder sb = new StringBuilder();
        AppendableWriter<StringBuilder> writer = new AppendableWriter<>(sb);
        char[] chars = {'a', 'b', 'c'};
        assertThrows(IndexOutOfBoundsException.class, () -> writer.write(chars, 0, 4));
    }
}