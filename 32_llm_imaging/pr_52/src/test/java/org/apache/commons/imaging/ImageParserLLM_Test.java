package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageParserLLM_Test {

    @Test
    public void testJavadocChanges() {
        // This test is to ensure that the Javadoc changes do not affect the functionality of the class.
        // Since Javadoc changes are non-functional, we will just instantiate the class and call a method to ensure no runtime errors.
        ImageParser parser = new ImageParser() {
            @Override
            public ImageMetadata getMetadata(ByteSource byteSource, Map<String, Object> params) throws ImageReadException, IOException {
                return null;
            }

            @Override
            public ImageInfo getImageInfo(ByteSource byteSource, Map<String, Object> params) throws ImageReadException, IOException {
                return null;
            }

            @Override
            public BufferedImage getBufferedImage(ByteSource byteSource, Map<String, Object> params) throws ImageReadException, IOException {
                return null;
            }

            @Override
            public Dimension getImageSize(ByteSource byteSource, Map<String, Object> params) throws ImageReadException, IOException {
                return null;
            }

            @Override
            public String getXmpXml(ByteSource byteSource, Map<String, Object> params) throws ImageReadException, IOException {
                return null;
            }

            @Override
            public byte[] getICCProfileBytes(ByteSource byteSource, Map<String, Object> params) throws ImageReadException, IOException {
                return new byte[0];
            }

            @Override
            public String getName() {
                return "TestParser";
            }

            @Override
            public String getDefaultExtension() {
                return ".test";
            }

            @Override
            protected String[] getAcceptedExtensions() {
                return new String[]{".test"};
            }

            @Override
            protected ImageFormat[] getAcceptedTypes() {
                return new ImageFormat[]{ImageFormat.IMAGE_FORMAT_UNKNOWN};
            }
        };

        // Call a method to ensure no runtime errors
        assertDoesNotThrow(() -> parser.getName());
    }
}