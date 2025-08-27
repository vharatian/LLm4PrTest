package org.apache.commons.imaging.formats.png;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.png.chunks.PngChunk;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIccp;
import org.apache.commons.imaging.formats.png.chunks.PngChunkSrgb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PngImageParserLLM_Test {

    @Mock
    private ByteSource byteSource;

    @Mock
    private InputStream inputStream;

    private PngImageParser pngImageParser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pngImageParser = new PngImageParser();
    }

    @Test
    public void testLoggerFinestLevelForSrgb() throws ImageReadException, IOException {
        Logger logger = Logger.getLogger(PngImageParser.class.getName());
        Level originalLevel = logger.getLevel();
        logger.setLevel(Level.FINEST);

        PngChunkSrgb pngChunkSrgb = mock(PngChunkSrgb.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(pngImageParser.readChunks(inputStream, new ChunkType[]{ChunkType.sRGB}, false))
                .thenReturn(List.of(pngChunkSrgb));

        pngImageParser.getBufferedImage(byteSource, null);

        assertTrue(logger.isLoggable(Level.FINEST));

        logger.setLevel(originalLevel);
    }

    @Test
    public void testLoggerFinestLevelForIccp() throws ImageReadException, IOException {
        Logger logger = Logger.getLogger(PngImageParser.class.getName());
        Level originalLevel = logger.getLevel();
        logger.setLevel(Level.FINEST);

        PngChunkIccp pngChunkIccp = mock(PngChunkIccp.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);
        when(pngImageParser.readChunks(inputStream, new ChunkType[]{ChunkType.iCCP}, false))
                .thenReturn(List.of(pngChunkIccp));

        pngImageParser.getBufferedImage(byteSource, null);

        assertTrue(logger.isLoggable(Level.FINEST));

        logger.setLevel(originalLevel);
    }
}