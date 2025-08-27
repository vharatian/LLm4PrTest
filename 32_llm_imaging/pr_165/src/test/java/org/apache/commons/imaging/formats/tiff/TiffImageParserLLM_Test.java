package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.datareaders.ImageDataReader;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterBiLevel;
import org.apache.commons.imaging.formats.tiff.write.TiffImageWriterLossy;
import org.junit.jupiter.api.Test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiffImageParserLLM_Test {

    @Test
    public void testGetRasterDataWithFloatingPoint() throws ImageReadException, IOException {
        TiffDirectory mockDirectory = mock(TiffDirectory.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        Map<String, Object> params = new HashMap<>();
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_X, 0);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_Y, 0);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_WIDTH, 100);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_HEIGHT, 100);

        when(mockDirectory.entries).thenReturn(mock(List.class));
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, true))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT});
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_SAMPLES_PER_PIXEL))
                .thenReturn(null);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_BITS_PER_SAMPLE))
                .thenReturn(null);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_COMPRESSION))
                .thenReturn(null);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH))
                .thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH))
                .thenReturn(100);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_PREDICTOR))
                .thenReturn(null);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));

        TiffImageParser parser = new TiffImageParser();
        TiffRasterData rasterData = parser.getRasterData(mockDirectory, byteOrder, params);

        assertNotNull(rasterData);
    }

    @Test
    public void testGetRasterDataWithInteger() throws ImageReadException, IOException {
        TiffDirectory mockDirectory = mock(TiffDirectory.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        Map<String, Object> params = new HashMap<>();
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_X, 0);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_Y, 0);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_WIDTH, 100);
        params.put(TiffConstants.PARAM_KEY_SUBIMAGE_HEIGHT, 100);

        when(mockDirectory.entries).thenReturn(mock(List.class));
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, true))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_TWOS_COMPLEMENT_SIGNED_INTEGER});
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_SAMPLES_PER_PIXEL))
                .thenReturn(null);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_BITS_PER_SAMPLE))
                .thenReturn(null);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_COMPRESSION))
                .thenReturn(null);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH))
                .thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH))
                .thenReturn(100);
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_PREDICTOR))
                .thenReturn(null);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));

        TiffImageParser parser = new TiffImageParser();
        TiffRasterData rasterData = parser.getRasterData(mockDirectory, byteOrder, params);

        assertNotNull(rasterData);
    }

    @Test
    public void testGetRasterDataWithUnsupportedFormat() {
        TiffDirectory mockDirectory = mock(TiffDirectory.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        Map<String, Object> params = new HashMap<>();

        when(mockDirectory.entries).thenReturn(mock(List.class));
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, true))
                .thenReturn(new short[]{0});

        TiffImageParser parser = new TiffImageParser();

        assertThrows(ImageReadException.class, () -> {
            parser.getRasterData(mockDirectory, byteOrder, params);
        });
    }
}