package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GifImageParserLLM_Test {

    @Test
    public void testFindAllImageDataWithEmptyDescriptors() {
        GifImageParser parser = new GifImageParser();
        GifImageContents imageContents = new GifImageContents(
            new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 1, 1, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0),
            null,
            List.of()
        );

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            parser.findAllImageData(imageContents);
        });

        assertEquals("GIF: Couldn't read Image Descriptor", exception.getMessage());
    }

    @Test
    public void testFindAllImageDataWithMismatchedGraphicControlExtensions() {
        GifImageParser parser = new GifImageParser();
        GifImageContents imageContents = new GifImageContents(
            new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 1, 1, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0),
            null,
            List.of(new ImageDescriptor(0, 0, 0, 1, 1, (byte) 0, false, false, false, (byte) 0, null, null),
                    new GraphicControlExtension(0, 0, 0, false, 0, 0))
        );

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            parser.findAllImageData(imageContents);
        });

        assertEquals("GIF: Invalid amount of Graphic Control Extensions", exception.getMessage());
    }

    @Test
    public void testFindAllImageDataWithEmptyGraphicControlExtensions() throws ImageReadException {
        GifImageParser parser = new GifImageParser();
        GifImageContents imageContents = new GifImageContents(
            new GifHeaderInfo((byte) 'G', (byte) 'I', (byte) 'F', (byte) '8', (byte) '9', (byte) 'a', 1, 1, (byte) 0, (byte) 0, (byte) 0, false, (byte) 0, false, (byte) 0),
            null,
            List.of(new ImageDescriptor(0, 0, 0, 1, 1, (byte) 0, false, false, false, (byte) 0, null, null))
        );

        List<GifImageData> imageData = parser.findAllImageData(imageContents);

        assertEquals(1, imageData.size());
        assertNull(imageData.get(0).gce);
    }

    @Test
    public void testGetXmpXmlWithEmptyResult() throws ImageReadException, IOException {
        GifImageParser parser = new GifImageParser();
        ByteSource byteSource = ByteSource.array(new byte[] { 0x47, 0x49, 0x46, 0x38, 0x39, 0x61, 0x01, 0x00, 0x01, 0x00, 0x80, 0x00, 0x00, 0x00, 0x00, 0x00, 0x21, 0xF9, 0x04, 0x01, 0x00, 0x00, 0x00, 0x00, 0x2C, 0x00, 0x00, 0x00, 0x00, 0x01, 0x00, 0x01, 0x00, 0x00, 0x02, 0x02, 0x44, 0x01, 0x00, 0x3B });

        String xmpXml = parser.getXmpXml(byteSource, null);

        assertNull(xmpXml);
    }
}