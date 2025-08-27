package org.apache.commons.imaging.formats.tiff;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Rectangle;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.datareaders.ImageDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiffImageParserLLM_Test {

    private TiffImageParser tiffImageParser;
    private TiffDirectory mockDirectory;
    private ByteSource mockByteSource;
    private Map<String, Object> params;

    @BeforeEach
    public void setUp() {
        tiffImageParser = new TiffImageParser();
        mockDirectory = mock(TiffDirectory.class);
        mockByteSource = mock(ByteSource.class);
        params = new HashMap<>();
    }

    @Test
    public void testGetRasterDataUnsupportedBitsPerSample() throws Exception {
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, true))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT});
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_PLANAR_CONFIGURATION))
                .thenReturn(null);
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_BITS_PER_SAMPLE))
                .thenReturn(new int[]{16});
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH))
                .thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH))
                .thenReturn(100);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));

        assertThrows(ImageReadException.class, () -> {
            tiffImageParser.getRasterData(mockDirectory, ByteOrder.BIG_ENDIAN, params);
        });
    }

    @Test
    public void testGetRasterDataPlanarConfiguration() throws Exception {
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_SAMPLE_FORMAT, true))
                .thenReturn(new short[]{TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT});
        when(mockDirectory.findField(TiffTagConstants.TIFF_TAG_PLANAR_CONFIGURATION))
                .thenReturn(null);
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_BITS_PER_SAMPLE))
                .thenReturn(new int[]{32});
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_WIDTH))
                .thenReturn(100);
        when(mockDirectory.getSingleFieldValue(TiffTagConstants.TIFF_TAG_IMAGE_LENGTH))
                .thenReturn(100);
        when(mockDirectory.getTiffImageData()).thenReturn(mock(TiffImageData.class));
        when(mockDirectory.getFieldValue(TiffTagConstants.TIFF_TAG_COMPRESSION))
                .thenReturn(TiffTagConstants.TIFF_COMPRESSION_UNCOMPRESSED_1);

        TiffRasterData rasterData = tiffImageParser.getRasterData(mockDirectory, ByteOrder.BIG_ENDIAN, params);
        assertEquals(TiffPlanarConfiguration.CHUNKY, rasterData.getPlanarConfiguration());
    }
}