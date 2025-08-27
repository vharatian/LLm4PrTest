package org.apache.commons.imaging.formats.psd;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PsdImageParserLLM_Test {

    @Test
    public void testXmpEmbeddableImplementation() throws ImageReadException, IOException {
        PsdImageParser parser = new PsdImageParser();
        assertNotNull(parser);
        assertEquals(ByteOrder.BIG_ENDIAN, parser.getByteOrder());
    }

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        ByteSource byteSource = ByteSource.file("path/to/test.psd");
        PsdImageParser parser = new PsdImageParser();
        String xmpXml = parser.getXmpXml(byteSource, null);
        assertNotNull(xmpXml);
    }
}