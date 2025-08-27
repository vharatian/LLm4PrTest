package org.apache.commons.compress.compressors.gzip;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressorInputStreamLLM_Test {

    @Test
    public void testInitWithISO8859_1Charset() throws IOException {
        byte[] gzipHeader = new byte[]{
                0x1f, (byte) 0x8b, // Magic number
                0x08, // Compression method (deflate)
                0x08, // Flags (FNAME)
                0x00, 0x00, 0x00, 0x00, // Modification time
                0x00, // Extra flags
                0x00, // Operating system
                't', 'e', 's', 't', 0x00 // Original file name (null-terminated)
        };

        ByteArrayInputStream bais = new ByteArrayInputStream(gzipHeader);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);

        assertEquals("test", gzipIn.getMetaData().getFilename());
    }

    @Test
    public void testInitWithCommentISO8859_1Charset() throws IOException {
        byte[] gzipHeader = new byte[]{
                0x1f, (byte) 0x8b, // Magic number
                0x08, // Compression method (deflate)
                0x10, // Flags (FCOMMENT)
                0x00, 0x00, 0x00, 0x00, // Modification time
                0x00, // Extra flags
                0x00, // Operating system
                't', 'e', 's', 't', 0x00 // Comment (null-terminated)
        };

        ByteArrayInputStream bais = new ByteArrayInputStream(gzipHeader);
        GzipCompressorInputStream gzipIn = new GzipCompressorInputStream(bais);

        assertEquals("test", gzipIn.getMetaData().getComment());
    }
}