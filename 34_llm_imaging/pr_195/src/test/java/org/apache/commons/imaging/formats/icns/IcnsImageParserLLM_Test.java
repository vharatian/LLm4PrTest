package org.apache.commons.imaging.formats.icns;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IcnsImageParserLLM_Test {

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        // Mock ByteSource
        ByteSource byteSource = mock(ByteSource.class);
        
        // Mock IcnsContents and IcnsDecoder
        IcnsImageParser.IcnsContents contents = mock(IcnsImageParser.IcnsContents.class);
        IcnsImageParser.IcnsElement element = mock(IcnsImageParser.IcnsElement.class);
        List<IcnsImageParser.IcnsElement> elements = new ArrayList<>();
        elements.add(element);
        when(contents.icnsElements).thenReturn(elements.toArray(new IcnsImageParser.IcnsElement[0]));
        
        // Mock BufferedImage
        BufferedImage image = mock(BufferedImage.class);
        when(image.getHeight()).thenReturn(32);
        when(image.getWidth()).thenReturn(32);
        
        // Mock IcnsDecoder
        List<BufferedImage> images = new ArrayList<>();
        images.add(image);
        IcnsDecoder decoder = mock(IcnsDecoder.class);
        when(decoder.decodeAllImages(any())).thenReturn(images);
        
        // Create instance of IcnsImageParser
        IcnsImageParser parser = new IcnsImageParser() {
            @Override
            protected IcnsContents readImage(ByteSource byteSource) throws ImageReadException, IOException {
                return contents;
            }
        };
        
        // Call getImageInfo and verify results
        IcnsImagingParameters params = new IcnsImagingParameters();
        ImageInfo imageInfo = parser.getImageInfo(byteSource, params);
        
        assertNotNull(imageInfo);
        assertEquals("Icns", imageInfo.getFormatName());
        assertEquals(32, imageInfo.getBitsPerPixel());
        assertEquals(new ArrayList<>(), imageInfo.getComments());
        assertEquals(ImageFormats.ICNS, imageInfo.getFormat());
        assertEquals("ICNS Apple Icon Image", imageInfo.getFormatDetails());
        assertEquals(32, imageInfo.getHeight());
        assertEquals("image/x-icns", imageInfo.getMimeType());
        assertEquals(1, imageInfo.getNumberOfImages());
        assertEquals(32, imageInfo.getWidth());
        assertFalse(imageInfo.isProgressive());
        assertTrue(imageInfo.isTransparent());
        assertFalse(imageInfo.isAnimation());
        assertEquals(ImageInfo.ColorType.RGB, imageInfo.getColorType());
        assertEquals(ImageInfo.CompressionAlgorithm.UNKNOWN, imageInfo.getCompressionAlgorithm());
    }
}