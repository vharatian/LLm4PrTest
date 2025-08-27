package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GifImageParserLLM_Test {

    @Test
    public void testReadBlocksWithNonEmptyLabel() throws IOException {
        InputStream is = mock(InputStream.class);
        when(is.read()).thenReturn(0x21, 0xff, 0x0B, -1); // EXTENSION_CODE, APPLICATION_EXTENSION_LABEL, block size, end of stream
        when(is.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            buffer[0] = 'N';
            buffer[1] = 'E';
            buffer[2] = 'T';
            buffer[3] = 'S';
            buffer[4] = 'C';
            buffer[5] = 'A';
            buffer[6] = 'P';
            buffer[7] = 'E';
            buffer[8] = '2';
            buffer[9] = '.';
            buffer[10] = '0';
            return 11;
        });

        GifImageParser parser = new GifImageParser();
        GifHeaderInfo ghi = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 1, 1, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);
        FormatCompliance formatCompliance = FormatCompliance.getDefault();

        List<GifBlock> blocks = parser.readBlocks(ghi, is, false, formatCompliance);
        assertEquals(1, blocks.size());
        assertTrue(blocks.get(0) instanceof GenericGifBlock);
    }

    @Test
    public void testGetImageSizeWithoutNullCheck() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImageParser parser = new GifImageParser();
        GifImageContents contents = mock(GifImageContents.class);
        GifHeaderInfo headerInfo = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 100, 200, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);
        when(contents.gifHeaderInfo).thenReturn(headerInfo);
        when(parser.readFile(byteSource, false)).thenReturn(contents);

        Dimension dimension = parser.getImageSize(byteSource, null);
        assertEquals(100, dimension.width);
        assertEquals(200, dimension.height);
    }

    @Test
    public void testGetMetadataWithoutNullCheck() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImageParser parser = new GifImageParser();
        GifImageContents contents = mock(GifImageContents.class);
        GifHeaderInfo headerInfo = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 100, 200, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);
        when(contents.gifHeaderInfo).thenReturn(headerInfo);
        when(parser.readFile(byteSource, false)).thenReturn(contents);

        ImageMetadata metadata = parser.getMetadata(byteSource, null);
        assertNotNull(metadata);
    }

    @Test
    public void testGetImageInfoWithoutNullCheck() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImageParser parser = new GifImageParser();
        GifImageContents contents = mock(GifImageContents.class);
        GifHeaderInfo headerInfo = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 100, 200, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);
        when(contents.gifHeaderInfo).thenReturn(headerInfo);
        when(parser.readFile(byteSource, false)).thenReturn(contents);

        ImageInfo imageInfo = parser.getImageInfo(byteSource, null);
        assertNotNull(imageInfo);
    }

    @Test
    public void testGetAllBufferedImagesWithoutNullCheck() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImageParser parser = new GifImageParser();
        GifImageContents contents = mock(GifImageContents.class);
        GifHeaderInfo headerInfo = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 100, 200, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);
        when(contents.gifHeaderInfo).thenReturn(headerInfo);
        when(parser.readFile(byteSource, false)).thenReturn(contents);

        List<BufferedImage> images = parser.getAllBufferedImages(byteSource);
        assertNotNull(images);
    }

    @Test
    public void testGetBufferedImageWithoutNullCheck() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImageParser parser = new GifImageParser();
        GifImageContents contents = mock(GifImageContents.class);
        GifHeaderInfo headerInfo = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 100, 200, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);
        when(contents.gifHeaderInfo).thenReturn(headerInfo);
        when(parser.readFile(byteSource, false)).thenReturn(contents);

        BufferedImage image = parser.getBufferedImage(byteSource, null);
        assertNotNull(image);
    }

    @Test
    public void testGetXmpXmlWithNullFormatCompliance() throws ImageReadException, IOException {
        ByteSource byteSource = mock(ByteSource.class);
        InputStream is = mock(InputStream.class);
        when(byteSource.getInputStream()).thenReturn(is);
        when(is.read()).thenReturn(0x21, 0xff, 0x0B, -1); // EXTENSION_CODE, APPLICATION_EXTENSION_LABEL, block size, end of stream
        when(is.read(any(byte[].class), anyInt(), anyInt())).thenAnswer(invocation -> {
            byte[] buffer = invocation.getArgument(0);
            buffer[0] = 'X';
            buffer[1] = 'M';
            buffer[2] = 'P';
            buffer[3] = ' ';
            buffer[4] = 'D';
            buffer[5] = 'a';
            buffer[6] = 't';
            buffer[7] = 'a';
            return 8;
        });

        GifImageParser parser = new GifImageParser();
        GifHeaderInfo ghi = new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 1, 1, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0);

        String xmpXml = parser.getXmpXml(byteSource, null);
        assertNotNull(xmpXml);
        assertEquals("XMP Data", xmpXml);
    }
}