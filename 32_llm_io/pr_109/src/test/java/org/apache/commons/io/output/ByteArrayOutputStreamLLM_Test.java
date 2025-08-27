package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ByteArrayOutputStreamLLM_Test {

    @Test
    public void testToInputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write("test".getBytes());
        InputStream inputStream = byteArrayOutputStream.toInputStream();
        assertTrue(inputStream instanceof ByteArrayInputStream);
        byte[] buffer = new byte[4];
        int bytesRead = inputStream.read(buffer);
        assertEquals(4, bytesRead);
        assertArrayEquals("test".getBytes(), buffer);
    }
}