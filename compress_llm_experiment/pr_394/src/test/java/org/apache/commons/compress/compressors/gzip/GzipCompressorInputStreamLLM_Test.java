package org.apache.commons.compress.compressors.gzip;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressorInputStreamLLM_Test {

    @Test
    public void testReadToNull() throws IOException {
        byte[] input = { 't', 'e', 's', 't', 0x00 };
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(input));
        byte[] result = GzipCompressorInputStream.readToNull(dataInputStream);
        assertArrayEquals(new byte[] { 't', 'e', 's', 't' }, result);
    }

    @Test
    public void testReadToNullWithEmptyInput() throws IOException {
        byte[] input = { 0x00 };
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(input));
        byte[] result = GzipCompressorInputStream.readToNull(dataInputStream);
        assertArrayEquals(new byte[] {}, result);
    }

    @Test
    public void testReadToNullWithNoNullTerminator() {
        byte[] input = { 't', 'e', 's', 't' };
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(input));
        assertThrows(IOException.class, () -> {
            GzipCompressorInputStream.readToNull(dataInputStream);
        });
    }
}