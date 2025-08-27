package org.apache.commons.imaging.formats.png;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIccp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PngImageParserLLM_Test {

    @Test
    public void testGetBufferedImage_withInvalidICCProfile_throwsImageReadException() throws IOException, ImageReadException {
        // Arrange
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = new ByteArrayInputStream(new byte[]{});
        when(byteSource.getInputStream()).thenReturn(inputStream);

        PngChunkIccp iccpChunk = mock(PngChunkIccp.class);
        when(iccpChunk.getUncompressedProfile()).thenReturn(new byte[]{0, 1, 2, 3});
        List<PngChunkIccp> iccpChunks = Arrays.asList(iccpChunk);

        // Act & Assert
        assertThrows(ImageReadException.class, () -> {
            parser.getBufferedImage(byteSource, null);
        });
    }
}