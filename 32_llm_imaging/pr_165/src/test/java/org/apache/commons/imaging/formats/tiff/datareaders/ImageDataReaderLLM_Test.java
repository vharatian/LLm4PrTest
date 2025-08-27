package org.apache.commons.imaging.formats.tiff.datareaders;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.BitInputStream;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageDataReaderLLM_Test {

    private TiffDirectory mockDirectory;
    private PhotometricInterpreter mockPhotometricInterpreter;
    private ImageDataReader imageDataReader;

    @BeforeEach
    public void setUp() {
        mockDirectory = mock(TiffDirectory.class);
        mockPhotometricInterpreter = mock(PhotometricInterpreter.class);
        imageDataReader = new ImageDataReader(mockDirectory, mockPhotometricInterpreter, new int[]{8}, 1, 1, 1, 100, 100) {
            @Override
            public ImageBuilder readImageData(Rectangle subImageSpecification, boolean hasAlpha, boolean isAlphaPremultiplied) throws ImageReadException, IOException {
                return null;
            }

            @Override
            public TiffRasterData readRasterData(Rectangle subImage) throws ImageReadException, IOException {
                return null;
            }
        };
    }

    @Test
    public void testUnpackFloatingPointSamplesWithPredictor() throws ImageReadException {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        int[] expectedSamples = new int[]{0x00010203, 0x04050607};
        int[] samples = imageDataReader.unpackFloatingPointSamples(2, 1, 2, bytes, TiffTagConstants.PREDICTOR_VALUE_FLOATING_POINT_DIFFERENCING, 32, ByteOrder.BIG_ENDIAN);
        assertArrayEquals(expectedSamples, samples);
    }

    @Test
    public void testUnpackFloatingPointSamplesWithoutPredictor() throws ImageReadException {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        int[] expectedSamples = new int[]{0x00010203, 0x04050607};
        int[] samples = imageDataReader.unpackFloatingPointSamples(2, 1, 2, bytes, 1, 32, ByteOrder.BIG_ENDIAN);
        assertArrayEquals(expectedSamples, samples);
    }

    @Test
    public void testUnpackIntSamples() {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        int[] expectedSamples = new int[]{0x0100, 0x0302, 0x0504, 0x0706};
        int[] samples = imageDataReader.unpackIntSamples(4, 1, 4, bytes, 1, 16, ByteOrder.LITTLE_ENDIAN);
        assertArrayEquals(expectedSamples, samples);
    }

    @Test
    public void testUnpackIntSamplesWithPredictor() {
        byte[] bytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        int[] expectedSamples = new int[]{0x0100, 0x0402, 0x0904, 0x1006};
        int[] samples = imageDataReader.unpackIntSamples(4, 1, 4, bytes, TiffTagConstants.PREDICTOR_VALUE_HORIZONTAL_DIFFERENCING, 16, ByteOrder.LITTLE_ENDIAN);
        assertArrayEquals(expectedSamples, samples);
    }

    @Test
    public void testTransferBlockToRaster() {
        int[] blockData = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] rasterData = new int[16];
        imageDataReader.transferBlockToRaster(0, 0, 2, 2, blockData, 0, 0, 4, 4, rasterData);
        int[] expectedRasterData = new int[]{1, 2, 0, 0, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedRasterData, rasterData);
    }

    @Test
    public void testTransferBlockToRasterWithOffset() {
        int[] blockData = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] rasterData = new int[16];
        imageDataReader.transferBlockToRaster(1, 1, 2, 2, blockData, 0, 0, 4, 4, rasterData);
        int[] expectedRasterData = new int[]{0, 0, 0, 0, 0, 1, 2, 0, 0, 3, 4, 0, 0, 0, 0, 0};
        assertArrayEquals(expectedRasterData, rasterData);
    }
}