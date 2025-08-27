package org.apache.commons.compress.archivers.arj;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import org.junit.Test;

public class ArjArchiveInputStreamLLM_Test {

    @Test
    public void testReadStringWithCharset() throws Exception {
        byte[] data = { 't', 'e', 's', 't', 0 };
        try (DataInputStream dataIn = new DataInputStream(new ByteArrayInputStream(data))) {
            ArjArchiveInputStream arjStream = new ArjArchiveInputStream(new ByteArrayInputStream(new byte[0]), "UTF-8");
            String result = arjStream.readString(dataIn);
            assertEquals("test", result);
        }
    }

    @Test
    public void testReadStringWithoutCharset() throws Exception {
        byte[] data = { 't', 'e', 's', 't', 0 };
        try (DataInputStream dataIn = new DataInputStream(new ByteArrayInputStream(data))) {
            ArjArchiveInputStream arjStream = new ArjArchiveInputStream(new ByteArrayInputStream(new byte[0]));
            String result = arjStream.readString(dataIn);
            assertEquals("test", result);
        }
    }
}