package org.apache.commons.imaging.formats.tiff.datareaders;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.tiff.AbstractTiffImageData;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;
import java.nio.ByteOrder;

public class DataReaderStripsLLM_Test {

    @Test
    public void testReadImageDataWithJpegCompressionAndInterleaved() {
        TiffDirectory directory = null;
        PhotometricInterpreter photometricInterpreter = null;
        int bitsPerPixel = 8;
        int[] bitsPerSample = {8, 8, 8};
        int predictor = 1;
        int samplesPerPixel = 3;
        int sampleFormat = 1;
        int width = 10;
        int height = 10;
        int compression = TiffConstants.TIFF_COMPRESSION_JPEG;
        TiffPlanarConfiguration planarConfiguration = TiffPlanarConfiguration.CHUNKY;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int rowsPerStrip = 2;
        AbstractTiffImageData.Strips imageData = null;

        DataReaderStrips strips = new DataReaderStrips(directory, photometricInterpreter, bitsPerPixel, bitsPerSample, predictor, samplesPerPixel, sampleFormat, width, height, compression, planarConfiguration, byteOrder, rowsPerStrip, imageData);

        Rectangle subImageSpecification = new Rectangle(0, 0, width, height);
        assertDoesNotThrow(() -> {
            ImageBuilder imageBuilder = strips.readImageData(subImageSpecification, false, false);
        });
    }

    @Test
    public void testReadImageDataWithJpegCompressionAndPlanarConfiguration() {
        TiffDirectory directory = null;
        PhotometricInterpreter photometricInterpreter = null;
        int bitsPerPixel = 8;
        int[] bitsPerSample = {8, 8, 8};
        int predictor = 1;
        int samplesPerPixel = 3;
        int sampleFormat = 1;
        int width = 10;
        int height = 10;
        int compression = TiffConstants.TIFF_COMPRESSION_JPEG;
        TiffPlanarConfiguration planarConfiguration = TiffPlanarConfiguration.PLANAR;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int rowsPerStrip = 2;
        AbstractTiffImageData.Strips imageData = null;

        DataReaderStrips strips = new DataReaderStrips(directory, photometricInterpreter, bitsPerPixel, bitsPerSample, predictor, samplesPerPixel, sampleFormat, width, height, compression, planarConfiguration, byteOrder, rowsPerStrip, imageData);

        Rectangle subImageSpecification = new Rectangle(0, 0, width, height);
        assertThrows(ImagingException.class, () -> {
            strips.readImageData(subImageSpecification, false, false);
        });
    }
}