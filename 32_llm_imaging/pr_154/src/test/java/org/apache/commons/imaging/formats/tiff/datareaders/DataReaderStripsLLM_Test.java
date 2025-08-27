package org.apache.commons.imaging.formats.tiff.datareaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreterRgb;
import org.junit.jupiter.api.Test;
import java.awt.Rectangle;
import java.nio.ByteOrder;

public class DataReaderStripsLLM_Test {

    @Test
    public void testInterpretStrip24Bit() throws Exception {
        // Setup
        final int bitsPerPixel = 24;
        final int width = 2;
        final int height = 2;
        final int rowsPerStrip = 2;
        final int[] bitsPerSample = {8, 8, 8};
        final byte[] bytes = {
            (byte) 0xff, 0x00, 0x00, // Red
            0x00, (byte) 0xff, 0x00, // Green
            0x00, 0x00, (byte) 0xff, // Blue
            (byte) 0xff, (byte) 0xff, 0x00 // Yellow
        };
        final TiffImageData.Strips imageData = new TiffImageData.Strips(new TiffImageData.Strips.Strip[] {
            new TiffImageData.Strips.Strip(bytes, 0)
        });
        final DataReaderStrips dataReaderStrips = new DataReaderStrips(
            null, new PhotometricInterpreterRgb(), bitsPerPixel, bitsPerSample, 1, 3, 0, width, height, 1,
            TiffPlanarConfiguration.CHUNKY, ByteOrder.BIG_ENDIAN, rowsPerStrip, imageData
        );
        final ImageBuilder imageBuilder = new ImageBuilder(width, height, false, false);

        // Execute
        dataReaderStrips.interpretStrip(imageBuilder, bytes, width * height, height);

        // Verify
        assertEquals(0xffff0000, imageBuilder.getRGB(0, 0)); // Red
        assertEquals(0xff00ff00, imageBuilder.getRGB(1, 0)); // Green
        assertEquals(0xff0000ff, imageBuilder.getRGB(0, 1)); // Blue
        assertEquals(0xffffff00, imageBuilder.getRGB(1, 1)); // Yellow
    }

    @Test
    public void testInterpretStrip32Bit() throws Exception {
        // Setup
        final int bitsPerPixel = 32;
        final int width = 2;
        final int height = 2;
        final int rowsPerStrip = 2;
        final int[] bitsPerSample = {8, 8, 8, 8};
        final byte[] bytes = {
            (byte) 0xff, 0x00, 0x00, (byte) 0xff, // Red
            0x00, (byte) 0xff, 0x00, (byte) 0xff, // Green
            0x00, 0x00, (byte) 0xff, (byte) 0xff, // Blue
            (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xff // Yellow
        };
        final TiffImageData.Strips imageData = new TiffImageData.Strips(new TiffImageData.Strips.Strip[] {
            new TiffImageData.Strips.Strip(bytes, 0)
        });
        final DataReaderStrips dataReaderStrips = new DataReaderStrips(
            null, new PhotometricInterpreterRgb(), bitsPerPixel, bitsPerSample, 1, 4, 0, width, height, 1,
            TiffPlanarConfiguration.CHUNKY, ByteOrder.BIG_ENDIAN, rowsPerStrip, imageData
        );
        final ImageBuilder imageBuilder = new ImageBuilder(width, height, false, false);

        // Execute
        dataReaderStrips.interpretStrip(imageBuilder, bytes, width * height, height);

        // Verify
        assertEquals(0xffff0000, imageBuilder.getRGB(0, 0)); // Red
        assertEquals(0xff00ff00, imageBuilder.getRGB(1, 0)); // Green
        assertEquals(0xff0000ff, imageBuilder.getRGB(0, 1)); // Blue
        assertEquals(0xffffff00, imageBuilder.getRGB(1, 1)); // Yellow
    }
}