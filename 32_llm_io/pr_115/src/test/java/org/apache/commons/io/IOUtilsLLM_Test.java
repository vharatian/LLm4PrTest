package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class IOUtilsLLM_Test {

    @Test
    public void testCopyInputStreamToWriterWithNullCharset() throws IOException {
        InputStream inputStream = mock(InputStream.class);
        Writer writer = mock(Writer.class);
        assertThrows(NullPointerException.class, () -> IOUtils.copy(inputStream, writer, (Charset) null));
    }

    @Test
    public void testWriteCharArrayToOutputStreamWithNullCharset() throws IOException {
        char[] data = "test data".toCharArray();
        OutputStream outputStream = mock(OutputStream.class);
        assertThrows(NullPointerException.class, () -> IOUtils.write(data, outputStream, (Charset) null));
    }
}