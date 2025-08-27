package org.apache.commons.imaging.formats.tiff.datareaders;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.jpeg.decoder.JpegDecoder;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.AdobePhotoshopTagConstants;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataInterpreterJpegLLM_Test {

    private DataInterpreterJpeg dataInterpreterJpeg;
    private TiffDirectory mockDirectory;
    private ImageBuilder mockImageBuilder;
    private JpegDecoder mockJpegDecoder;

    @BeforeEach
    void setUp() {
        dataInterpreterJpeg = new DataInterpreterJpeg();
        mockDirectory = mock(TiffDirectory.class);
        mockImageBuilder = mock(ImageBuilder.class);
        mockJpegDecoder = mock(JpegDecoder.class);
    }

    @Test
    void testInterpretBlockWithEmptyCompressedData() throws ImagingException, IOException {
        byte[] compressed = new byte[4];
        dataInterpreterJpeg.intepretBlock(mockDirectory, mockImageBuilder, 0, 0, 10, 10, compressed);
        verifyNoInteractions(mockImageBuilder);
    }

    @Test
    void testInterpretBlockWithNullField() throws ImagingException, IOException {
        byte[] compressed = new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xd9};
        when(mockDirectory.getFieldValue(AdobePhotoshopTagConstants.EXIF_TAG_JPEGTABLES, false)).thenReturn(null);
        when(mockJpegDecoder.decode(any(ByteSource.class))).thenReturn(mock(BufferedImage.class));

        dataInterpreterJpeg.intepretBlock(mockDirectory, mockImageBuilder, 0, 0, 10, 10, compressed);

        verify(mockDirectory).getFieldValue(AdobePhotoshopTagConstants.EXIF_TAG_JPEGTABLES, false);
        verify(mockJpegDecoder).decode(any(ByteSource.class));
    }

    @Test
    void testInterpretBlockWithField() throws ImagingException, IOException {
        byte[] compressed = new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xd9};
        byte[] field = new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xd9};
        when(mockDirectory.getFieldValue(AdobePhotoshopTagConstants.EXIF_TAG_JPEGTABLES, false)).thenReturn(field);
        when(mockJpegDecoder.decode(any(ByteSource.class))).thenReturn(mock(BufferedImage.class));

        dataInterpreterJpeg.intepretBlock(mockDirectory, mockImageBuilder, 0, 0, 10, 10, compressed);

        verify(mockDirectory).getFieldValue(AdobePhotoshopTagConstants.EXIF_TAG_JPEGTABLES, false);
        verify(mockJpegDecoder).decode(any(ByteSource.class));
    }

    @Test
    void testInterpretBlockWithPhotometricInterpretationRGB() throws ImagingException, IOException {
        byte[] compressed = new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xd9};
        TiffField mockTiffField = mock(TiffField.class);
        when(mockTiffField.getIntValue()).thenReturn(TiffTagConstants.PHOTOMETRIC_INTERPRETATION_VALUE_RGB);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION)).thenReturn(mockTiffField);
        when(mockJpegDecoder.decode(any(ByteSource.class))).thenReturn(mock(BufferedImage.class));

        dataInterpreterJpeg.intepretBlock(mockDirectory, mockImageBuilder, 0, 0, 10, 10, compressed);

        verify(mockDirectory).findField(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION);
        verify(mockJpegDecoder).setTiffRgb();
        verify(mockJpegDecoder).decode(any(ByteSource.class));
    }

    @Test
    void testInterpretBlockWithPhotometricInterpretationNonRGB() throws ImagingException, IOException {
        byte[] compressed = new byte[]{(byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xd9};
        TiffField mockTiffField = mock(TiffField.class);
        when(mockTiffField.getIntValue()).thenReturn(0); // Non-RGB value
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION)).thenReturn(mockTiffField);
        when(mockJpegDecoder.decode(any(ByteSource.class))).thenReturn(mock(BufferedImage.class));

        dataInterpreterJpeg.intepretBlock(mockDirectory, mockImageBuilder, 0, 0, 10, 10, compressed);

        verify(mockDirectory).findField(TiffTagConstants.TIFF_TAG_PHOTOMETRIC_INTERPRETATION);
        verify(mockJpegDecoder, never()).setTiffRgb();
        verify(mockJpegDecoder).decode(any(ByteSource.class));
    }
}