package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Dimension;
import java.io.IOException;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiffImageParserLLM_Test {

    private TiffImageParser tiffImageParser;
    private ByteSource byteSource;
    private TiffImagingParameters params;

    @BeforeEach
    public void setUp() {
        tiffImageParser = new TiffImageParser();
        byteSource = mock(ByteSource.class);
        params = new TiffImagingParameters();
    }

    @Test
    public void testGetICCProfileBytesWithStrictParams() throws ImageReadException, IOException {
        params.setStrict(true);
        TiffReader tiffReader = mock(TiffReader.class);
        TiffContents tiffContents = mock(TiffContents.class);
        TiffDirectory tiffDirectory = mock(TiffDirectory.class);

        when(tiffReader.readFirstDirectory(byteSource, false, FormatCompliance.getDefault())).thenReturn(tiffContents);
        when(tiffContents.directories).thenReturn(List.of(tiffDirectory));
        when(tiffDirectory.getFieldValue(TiffEpTagConstants.EXIF_TAG_INTER_COLOR_PROFILE, false)).thenReturn(new byte[]{1, 2, 3});

        byte[] iccProfileBytes = tiffImageParser.getICCProfileBytes(byteSource, params);
        assertEquals(3, iccProfileBytes.length);
    }

    @Test
    public void testGetImageSizeWithStrictParams() throws ImageReadException, IOException {
        params.setStrict(true);
        TiffReader tiffReader = mock(TiffReader.class);
        TiffContents tiffContents = mock(TiffContents.class);
        TiffDirectory tiffDirectory = mock(TiffDirectory.class);
        TiffField widthField = mock(TiffField.class);
        TiffField heightField = mock(TiffField.class);

        when(tiffReader.readFirstDirectory(byteSource, false, FormatCompliance.getDefault())).thenReturn(tiffContents);
        when(tiffContents.directories).thenReturn(List.of(tiffDirectory));
        when(tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH, true)).thenReturn(widthField);
        when(tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH, true)).thenReturn(heightField);
        when(widthField.getIntValue()).thenReturn(100);
        when(heightField.getIntValue()).thenReturn(200);

        Dimension imageSize = tiffImageParser.getImageSize(byteSource, params);
        assertEquals(100, imageSize.width);
        assertEquals(200, imageSize.height);
    }

    @Test
    public void testGetImageInfoWithStrictParams() throws ImageReadException, IOException {
        params.setStrict(true);
        TiffReader tiffReader = mock(TiffReader.class);
        TiffContents tiffContents = mock(TiffContents.class);
        TiffDirectory tiffDirectory = mock(TiffDirectory.class);
        TiffField widthField = mock(TiffField.class);
        TiffField heightField = mock(TiffField.class);

        when(tiffReader.readDirectories(byteSource, false, FormatCompliance.getDefault())).thenReturn(tiffContents);
        when(tiffContents.directories).thenReturn(List.of(tiffDirectory));
        when(tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH, true)).thenReturn(widthField);
        when(tiffDirectory.findField(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH, true)).thenReturn(heightField);
        when(widthField.getIntValue()).thenReturn(100);
        when(heightField.getIntValue()).thenReturn(200);

        ImageInfo imageInfo = tiffImageParser.getImageInfo(byteSource, params);
        assertEquals(100, imageInfo.getWidth());
        assertEquals(200, imageInfo.getHeight());
    }

    @Test
    public void testCollectRawImageDataWithStrictParams() throws ImageReadException, IOException {
        params.setStrict(true);
        TiffReader tiffReader = mock(TiffReader.class);
        TiffContents tiffContents = mock(TiffContents.class);
        TiffDirectory tiffDirectory = mock(TiffDirectory.class);
        ImageDataElement imageDataElement = mock(ImageDataElement.class);

        when(tiffReader.readDirectories(byteSource, true, FormatCompliance.getDefault())).thenReturn(tiffContents);
        when(tiffContents.directories).thenReturn(List.of(tiffDirectory));
        when(tiffDirectory.getTiffRawImageDataElements()).thenReturn(List.of(imageDataElement));
        when(imageDataElement.offset).thenReturn(0L);
        when(imageDataElement.length).thenReturn(10L);
        when(byteSource.getBlock(0L, 10L)).thenReturn(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        List<byte[]> rawData = tiffImageParser.collectRawImageData(byteSource, params);
        assertEquals(1, rawData.size());
        assertEquals(10, rawData.get(0).length);
    }
}