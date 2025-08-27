package org.apache.commons.imaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Dimension;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.icc.IccProfileInfo;
import org.apache.commons.imaging.icc.IccProfileParser;
import org.apache.commons.imaging.internal.Util;
import org.junit.jupiter.api.Test;

public class ImagingLLM_Test extends ImagingTest {

    @Test
    public void testGuessFormatWithNullByteSource() throws IOException {
        assertEquals(ImageFormats.UNKNOWN, Imaging.guessFormat((ByteSource) null));
    }

    @Test
    public void testGuessFormatWithInvalidMagicNumbers() throws IOException {
        byte[] invalidMagicNumbers = new byte[] { 0x00, 0x00 };
        assertEquals(ImageFormats.UNKNOWN, Imaging.guessFormat(invalidMagicNumbers));
    }

    @Test
    public void testGuessFormatWithValidMagicNumbers() throws IOException {
        byte[] jpegMagicNumbers = new byte[] { (byte) 0xff, (byte) 0xd8 };
        assertEquals(ImageFormats.JPEG, Imaging.guessFormat(jpegMagicNumbers));
    }

    @Test
    public void testGuessFormatWithInvalidFile() throws IOException {
        File invalidFile = new File("invalid.file");
        assertEquals(ImageFormats.UNKNOWN, Imaging.guessFormat(invalidFile));
    }

    @Test
    public void testGetICCProfileWithNullByteSource() throws IOException, ImageReadException {
        assertNull(Imaging.getICCProfile((ByteSource) null));
    }

    @Test
    public void testGetICCProfileWithValidBytes() throws IOException, ImageReadException {
        byte[] validIccProfileBytes = new byte[] { /* valid ICC profile bytes */ };
        ICC_Profile profile = Imaging.getICCProfile(validIccProfileBytes);
        assertNotNull(profile);
    }

    @Test
    public void testGetImageInfoWithNullByteSource() throws IOException, ImageReadException {
        assertThrows(IllegalArgumentException.class, () -> Imaging.getImageInfo((ByteSource) null));
    }

    @Test
    public void testGetImageInfoWithValidBytes() throws IOException, ImageReadException {
        byte[] validImageBytes = new byte[] { /* valid image bytes */ };
        ImageInfo imageInfo = Imaging.getImageInfo(validImageBytes);
        assertNotNull(imageInfo);
    }

    @Test
    public void testGetImageSizeWithNullByteSource() throws IOException, ImageReadException {
        assertThrows(IllegalArgumentException.class, () -> Imaging.getImageSize((ByteSource) null));
    }

    @Test
    public void testGetImageSizeWithValidBytes() throws IOException, ImageReadException {
        byte[] validImageBytes = new byte[] { /* valid image bytes */ };
        Dimension dimension = Imaging.getImageSize(validImageBytes);
        assertNotNull(dimension);
    }

    @Test
    public void testGetXmpXmlWithNullByteSource() throws IOException, ImageReadException {
        assertNull(Imaging.getXmpXml((ByteSource) null));
    }

    @Test
    public void testGetXmpXmlWithValidBytes() throws IOException, ImageReadException {
        byte[] validXmpBytes = new byte[] { /* valid XMP bytes */ };
        String xmpXml = Imaging.getXmpXml(validXmpBytes);
        assertNotNull(xmpXml);
    }

    @Test
    public void testGetMetadataWithNullByteSource() throws IOException, ImageReadException {
        assertNull(Imaging.getMetadata((ByteSource) null));
    }

    @Test
    public void testGetMetadataWithValidBytes() throws IOException, ImageReadException {
        byte[] validMetadataBytes = new byte[] { /* valid metadata bytes */ };
        ImageMetadata metadata = Imaging.getMetadata(validMetadataBytes);
        assertNotNull(metadata);
    }

    @Test
    public void testDumpImageFileWithNullByteSource() throws IOException, ImageReadException {
        assertThrows(IllegalArgumentException.class, () -> Imaging.dumpImageFile((ByteSource) null));
    }

    @Test
    public void testDumpImageFileWithValidBytes() throws IOException, ImageReadException {
        byte[] validImageBytes = new byte[] { /* valid image bytes */ };
        String dump = Imaging.dumpImageFile(validImageBytes);
        assertNotNull(dump);
    }

    @Test
    public void testGetFormatComplianceWithNullByteSource() throws IOException, ImageReadException {
        assertThrows(IllegalArgumentException.class, () -> Imaging.getFormatCompliance((ByteSource) null));
    }

    @Test
    public void testGetFormatComplianceWithValidBytes() throws IOException, ImageReadException {
        byte[] validImageBytes = new byte[] { /* valid image bytes */ };
        FormatCompliance compliance = Imaging.getFormatCompliance(validImageBytes);
        assertNotNull(compliance);
    }

    @Test
    public void testGetAllBufferedImagesWithNullByteSource() throws IOException, ImageReadException {
        assertThrows(IllegalArgumentException.class, () -> Imaging.getAllBufferedImages((ByteSource) null));
    }

    @Test
    public void testGetAllBufferedImagesWithValidBytes() throws IOException, ImageReadException {
        byte[] validImageBytes = new byte[] { /* valid image bytes */ };
        List<BufferedImage> images = Imaging.getAllBufferedImages(validImageBytes);
        assertNotNull(images);
        assertFalse(images.isEmpty());
    }

    @Test
    public void testGetBufferedImageWithNullByteSource() throws IOException, ImageReadException {
        assertThrows(IllegalArgumentException.class, () -> Imaging.getBufferedImage((ByteSource) null));
    }

    @Test
    public void testGetBufferedImageWithValidBytes() throws IOException, ImageReadException {
        byte[] validImageBytes = new byte[] { /* valid image bytes */ };
        BufferedImage image = Imaging.getBufferedImage(validImageBytes);
        assertNotNull(image);
    }

    @Test
    public void testWriteImageWithNullParameters() throws IOException, ImageWriteException {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        assertThrows(NullPointerException.class, () -> Imaging.writeImage(image, (OutputStream) null, null));
    }

    @Test
    public void testWriteImageToBytesWithValidParameters() throws IOException, ImageWriteException {
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        byte[] bytes = Imaging.writeImageToBytes(image, ImageFormats.PNG);
        assertNotNull(bytes);
    }
}