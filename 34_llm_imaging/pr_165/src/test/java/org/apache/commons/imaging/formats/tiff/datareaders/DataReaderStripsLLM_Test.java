package org.apache.commons.imaging.formats.tiff.datareaders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;
import java.io.IOException;
import java.nio.ByteOrder;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.constants.TiffTagConstants;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.junit.jupiter.api.Test;

public class DataReaderStripsLLM_Test {

    @Test
    public void testReadRasterDataUnsupportedSampleFormat() {
        final int[] bitsPerPixel = {1, 2, 3};
        final DataReaderStrips strips = new DataReaderStrips(
            null, null, 3, bitsPerPixel, 2, 4, 0, 3, 1, 1,
            TiffPlanarConfiguration.CHUNKY, ByteOrder.BIG_ENDIAN, 2, null);

        assertThrows(ImageReadException.class, () -> {
            strips.readRasterData(null);
        });
    }

    @Test
    public void testReadRasterDataFloat() throws ImageReadException, IOException {
        final int[] bitsPerPixel = {32};
        final TiffImageData.Strips imageData = new TiffImageData.Strips(new TiffImageData.Strips.Strip[] {
            new TiffImageData.Strips.Strip(new byte[32], 0)
        });
        final DataReaderStrips strips = new DataReaderStrips(
            null, null, 32, bitsPerPixel, 1, 1, TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT, 2, 2, 1,
            TiffPlanarConfiguration.CHUNKY, ByteOrder.BIG_ENDIAN, 2, imageData);

        TiffRasterData data = strips.readRasterData(null);
        assertTrue(data instanceof TiffRasterDataFloat);
    }

    @Test
    public void testReadRasterDataInt() throws ImageReadException, IOException {
        final int[] bitsPerPixel = {32};
        final TiffImageData.Strips imageData = new TiffImageData.Strips(new TiffImageData.Strips.Strip[] {
            new TiffImageData.Strips.Strip(new byte[32], 0)
        });
        final DataReaderStrips strips = new DataReaderStrips(
            null, null, 32, bitsPerPixel, 1, 1, TiffTagConstants.SAMPLE_FORMAT_VALUE_TWOS_COMPLEMENT_SIGNED_INTEGER, 2, 2, 1,
            TiffPlanarConfiguration.CHUNKY, ByteOrder.BIG_ENDIAN, 2, imageData);

        TiffRasterData data = strips.readRasterData(null);
        assertTrue(data instanceof TiffRasterDataInt);
    }
}