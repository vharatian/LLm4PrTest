package org.apache.commons.io;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.io.output.AppendableWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IOUtilsLLM_Test {

    @Test
    public void testWriterWithWriter() {
        Writer writer = new StringBuilderWriter();
        Writer result = IOUtils.writer(writer);
        assertEquals(writer, result, "Expected the same Writer instance to be returned");
    }

    @Test
    public void testWriterWithStringBuilder() {
        StringBuilder sb = new StringBuilder();
        Writer result = IOUtils.writer(sb);
        assertEquals(StringBuilderWriter.class, result.getClass(), "Expected a StringBuilderWriter instance");
    }

    @Test
    public void testWriterWithAppendable() {
        Appendable appendable = new StringBuilder();
        Writer result = IOUtils.writer(appendable);
        assertEquals(AppendableWriter.class, result.getClass(), "Expected an AppendableWriter instance");
    }

    @Test
    public void testWriterWithNull() {
        assertThrows(NullPointerException.class, () -> IOUtils.writer(null), "Expected NullPointerException for null input");
    }
}