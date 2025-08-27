package org.apache.commons.imaging.formats.tiff.datareaders;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.tiff.TiffDirectory;
import org.apache.commons.imaging.formats.tiff.TiffImageData;
import org.apache.commons.imaging.formats.tiff.constants.TiffPlanarConfiguration;
import org.apache.commons.imaging.formats.tiff.photometricinterpreters.PhotometricInterpreter;
import org.junit.jupiter.api.Test;

import java.awt.Rectangle;
import java.io.IOException;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataReaderTiledLLM_Test {

    @Test
    public void testReadRasterDataFloat() throws ImageReadException, IOException {
        TiffDirectory directory = mock(TiffDirectory.class);
        PhotometricInterpreter photometricInterpreter = mock(PhotometricInterpreter.class);
        TiffImageData.Tiles imageData = mock(TiffImageData.Tiles.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

        DataReaderTiled dataReaderTiled = new DataReaderTiled(
                directory, photometricInterpreter, 256, 256, 32, new int[]{32}, 1, 1, 
                TiffTagConstants.SAMPLE_FORMAT_VALUE_IEEE_FLOATING_POINT, 1024, 1024, 
                1, TiffPlanarConfiguration.CHUNKY, byteOrder, imageData);

        Rectangle subImage = new Rectangle(0, 0, 512, 512);
        TiffRasterDataFloat rasterData = (TiffRasterDataFloat) dataReaderTiled.readRasterData(subImage);

        assertEquals(512, rasterData.getWidth());
        assertEquals(512, rasterData.getHeight());
    }

    @Test
    public void testReadRasterDataInt() throws ImageReadException, IOException {
        TiffDirectory directory = mock(TiffDirectory.class);
        PhotometricInterpreter photometricInterpreter = mock(PhotometricInterpreter.class);
        TiffImageData.Tiles imageData = mock(TiffImageData.Tiles.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

        DataReaderTiled dataReaderTiled = new DataReaderTiled(
                directory, photometricInterpreter, 256, 256, 32, new int[]{32}, 1, 1, 
                TiffTagConstants.SAMPLE_FORMAT_VALUE_TWOS_COMPLEMENT_SIGNED_INTEGER, 1024, 1024, 
                1, TiffPlanarConfiguration.CHUNKY, byteOrder, imageData);

        Rectangle subImage = new Rectangle(0, 0, 512, 512);
        TiffRasterDataInt rasterData = (TiffRasterDataInt) dataReaderTiled.readRasterData(subImage);

        assertEquals(512, rasterData.getWidth());
        assertEquals(512, rasterData.getHeight());
    }

    @Test
    public void testReadRasterDataUnsupportedFormat() {
        TiffDirectory directory = mock(TiffDirectory.class);
        PhotometricInterpreter photometricInterpreter = mock(PhotometricInterpreter.class);
        TiffImageData.Tiles imageData = mock(TiffImageData.Tiles.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

        DataReaderTiled dataReaderTiled = new DataReaderTiled(
                directory, photometricInterpreter, 256, 256, 32, new int[]{32}, 1, 1, 
                999, 1024, 1024, 1, TiffPlanarConfiguration.CHUNKY, byteOrder, imageData);

        Rectangle subImage = new Rectangle(0, 0, 512, 512);

        assertThrows(ImageReadException.class, () -> dataReaderTiled.readRasterData(subImage));
    }
}