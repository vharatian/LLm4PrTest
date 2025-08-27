package org.apache.commons.imaging.formats.tiff.datareaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.awt.Rectangle;
import java.nio.ByteOrder;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.ImageBuilder;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.junit.jupiter.api.Test;

public class DataReaderStripsLLM_Test {

    @Test
    public void testInterpretStripWithFloatingPointSamples() throws Exception {
        TiffDirectory directory = null;
        PhotometricInterpreter photometricInterpreter = null;
        int bitsPerPixel = 32;
        int[] bitsPerSample = {32};
        int predictor = 1;
        int samplesPerPixel = 1;
        int sampleFormat = 3; // IEEE floating point
        int width = 2;
        int height = 2;
        int compression = 1;
        TiffPlanarConfiguration planarConfiguration = TiffPlanarConfiguration.CHUNKY;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int rowsPerStrip = 2;
        TiffImageData.Strips imageData = null;

        DataReaderStrips strips = new DataReaderStrips(directory, photometricInterpreter, bitsPerPixel, bitsPerSample, predictor, samplesPerPixel, sampleFormat, width, height, compression, planarConfiguration, byteOrder, rowsPerStrip, imageData);

        ImageBuilder imageBuilder = new ImageBuilder(width, height, false, false);
        byte[] bytes = new byte[] {0x3f, (byte)0x80, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00}; // 1.0f and 2.0f in IEEE 754
        int pixelsPerStrip = 2;
        int yLimit = 2;

        strips.interpretStrip(imageBuilder, bytes, pixelsPerStrip, yLimit);

        // Verify the interpreted values
        assertEquals(0xff000000, imageBuilder.getRGB(0, 0)); // 1.0f interpreted as black
        assertEquals(0xff000000, imageBuilder.getRGB(1, 0)); // 2.0f interpreted as black
    }

    @Test
    public void testReadRasterDataUnsupportedSampleFormat() {
        TiffDirectory directory = null;
        PhotometricInterpreter photometricInterpreter = null;
        int bitsPerPixel = 8;
        int[] bitsPerSample = {8};
        int predictor = 1;
        int samplesPerPixel = 1;
        int sampleFormat = 99; // Unsupported sample format
        int width = 2;
        int height = 2;
        int compression = 1;
        TiffPlanarConfiguration planarConfiguration = TiffPlanarConfiguration.CHUNKY;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int rowsPerStrip = 2;
        TiffImageData.Strips imageData = null;

        DataReaderStrips strips = new DataReaderStrips(directory, photometricInterpreter, bitsPerPixel, bitsPerSample, predictor, samplesPerPixel, sampleFormat, width, height, compression, planarConfiguration, byteOrder, rowsPerStrip, imageData);

        assertThrows(ImageReadException.class, () -> {
            strips.readRasterData(new Rectangle(0, 0, width, height));
        });
    }

    @Test
    public void testReadRasterDataFloat() throws Exception {
        TiffDirectory directory = null;
        PhotometricInterpreter photometricInterpreter = null;
        int bitsPerPixel = 32;
        int[] bitsPerSample = {32};
        int predictor = 1;
        int samplesPerPixel = 1;
        int sampleFormat = 3; // IEEE floating point
        int width = 2;
        int height = 2;
        int compression = 1;
        TiffPlanarConfiguration planarConfiguration = TiffPlanarConfiguration.CHUNKY;
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int rowsPerStrip = 2;
        TiffImageData.Strips imageData = null;

        DataReaderStrips strips = new DataReaderStrips(directory, photometricInterpreter, bitsPerPixel, bitsPerSample, predictor, samplesPerPixel, sampleFormat, width, height, compression, planarConfiguration, byteOrder, rowsPerStrip, imageData);

        // Mocked data for testing
        Rectangle subImage = new Rectangle(0, 0, width, height);

        // This should not throw an exception
        strips.readRasterData(subImage);
    }
}