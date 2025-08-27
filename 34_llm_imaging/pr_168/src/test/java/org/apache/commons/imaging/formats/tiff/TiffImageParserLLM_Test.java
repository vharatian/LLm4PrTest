package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.datareaders.ImageDataReader;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterRgb;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiffImageParserLLM_Test {

    @Test
    public void testGetBufferedImageWithAlpha() throws ImageReadException, IOException {
        TiffDirectory mockDirectory = mock(TiffDirectory.class);
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION)).thenReturn(TiffTagConstants.PHOTOMETRIC_INTERPRETATION_VALUE_RGB);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_EXTRA_SAMPLES)).thenReturn(null);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH)).thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH)).thenReturn(100);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));
        when(mockDirectory.entries).thenReturn(new ArrayList<>());

        TiffImageParser parser = new TiffImageParser();
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        Map<String, Object> params = new HashMap<>();

        BufferedImage image = parser.getBufferedImage(mockDirectory, byteOrder, params);

        assertNotNull(image);
        assertEquals(BufferedImage.TYPE_INT_ARGB, image.getType());
    }

    @Test
    public void testGetBufferedImageWithoutAlpha() throws ImageReadException, IOException {
        TiffDirectory mockDirectory = mock(TiffDirectory.class);
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION)).thenReturn(TiffTagConstants.PHOTOMETRIC_INTERPRETATION_VALUE_RGB);
        TiffField mockExtraSamplesField = mock(TiffField.class);
        when(mockExtraSamplesField.getIntValue()).thenReturn(0);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_EXTRA_SAMPLES)).thenReturn(mockExtraSamplesField);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH)).thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH)).thenReturn(100);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));
        when(mockDirectory.entries).thenReturn(new ArrayList<>());

        TiffImageParser parser = new TiffImageParser();
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        Map<String, Object> params = new HashMap<>();

        BufferedImage image = parser.getBufferedImage(mockDirectory, byteOrder, params);

        assertNotNull(image);
        assertEquals(BufferedImage.TYPE_INT_RGB, image.getType());
    }

    @Test
    public void testGetBufferedImageWithAssociatedAlpha() throws ImageReadException, IOException {
        TiffDirectory mockDirectory = mock(TiffDirectory.class);
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION)).thenReturn(TiffTagConstants.PHOTOMETRIC_INTERPRETATION_VALUE_RGB);
        TiffField mockExtraSamplesField = mock(TiffField.class);
        when(mockExtraSamplesField.getIntValue()).thenReturn(TiffTagConstants.EXTRA_SAMPLE_ASSOCIATED_ALPHA);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_EXTRA_SAMPLES)).thenReturn(mockExtraSamplesField);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH)).thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH)).thenReturn(100);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));
        when(mockDirectory.entries).thenReturn(new ArrayList<>());

        TiffImageParser parser = new TiffImageParser();
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        Map<String, Object> params = new HashMap<>();

        BufferedImage image = parser.getBufferedImage(mockDirectory, byteOrder, params);

        assertNotNull(image);
        assertEquals(BufferedImage.TYPE_INT_ARGB_PRE, image.getType());
    }
}