package org.apache.commons.imaging.formats.psd;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PsdImageParserLLM_Test {

    @Test
    public void testGetICCProfileBytes_NullBlocks() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = mock(Map.class);

        PsdImageParser spyParser = spy(parser);
        doReturn(null).when(spyParser).readImageResourceBlocks(any(ByteSource.class), any(int[].class), anyInt());

        byte[] result = spyParser.getICCProfileBytes(byteSource, params);
        assertNull(result);
    }

    @Test
    public void testGetICCProfileBytes_EmptyBlocks() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = mock(Map.class);

        PsdImageParser spyParser = spy(parser);
        doReturn(List.of()).when(spyParser).readImageResourceBlocks(any(ByteSource.class), any(int[].class), anyInt());

        byte[] result = spyParser.getICCProfileBytes(byteSource, params);
        assertNull(result);
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PsdHeaderInfo headerInfo = new PsdHeaderInfo(1, new byte[6], 3, 100, 200, 8, 3);

        PsdImageParser spyParser = spy(parser);
        doReturn(headerInfo).when(spyParser).readHeader(any(ByteSource.class));

        Dimension result = spyParser.getImageSize(byteSource, null);
        assertEquals(new Dimension(200, 100), result);
    }

    @Test
    public void testGetImageInfo() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PsdHeaderInfo headerInfo = new PsdHeaderInfo(1, new byte[6], 3, 100, 200, 8, 3);
        PsdImageContents imageContents = new PsdImageContents(headerInfo, 0, 0, 0, 0);

        PsdImageParser spyParser = spy(parser);
        doReturn(imageContents).when(spyParser).readImageContents(any(ByteSource.class));

        ImageInfo result = spyParser.getImageInfo(byteSource, null);
        assertNotNull(result);
        assertEquals(200, result.getWidth());
        assertEquals(100, result.getHeight());
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PsdHeaderInfo headerInfo = new PsdHeaderInfo(1, new byte[6], 3, 100, 200, 8, 3);
        PsdImageContents imageContents = new PsdImageContents(headerInfo, 0, 0, 0, 0);

        PsdImageParser spyParser = spy(parser);
        doReturn(imageContents).when(spyParser).readImageContents(any(ByteSource.class));
        doReturn(mock(InputStream.class)).when(spyParser).getInputStream(any(ByteSource.class), anyInt());

        BufferedImage result = spyParser.getBufferedImage(byteSource, null);
        assertNotNull(result);
        assertEquals(200, result.getWidth());
        assertEquals(100, result.getHeight());
    }

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        PsdHeaderInfo headerInfo = new PsdHeaderInfo(1, new byte[6], 3, 100, 200, 8, 3);
        PsdImageContents imageContents = new PsdImageContents(headerInfo, 0, 0, 0, 0);
        ImageResourceBlock xmpBlock = new ImageResourceBlock(IMAGE_RESOURCE_ID_XMP, new byte[0], "test".getBytes(StandardCharsets.UTF_8));

        PsdImageParser spyParser = spy(parser);
        doReturn(imageContents).when(spyParser).readImageContents(any(ByteSource.class));
        doReturn(List.of(xmpBlock)).when(spyParser).readImageResourceBlocks(any(ByteSource.class), any(int[].class), anyInt());

        String result = spyParser.getXmpXml(byteSource, null);
        assertEquals("test", result);
    }
}