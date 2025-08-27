package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.test.TestUtils;
import org.apache.commons.io.test.ThrowOnCloseInputStream;
import org.apache.commons.io.test.ThrowOnFlushAndCloseOutputStream;
import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
public class CopyUtilsLLM_Test {

    private static final int FILE_SIZE = 1024 * 4 + 1;
    private final byte[] inData = TestUtils.generateTestData(FILE_SIZE);

    @Test
    public void testCopy_inputStreamToOutputStream_noInitialValue() throws Exception {
        InputStream in = new ByteArrayInputStream(inData);
        in = new ThrowOnCloseInputStream(in);
        final ByteArrayOutputStream baout = new ByteArrayOutputStream();
        final OutputStream out = new ThrowOnFlushAndCloseOutputStream(baout, false, true);
        final int count = CopyUtils.copy(in, out);
        assertEquals(0, in.available(), "Not all bytes were read");
        assertEquals(inData.length, baout.size(), "Sizes differ");
        assertArrayEquals(inData, baout.toByteArray(), "Content differs");
        assertEquals(inData.length, count);
    }

    @Test
    public void testCopy_readerToWriter_noInitialValue() throws Exception {
        InputStream in = new ByteArrayInputStream(inData);
        in = new ThrowOnCloseInputStream(in);
        final Reader reader = new java.io.InputStreamReader(in, StandardCharsets.US_ASCII);
        final ByteArrayOutputStream baout = new ByteArrayOutputStream();
        final OutputStream out = new ThrowOnFlushAndCloseOutputStream(baout, false, true);
        final Writer writer = new java.io.OutputStreamWriter(out, StandardCharsets.US_ASCII);
        final int count = CopyUtils.copy(reader, writer);
        writer.flush();
        assertEquals(inData.length, count, "The number of characters returned by copy is wrong");
        assertEquals(inData.length, baout.size(), "Sizes differ");
        assertArrayEquals(inData, baout.toByteArray(), "Content differs");
    }
}