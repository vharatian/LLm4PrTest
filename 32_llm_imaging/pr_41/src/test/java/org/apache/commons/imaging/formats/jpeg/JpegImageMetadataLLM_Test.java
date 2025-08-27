package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpegImageMetadataLLM_Test {

    @Test
    public void testGetEXIFThumbnailSize() throws ImageReadException, IOException {
        TiffImageMetadata mockExif = mock(TiffImageMetadata.class);
        JpegImageMetadata metadata = new JpegImageMetadata(null, mockExif);

        byte[] thumbnailData = new byte[]{1, 2, 3};
        when(mockExif.getDirectories()).thenReturn(List.of(mock(TiffImageMetadata.Directory.class)));
        when(mockExif.getDirectories().get(0).getJpegImageData()).thenReturn(new JpegImageData(thumbnailData));

        Dimension size = metadata.getEXIFThumbnailSize();
        assertNotNull(size);
    }

    @Test
    public void testGetEXIFThumbnailData() throws ImageReadException, IOException {
        TiffImageMetadata mockExif = mock(TiffImageMetadata.class);
        JpegImageMetadata metadata = new JpegImageMetadata(null, mockExif);

        byte[] thumbnailData = new byte[]{1, 2, 3};
        when(mockExif.getDirectories()).thenReturn(List.of(mock(TiffImageMetadata.Directory.class)));
        when(mockExif.getDirectories().get(0).getJpegImageData()).thenReturn(new JpegImageData(thumbnailData));

        byte[] data = metadata.getEXIFThumbnailData();
        assertNotNull(data);
        assertArrayEquals(thumbnailData, data);
    }

    @Test
    public void testGetEXIFThumbnail() throws ImageReadException, IOException {
        TiffImageMetadata mockExif = mock(TiffImageMetadata.class);
        JpegImageMetadata metadata = new JpegImageMetadata(null, mockExif);

        BufferedImage mockImage = mock(BufferedImage.class);
        when(mockExif.getDirectories()).thenReturn(List.of(mock(TiffImageMetadata.Directory.class)));
        when(mockExif.getDirectories().get(0).getThumbnail()).thenReturn(mockImage);

        BufferedImage image = metadata.getEXIFThumbnail();
        assertNotNull(image);
        assertEquals(mockImage, image);
    }
}