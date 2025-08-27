package org.apache.commons.imaging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceFile;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.apache.commons.imaging.formats.jpeg.JpegImageParser;
import org.junit.jupiter.api.Test;

public class ImagingLLM_Test {

    @Test
    public void testGetXmpXmlWithXmpEmbeddableParser() throws ImageReadException, IOException {
        // Arrange
        File jpegFile = new File("src/test/resources/test-images/jpeg/test.jpg");
        ByteSource byteSource = new ByteSourceFile(jpegFile);
        Map<String, Object> params = new HashMap<>();
        JpegImageParser jpegImageParser = new JpegImageParser();

        // Act
        String xmpXml = Imaging.getXmpXml(byteSource, params);

        // Assert
        assertEquals(jpegImageParser.getXmpXml(byteSource, params), xmpXml);
    }

    @Test
    public void testGetXmpXmlWithNonXmpEmbeddableParser() throws ImageReadException, IOException {
        // Arrange
        File pngFile = new File("src/test/resources/test-images/png/test.png");
        ByteSource byteSource = new ByteSourceFile(pngFile);
        Map<String, Object> params = new HashMap<>();

        // Act
        String xmpXml = Imaging.getXmpXml(byteSource, params);

        // Assert
        assertNull(xmpXml);
    }
}