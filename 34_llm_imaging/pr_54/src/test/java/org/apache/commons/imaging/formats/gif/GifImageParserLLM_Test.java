package org.apache.commons.imaging.formats.gif;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GifImageParserLLM_Test {

    @Test
    public void testGifImageParserImplementsXmpEmbeddable() {
        GifImageParser parser = new GifImageParser();
        assertTrue(parser instanceof XmpEmbeddable, "GifImageParser should implement XmpEmbeddable");
    }

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        ByteSource byteSource = ByteSource.file("src/test/resources/test.gif");
        GifImageParser parser = new GifImageParser();
        String xmpXml = parser.getXmpXml(byteSource, null);
        assertNotNull(xmpXml, "XMP XML should not be null");
    }
}