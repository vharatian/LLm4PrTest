package org.apache.commons.imaging.formats.png;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PngImageParserLLM_Test {

    @Test
    public void testGetChunkTypes() throws ImageReadException, IOException {
        // Create a sample PNG signature followed by a dummy chunk
        byte[] pngData = new byte[]{
            (byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n', // PNG signature
            0, 0, 0, 13, // Length of IHDR chunk
            'I', 'H', 'D', 'R', // IHDR chunk type
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // IHDR chunk data
            0, 0, 0, 0 // CRC
        };
        InputStream is = new ByteArrayInputStream(pngData);
        PngImageParser parser = new PngImageParser();
        List<String> chunkTypes = parser.getChunkTypes(is);
        assertNotNull(chunkTypes);
        assertEquals(1, chunkTypes.size());
        assertEquals("IHDR", chunkTypes.get(0));
    }

    @Test
    public void testGetChunkTypesWithInvalidSignature() {
        byte[] invalidPngData = new byte[]{
            'I', 'N', 'V', 'A', 'L', 'I', 'D', '!', // Invalid signature
            0, 0, 0, 13, // Length of IHDR chunk
            'I', 'H', 'D', 'R', // IHDR chunk type
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // IHDR chunk data
            0, 0, 0, 0 // CRC
        };
        InputStream is = new ByteArrayInputStream(invalidPngData);
        PngImageParser parser = new PngImageParser();
        assertThrows(ImageReadException.class, () -> parser.getChunkTypes(is));
    }

    @Test
    public void testGetChunkTypesWithIOException() {
        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Test IOException");
            }
        };
        PngImageParser parser = new PngImageParser();
        assertThrows(IOException.class, () -> parser.getChunkTypes(is));
    }
}