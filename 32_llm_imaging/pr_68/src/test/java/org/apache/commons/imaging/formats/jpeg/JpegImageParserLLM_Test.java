package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.jpeg.iptc.PhotoshopApp13Data;
import org.apache.commons.imaging.formats.jpeg.segments.App13Segment;
import org.apache.commons.imaging.formats.jpeg.segments.Segment;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JpegImageParserLLM_Test {

    @Test
    public void testGetPhotoshopMetadata_singleSegment() throws ImageReadException, IOException {
        // Arrange
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = mock(Map.class);

        App13Segment segment = mock(App13Segment.class);
        PhotoshopApp13Data data = mock(PhotoshopApp13Data.class);
        when(segment.parsePhotoshopSegment(params)).thenReturn(data);

        List<Segment> segments = new ArrayList<>();
        segments.add(segment);

        JpegImageParser spyParser = spy(parser);
        doReturn(segments).when(spyParser).readSegments(byteSource, new int[]{JpegConstants.JPEG_APP13_MARKER}, false);

        // Act
        JpegPhotoshopMetadata metadata = spyParser.getPhotoshopMetadata(byteSource, params);

        // Assert
        assertNotNull(metadata);
        assertEquals(data, metadata.photoshopApp13Data);
    }

    @Test
    public void testGetPhotoshopMetadata_multipleSegments() throws ImageReadException, IOException {
        // Arrange
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = mock(Map.class);

        App13Segment segment1 = mock(App13Segment.class);
        App13Segment segment2 = mock(App13Segment.class);
        PhotoshopApp13Data data1 = mock(PhotoshopApp13Data.class);
        PhotoshopApp13Data data2 = mock(PhotoshopApp13Data.class);
        when(segment1.parsePhotoshopSegment(params)).thenReturn(data1);
        when(segment2.parsePhotoshopSegment(params)).thenReturn(data2);

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        JpegImageParser spyParser = spy(parser);
        doReturn(segments).when(spyParser).readSegments(byteSource, new int[]{JpegConstants.JPEG_APP13_MARKER}, false);

        // Act & Assert
        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            spyParser.getPhotoshopMetadata(byteSource, params);
        });

        assertEquals("Jpeg contains more than one Photoshop App13 segment.", exception.getMessage());
    }

    @Test
    public void testGetPhotoshopMetadata_noSegments() throws ImageReadException, IOException {
        // Arrange
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = mock(Map.class);

        List<Segment> segments = new ArrayList<>();

        JpegImageParser spyParser = spy(parser);
        doReturn(segments).when(spyParser).readSegments(byteSource, new int[]{JpegConstants.JPEG_APP13_MARKER}, false);

        // Act
        JpegPhotoshopMetadata metadata = spyParser.getPhotoshopMetadata(byteSource, params);

        // Assert
        assertNull(metadata);
    }
}