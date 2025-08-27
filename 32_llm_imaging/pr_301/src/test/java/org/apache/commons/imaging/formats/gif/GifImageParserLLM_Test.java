package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImagingException;
import org.apache.commons.imaging.bytesource.ByteSource;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.common.BinaryFunctions;
import org.apache.commons.imaging.common.XmpImagingParameters;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GifImageParserLLM_Test {

    @Test
    public void testGetImageInfoWithStopReadingBeforeImageData() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(true);

        GifImageParser parser = new GifImageParser();
        GifImageParser spyParser = spy(parser);

        doReturn(new GifImageContents(new GifHeaderInfo(), null, null)).when(spyParser).readFile(byteSource, true);

        ImageInfo imageInfo = spyParser.getImageInfo(byteSource, params);

        assertNotNull(imageInfo);
        verify(spyParser).readFile(byteSource, true);
    }

    @Test
    public void testGetImageInfoWithoutStopReadingBeforeImageData() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(false);

        GifImageParser parser = new GifImageParser();
        GifImageParser spyParser = spy(parser);

        doReturn(new GifImageContents(new GifHeaderInfo(), null, null)).when(spyParser).readFile(byteSource, false);

        ImageInfo imageInfo = spyParser.getImageInfo(byteSource, params);

        assertNotNull(imageInfo);
        verify(spyParser).readFile(byteSource, false);
    }

    @Test
    public void testGetMetadataWithStopReadingBeforeImageData() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(true);

        GifImageParser parser = new GifImageParser();
        GifImageParser spyParser = spy(parser);

        doReturn(new GifImageContents(new GifHeaderInfo(), null, null)).when(spyParser).readFile(byteSource, true);

        ImageMetadata metadata = spyParser.getMetadata(byteSource, params);

        assertNotNull(metadata);
        verify(spyParser).readFile(byteSource, true);
    }

    @Test
    public void testGetMetadataWithoutStopReadingBeforeImageData() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(false);

        GifImageParser parser = new GifImageParser();
        GifImageParser spyParser = spy(parser);

        doReturn(new GifImageContents(new GifHeaderInfo(), null, null)).when(spyParser).readFile(byteSource, false);

        ImageMetadata metadata = spyParser.getMetadata(byteSource, params);

        assertNotNull(metadata);
        verify(spyParser).readFile(byteSource, false);
    }

    @Test
    public void testGetImageSizeWithStopReadingBeforeImageData() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(true);

        GifImageParser parser = new GifImageParser();
        GifImageParser spyParser = spy(parser);

        doReturn(new GifImageContents(new GifHeaderInfo(), null, null)).when(spyParser).readFile(byteSource, true);

        Dimension dimension = spyParser.getImageSize(byteSource, params);

        assertNotNull(dimension);
        verify(spyParser).readFile(byteSource, true);
    }

    @Test
    public void testGetImageSizeWithoutStopReadingBeforeImageData() throws IOException, ImagingException {
        ByteSource byteSource = mock(ByteSource.class);
        GifImagingParameters params = new GifImagingParameters();
        params.setStopReadingBeforeImageData(false);

        GifImageParser parser = new GifImageParser();
        GifImageParser spyParser = spy(parser);

        doReturn(new GifImageContents(new GifHeaderInfo(), null, null)).when(spyParser).readFile(byteSource, false);

        Dimension dimension = spyParser.getImageSize(byteSource, params);

        assertNotNull(dimension);
        verify(spyParser).readFile(byteSource, false);
    }
}