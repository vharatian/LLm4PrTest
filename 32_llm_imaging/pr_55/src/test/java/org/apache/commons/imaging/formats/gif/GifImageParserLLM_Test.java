package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.junit.jupiter.api.Test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GifImageParserLLM_Test {

    @Test
    public void testFindAllBlocks() throws ImageReadException, IOException {
        byte[] gifData = {/* some valid GIF byte data */};
        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();
        GifImageContents contents = parser.readFile(byteSource, false);

        List<ImageDescriptor> imageDescriptors = parser.findAllBlocks(contents.blocks, GifImageParser.IMAGE_SEPARATOR);
        assertNotNull(imageDescriptors);
        assertFalse(imageDescriptors.isEmpty());
    }

    @Test
    public void testGetImageSize() throws ImageReadException, IOException {
        byte[] gifData = {/* some valid GIF byte data */};
        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();
        Dimension dimension = parser.getImageSize(byteSource, null);

        assertNotNull(dimension);
        assertEquals(640, dimension.width); // Assuming the width of the GIF is 640
        assertEquals(480, dimension.height); // Assuming the height of the GIF is 480
    }

    @Test
    public void testGetMetadata() throws ImageReadException, IOException {
        byte[] gifData = {/* some valid GIF byte data */};
        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();
        GifImageMetadata metadata = (GifImageMetadata) parser.getMetadata(byteSource, null);

        assertNotNull(metadata);
        assertEquals(640, metadata.getWidth()); // Assuming the width of the GIF is 640
        assertEquals(480, metadata.getHeight()); // Assuming the height of the GIF is 480
        assertFalse(metadata.getItems().isEmpty());
    }

    @Test
    public void testGetAllBufferedImages() throws ImageReadException, IOException {
        byte[] gifData = {/* some valid GIF byte data */};
        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();
        List<BufferedImage> images = parser.getAllBufferedImages(byteSource);

        assertNotNull(images);
        assertFalse(images.isEmpty());
        assertEquals(640, images.get(0).getWidth()); // Assuming the width of the first image is 640
        assertEquals(480, images.get(0).getHeight()); // Assuming the height of the first image is 480
    }

    @Test
    public void testGetBufferedImage() throws ImageReadException, IOException {
        byte[] gifData = {/* some valid GIF byte data */};
        ByteSource byteSource = new ByteSourceArray(gifData);
        GifImageParser parser = new GifImageParser();
        BufferedImage image = parser.getBufferedImage(byteSource, null);

        assertNotNull(image);
        assertEquals(640, image.getWidth()); // Assuming the width of the image is 640
        assertEquals(480, image.getHeight()); // Assuming the height of the image is 480
    }
}