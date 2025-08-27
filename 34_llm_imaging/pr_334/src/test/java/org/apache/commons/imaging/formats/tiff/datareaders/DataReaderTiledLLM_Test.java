package org.apache.commons.imaging.formats.tiff.datareaders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Rectangle;
import java.io.IOException;
import java.nio.ByteOrder;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.tiff.AbstractTiffImageData;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataReaderTiledLLM_Test {

    private TiffDirectory mockDirectory;
    private PhotometricInterpreter mockPhotometricInterpreter;
    private AbstractTiffImageData.Tiles mockImageData;
    private DataReaderTiled dataReaderTiled;

    @BeforeEach
    public void setUp() {
        mockDirectory = mock(TiffDirectory.class);
        mockPhotometricInterpreter = mock(PhotometricInterpreter.class);
        mockImageData = mock(AbstractTiffImageData.Tiles.class);
        dataReaderTiled = new DataReaderTiled(
            mockDirectory,
            mockPhotometricInterpreter,
            256, // tileWidth
            256, // tileLength
            24, // bitsPerPixel
            new int[]{8, 8, 8}, // bitsPerSample
            TiffTagConstants.PREDICTOR_NONE, // predictor
            3, // samplesPerPixel
            TiffTagConstants.SAMPLE_FORMAT_VALUE_UNSPECIFIED, // sampleFormat
            1024, // width
            1024, // height
            TiffTagConstants.COMPRESSION_UNCOMPRESSED, // compression
            TiffPlanarConfiguration.CHUNKY, // planarConfiguration
            ByteOrder.BIG_ENDIAN, // byteOrder
            mockImageData // imageData
        );
    }

    @Test
    public void testReadImageDataWithJpegCompression() throws IOException, ImagingException {
        // Set up the mock data
        byte[] mockCompressedData = new byte[256 * 256 * 3];
        AbstractTiffImageData.Tile mockTile = mock(AbstractTiffImageData.Tile.class);
        when(mockTile.getData()).thenReturn(mockCompressedData);
        when(mockImageData.tiles).thenReturn(new AbstractTiffImageData.Tile[]{mockTile});

        // Set up the DataReaderTiled with JPEG compression
        dataReaderTiled = new DataReaderTiled(
            mockDirectory,
            mockPhotometricInterpreter,
            256, // tileWidth
            256, // tileLength
            24, // bitsPerPixel
            new int[]{8, 8, 8}, // bitsPerSample
            TiffTagConstants.PREDICTOR_NONE, // predictor
            3, // samplesPerPixel
            TiffTagConstants.SAMPLE_FORMAT_VALUE_UNSPECIFIED, // sampleFormat
            1024, // width
            1024, // height
            TiffTagConstants.COMPRESSION_JPEG, // compression
            TiffPlanarConfiguration.CHUNKY, // planarConfiguration
            ByteOrder.BIG_ENDIAN, // byteOrder
            mockImageData // imageData
        );

        // Define the subImageSpecification
        Rectangle subImageSpecification = new Rectangle(0, 0, 512, 512);

        // Call the method under test
        ImageBuilder result = dataReaderTiled.readImageData(subImageSpecification, false, false);

        // Verify the interactions and results
        assertNotNull(result);
        verify(mockImageData.tiles[0]).getData();
    }

    @Test
    public void testReadImageDataWithPlanarJpegCompressionThrowsException() {
        // Set up the DataReaderTiled with JPEG compression and planar configuration
        dataReaderTiled = new DataReaderTiled(
            mockDirectory,
            mockPhotometricInterpreter,
            256, // tileWidth
            256, // tileLength
            24, // bitsPerPixel
            new int[]{8, 8, 8}, // bitsPerSample
            TiffTagConstants.PREDICTOR_NONE, // predictor
            3, // samplesPerPixel
            TiffTagConstants.SAMPLE_FORMAT_VALUE_UNSPECIFIED, // sampleFormat
            1024, // width
            1024, // height
            TiffTagConstants.COMPRESSION_JPEG, // compression
            TiffPlanarConfiguration.PLANAR, // planarConfiguration
            ByteOrder.BIG_ENDIAN, // byteOrder
            mockImageData // imageData
        );

        // Define the subImageSpecification
        Rectangle subImageSpecification = new Rectangle(0, 0, 512, 512);

        // Call the method under test and verify the exception
        ImagingException exception = assertThrows(ImagingException.class, () -> {
            dataReaderTiled.readImageData(subImageSpecification, false, false);
        });

        assertEquals("TIFF file in non-supported configuration: JPEG compression used in planar configuration.", exception.getMessage());
    }
}