package org.apache.commons.imaging.formats.png;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceInputStream;
import org.apache.commons.imaging.formats.png.chunks.ChunkType;
import org.apache.commons.imaging.formats.png.chunks.PngChunk;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PngImageParserLLM_Test {

    @Test
    public void testReadChunksWithInvalidLength() {
        PngImageParser parser = new PngImageParser();
        byte[] invalidPngData = new byte[]{
                (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A, // PNG signature
                0x00, 0x00, 0x00, (byte) 0xFF, // Invalid length (negative value when interpreted as signed int)
                0x49, 0x48, 0x44, 0x52 // IHDR chunk type
        };
        ByteSource byteSource = new ByteSourceInputStream(new ByteArrayInputStream(invalidPngData), "invalidPngData");

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            parser.readChunks(byteSource.getInputStream(), new ChunkType[]{ChunkType.IHDR}, false);
        });

        assertTrue(exception.getMessage().contains("Invalid PNG chunk length"));
    }

    @Test
    public void testReadChunksWithValidLength() throws ImageReadException, IOException {
        PngImageParser parser = new PngImageParser();
        byte[] validPngData = new byte[]{
                (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A, // PNG signature
                0x00, 0x00, 0x00, 0x0D, // Valid length
                0x49, 0x48, 0x44, 0x52, // IHDR chunk type
                // IHDR chunk data (13 bytes)
                0x00, 0x00, 0x00, 0x01, // Width: 1
                0x00, 0x00, 0x00, 0x01, // Height: 1
                0x08, // Bit depth: 8
                0x02, // Color type: Truecolor
                0x00, // Compression method: deflate
                0x00, // Filter method: adaptive
                0x00, // Interlace method: no interlace
                0x1F, 0x15, (byte) 0xC4, (byte) 0x89 // CRC
        };
        ByteSource byteSource = new ByteSourceInputStream(new ByteArrayInputStream(validPngData), "validPngData");

        List<PngChunk> chunks = parser.readChunks(byteSource.getInputStream(), new ChunkType[]{ChunkType.IHDR}, false);

        assertNotNull(chunks);
        assertEquals(1, chunks.size());
        assertEquals(ChunkType.IHDR.value, chunks.get(0).chunkType);
    }
}