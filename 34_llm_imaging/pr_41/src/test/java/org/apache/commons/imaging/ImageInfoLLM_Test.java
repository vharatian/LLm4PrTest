package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ImageInfoLLM_Test {

    @Test
    public void testGetBitsPerPixel() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(24, imageInfo.getBitsPerPixel());
    }

    @Test
    public void testGetComments() {
        ImageInfo imageInfo = createTestImageInfo();
        List<String> comments = imageInfo.getComments();
        assertNotNull(comments);
        assertEquals(2, comments.size());
        assertEquals("Comment 1", comments.get(0));
        assertEquals("Comment 2", comments.get(1));
    }

    @Test
    public void testGetFormat() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(ImageFormat.IMAGE_FORMAT_PNG, imageInfo.getFormat());
    }

    @Test
    public void testGetFormatName() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals("PNG", imageInfo.getFormatName());
    }

    @Test
    public void testGetHeight() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(800, imageInfo.getHeight());
    }

    @Test
    public void testGetMimeType() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals("image/png", imageInfo.getMimeType());
    }

    @Test
    public void testGetNumberOfImages() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(1, imageInfo.getNumberOfImages());
    }

    @Test
    public void testGetPhysicalHeightDpi() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(300, imageInfo.getPhysicalHeightDpi());
    }

    @Test
    public void testGetPhysicalHeightInch() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(2.67f, imageInfo.getPhysicalHeightInch(), 0.01);
    }

    @Test
    public void testGetPhysicalWidthDpi() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(300, imageInfo.getPhysicalWidthDpi());
    }

    @Test
    public void testGetPhysicalWidthInch() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(3.33f, imageInfo.getPhysicalWidthInch(), 0.01);
    }

    @Test
    public void testGetWidth() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(1000, imageInfo.getWidth());
    }

    @Test
    public void testIsProgressive() {
        ImageInfo imageInfo = createTestImageInfo();
        assertTrue(imageInfo.isProgressive());
    }

    @Test
    public void testGetColorType() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(ImageInfo.ColorType.RGB, imageInfo.getColorType());
    }

    @Test
    public void testGetFormatDetails() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals("PNG format", imageInfo.getFormatDetails());
    }

    @Test
    public void testIsTransparent() {
        ImageInfo imageInfo = createTestImageInfo();
        assertTrue(imageInfo.isTransparent());
    }

    @Test
    public void testUsesPalette() {
        ImageInfo imageInfo = createTestImageInfo();
        assertFalse(imageInfo.usesPalette());
    }

    @Test
    public void testGetCompressionAlgorithm() {
        ImageInfo imageInfo = createTestImageInfo();
        assertEquals(ImageInfo.CompressionAlgorithm.PNG_FILTER, imageInfo.getCompressionAlgorithm());
    }

    private ImageInfo createTestImageInfo() {
        List<String> comments = new ArrayList<>();
        comments.add("Comment 1");
        comments.add("Comment 2");

        return new ImageInfo(
                "PNG format",
                24,
                comments,
                ImageFormat.IMAGE_FORMAT_PNG,
                "PNG",
                800,
                "image/png",
                1,
                300,
                2.67f,
                300,
                3.33f,
                1000,
                true,
                true,
                false,
                ImageInfo.ColorType.RGB,
                ImageInfo.CompressionAlgorithm.PNG_FILTER
        );
    }
}