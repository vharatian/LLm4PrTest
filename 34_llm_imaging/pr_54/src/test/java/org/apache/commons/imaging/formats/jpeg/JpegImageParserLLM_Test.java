package org.apache.commons.imaging.formats.jpeg;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.XmpEmbeddable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JpegImageParserLLM_Test {

    @Test
    public void testJpegImageParserImplementsXmpEmbeddable() {
        JpegImageParser parser = new JpegImageParser();
        assertTrue(parser instanceof XmpEmbeddable, "JpegImageParser should implement XmpEmbeddable interface");
    }

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = ByteSource.file("path/to/jpeg/with/xmp.jpg");
        Map<String, Object> params = new HashMap<>();
        
        String xmpXml = parser.getXmpXml(byteSource, params);
        
        assertNotNull(xmpXml, "XMP XML should not be null for JPEG with XMP data");
        assertTrue(xmpXml.contains("<x:xmpmeta"), "XMP XML should contain XMP metadata");
    }
}