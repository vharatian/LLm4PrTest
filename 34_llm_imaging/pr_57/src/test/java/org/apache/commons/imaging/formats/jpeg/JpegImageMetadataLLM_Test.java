package org.apache.commons.imaging.formats.jpeg;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.tiff.JpegImageData;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JpegImageMetadataLLM_Test {

    private JpegImageMetadata jpegImageMetadata;
    private TiffImageMetadata mockExif;

    @BeforeEach
    public void setUp() {
        mockExif = mock(TiffImageMetadata.class);
        jpegImageMetadata = new JpegImageMetadata(null, mockExif);
    }

    @Test
    public void testGetEXIFThumbnailData_withJpegImageData() throws ImageReadException, IOException {
        byte[] expectedData = new byte[]{1, 2, 3, 4};

        JpegImageData mockJpegImageData = mock(JpegImageData.class);
        when(mockJpegImageData.getData()).thenReturn(expectedData);

        Directory mockDirectory = mock(Directory.class);
        when(mockDirectory.getJpegImageData()).thenReturn(mockJpegImageData);

        when(mockExif.getDirectories()).thenReturn(Collections.singletonList(mockDirectory));

        byte[] actualData = jpegImageMetadata.getEXIFThumbnailData();

        assertArrayEquals(expectedData, actualData);
    }

    @Test
    public void testGetEXIFThumbnailData_withoutJpegImageData() throws ImageReadException, IOException {
        Directory mockDirectory = mock(Directory.class);
        when(mockDirectory.getJpegImageData()).thenReturn(null);

        when(mockExif.getDirectories()).thenReturn(Collections.singletonList(mockDirectory));

        byte[] actualData = jpegImageMetadata.getEXIFThumbnailData();

        assertNull(actualData);
    }
}