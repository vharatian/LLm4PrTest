package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TiffImageParserLLM_Test {

    @Test
    public void testGetBufferedImageWithValidPartialImageParams() throws ImageReadException, IOException {
        // Arrange
        TiffImageParser parser = new TiffImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_X, 0);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_Y, 0);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_WIDTH, 100);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_HEIGHT, 100);

        // Mocking the behavior of byteSource and other dependencies
        when(byteSource.getBlock(0, 100)).thenReturn(new byte[100]);

        // Act
        BufferedImage image = parser.getBufferedImage(byteSource, params);

        // Assert
        assertNotNull(image, "BufferedImage should not be null");
    }

    @Test
    public void testGetBufferedImageWithInvalidPartialImageParams() throws ImageReadException, IOException {
        // Arrange
        TiffImageParser parser = new TiffImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_X, -1);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_Y, -1);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_WIDTH, -100);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_HEIGHT, -100);

        // Act & Assert
        assertThrows(ImageReadException.class, () -> {
            parser.getBufferedImage(byteSource, params);
        }, "Expected ImageReadException for invalid subimage parameters");
    }
}