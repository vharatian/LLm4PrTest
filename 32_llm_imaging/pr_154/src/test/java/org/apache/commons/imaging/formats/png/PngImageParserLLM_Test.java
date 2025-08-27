package org.apache.commons.imaging.formats.png;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.formats.png.chunks.PngChunk;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIccp;
import org.apache.commons.imaging.formats.png.chunks.PngChunkIhdr;
import org.apache.commons.imaging.formats.png.chunks.PngChunkText;
import org.apache.commons.imaging.formats.png.chunks.PngChunkZtxt;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PngImageParserLLM_Test {

    @Test
    public void testGetICCProfileBytes_NoChunks() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        when(byteSource.getInputStream()).thenReturn(mock(InputStream.class));

        byte[] result = parser.getICCProfileBytes(byteSource, null);
        assertNull(result);
    }

    @Test
    public void testGetImageSize_NoChunks() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        when(byteSource.getInputStream()).thenReturn(mock(InputStream.class));

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            parser.getImageSize(byteSource, null);
        });
        assertEquals("Png: No chunks", exception.getMessage());
    }

    @Test
    public void testGetMetadata_NoChunks() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        when(byteSource.getInputStream()).thenReturn(mock(InputStream.class));

        ImageMetadata result = parser.getMetadata(byteSource, null);
        assertNull(result);
    }

    @Test
    public void testGetImageInfo_NoChunks() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        when(byteSource.getInputStream()).thenReturn(mock(InputStream.class));

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            parser.getImageInfo(byteSource, null);
        });
        assertEquals("PNG: no chunks", exception.getMessage());
    }

    @Test
    public void testGetBufferedImage_NoChunks() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        when(byteSource.getInputStream()).thenReturn(mock(InputStream.class));

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            parser.getBufferedImage(byteSource, null);
        });
        assertEquals("PNG: no chunks", exception.getMessage());
    }

    @Test
    public void testGetXmpXml_NoChunks() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        when(byteSource.getInputStream()).thenReturn(mock(InputStream.class));

        String result = parser.getXmpXml(byteSource, null);
        assertNull(result);
    }

    @Test
    public void testGetBufferedImage_IccProfile() throws IOException, ImageReadException {
        PngImageParser parser = new PngImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        InputStream inputStream = mock(InputStream.class);
        when(byteSource.getInputStream()).thenReturn(inputStream);

        PngChunkIccp iccpChunk = mock(PngChunkIccp.class);
        when(iccpChunk.getUncompressedProfile()).thenReturn(new byte[]{});
        List<PngChunk> chunks = List.of(iccpChunk);
        when(parser.readChunks(inputStream, new ChunkType[]{ChunkType.iCCP}, true)).thenReturn(chunks);

        byte[] result = parser.getICCProfileBytes(byteSource, null);
        assertNotNull(result);
    }
}