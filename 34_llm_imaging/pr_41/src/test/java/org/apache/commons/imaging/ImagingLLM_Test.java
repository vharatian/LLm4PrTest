package org.apache.commons.imaging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.junit.Test;

public class ImagingLLM_Test extends ImagingTest {

    @Test
    public void testGuessFormatByteArray() throws IOException, ImageReadException {
        byte[] jpegBytes = new byte[] { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0, 0x00, 0x10, 0x4a, 0x46, 0x49, 0x46, 0x00, 0x01 };
        ImageFormat format = Imaging.guessFormat(jpegBytes);
        assertEquals(ImageFormats.JPEG, format);
    }

    @Test
    public void testGuessFormatFile() throws IOException, ImageReadException {
        File jpegFile = getTestImageByName("test.jpg");
        ImageFormat format = Imaging.guessFormat(jpegFile);
        assertEquals(ImageFormats.JPEG, format);
    }

    @Test
    public void testGetICCProfileByteArray() throws IOException, ImageReadException {
        byte[] jpegBytes = new byte[] { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0, 0x00, 0x10, 0x4a, 0x46, 0x49, 0x46, 0x00, 0x01 };
        ICC_Profile profile = Imaging.getICCProfile(jpegBytes);
        assertNotNull(profile);
    }

    @Test
    public void testGetICCProfileFile() throws IOException, ImageReadException {
        File jpegFile = getTestImageByName("test.jpg");
        ICC_Profile profile = Imaging.getICCProfile(jpegFile);
        assertNotNull(profile);
    }

    @Test
    public void testGetImageInfoByteArray() throws IOException, ImageReadException {
        byte[] jpegBytes = new byte[] { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0, 0x00, 0x10, 0x4a, 0x46, 0x49, 0x46, 0x00, 0x01 };
        ImageInfo info = Imaging.getImageInfo(jpegBytes);
        assertNotNull(info);
    }

    @Test
    public void testGetImageInfoFile() throws IOException, ImageReadException {
        File jpegFile = getTestImageByName("test.jpg");
        ImageInfo info = Imaging.getImageInfo(jpegFile);
        assertNotNull(info);
    }

    @Test
    public void testGetImageSizeByteArray() throws IOException, ImageReadException {
        byte[] jpegBytes = new byte[] { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0, 0x00, 0x10, 0x4a, 0x46, 0x49, 0x46, 0x00, 0x01 };
        Dimension size = Imaging.getImageSize(jpegBytes);
        assertNotNull(size);
    }

    @Test
    public void testGetImageSizeFile() throws IOException, ImageReadException {
        File jpegFile = getTestImageByName("test.jpg");
        Dimension size = Imaging.getImageSize(jpegFile);
        assertNotNull(size);
    }

    @Test
    public void testGetXmpXmlByteArray() throws IOException, ImageReadException {
        byte[] jpegBytes = new byte[] { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0, 0x00, 0x10, 0x4a, 0x46, 0x49, 0x46, 0x00, 0x01 };
        String xmpXml = Imaging.getXmpXml(jpegBytes);
        assertNotNull(xmpXml);
    }

    @Test
    public void testGetXmpXmlFile() throws IOException, ImageReadException {
        File jpegFile = getTestImageByName("test.jpg");
        String xmpXml = Imaging.getXmpXml(jpegFile);
        assertNotNull(xmpXml);
    }

    @Test
    public void testGetMetadataByteArray() throws IOException, ImageReadException {
        byte[] jpegBytes = new byte[] { (byte) 0xff, (byte) 0xd8, (byte) 0xff, (byte) 0xe0, 0x00, 0x10, 0x4a, 0x46, 0x49, 0x46, 0x00, 0x01 };
        ImageMetadata metadata = Imaging.getMetadata(jpegBytes);
        assertNotNull(metadata);
    }

    @Test
    public void testGetMetadataFile() throws IOException, ImageReadException {
        File jpegFile = getTestImageByName("test.jpg");
        ImageMetadata metadata = Imaging.getMetadata(jpegFile);
        assertNotNull(metadata);
    }

    @Test
    public void testGuessFormatByteArrayThrowsException() {
        byte[] invalidBytes = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        assertThrows(ImageReadException.class, () -> {
            Imaging.guessFormat(invalidBytes);
        });
    }

    @Test
    public void testGuessFormatFileThrowsException() {
        File invalidFile = new File("invalid.file");
        assertThrows(ImageReadException.class, () -> {
            Imaging.guessFormat(invalidFile);
        });
    }

    @Test
    public void testGetICCProfileByteArrayThrowsException() {
        byte[] invalidBytes = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        assertThrows(ImageReadException.class, () -> {
            Imaging.getICCProfile(invalidBytes);
        });
    }

    @Test
    public void testGetICCProfileFileThrowsException() {
        File invalidFile = new File("invalid.file");
        assertThrows(ImageReadException.class, () -> {
            Imaging.getICCProfile(invalidFile);
        });
    }

    @Test
    public void testGetImageInfoByteArrayThrowsException() {
        byte[] invalidBytes = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        assertThrows(ImageReadException.class, () -> {
            Imaging.getImageInfo(invalidBytes);
        });
    }

    @Test
    public void testGetImageInfoFileThrowsException() {
        File invalidFile = new File("invalid.file");
        assertThrows(ImageReadException.class, () -> {
            Imaging.getImageInfo(invalidFile);
        });
    }

    @Test
    public void testGetImageSizeByteArrayThrowsException() {
        byte[] invalidBytes = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        assertThrows(ImageReadException.class, () -> {
            Imaging.getImageSize(invalidBytes);
        });
    }

    @Test
    public void testGetImageSizeFileThrowsException() {
        File invalidFile = new File("invalid.file");
        assertThrows(ImageReadException.class, () -> {
            Imaging.getImageSize(invalidFile);
        });
    }

    @Test
    public void testGetXmpXmlByteArrayThrowsException() {
        byte[] invalidBytes = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        assertThrows(ImageReadException.class, () -> {
            Imaging.getXmpXml(invalidBytes);
        });
    }

    @Test
    public void testGetXmpXmlFileThrowsException() {
        File invalidFile = new File("invalid.file");
        assertThrows(ImageReadException.class, () -> {
            Imaging.getXmpXml(invalidFile);
        });
    }

    @Test
    public void testGetMetadataByteArrayThrowsException() {
        byte[] invalidBytes = new byte[] { 0x00, 0x01, 0x02, 0x03 };
        assertThrows(ImageReadException.class, () -> {
            Imaging.getMetadata(invalidBytes);
        });
    }

    @Test
    public void testGetMetadataFileThrowsException() {
        File invalidFile = new File("invalid.file");
        assertThrows(ImageReadException.class, () -> {
            Imaging.getMetadata(invalidFile);
        });
    }
}