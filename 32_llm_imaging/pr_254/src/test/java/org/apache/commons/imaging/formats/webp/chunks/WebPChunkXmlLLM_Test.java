package org.apache.commons.imaging.formats.webp.chunks;

import org.apache.commons.imaging.ImagingException;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WebPChunkXmlLLM_Test {

    @Test
    public void testGetXml() throws ImagingException {
        String xmlContent = "<xml>test</xml>";
        byte[] bytes = xmlContent.getBytes(StandardCharsets.UTF_8);
        WebPChunkXml chunk = new WebPChunkXml(1, bytes.length, bytes);
        assertEquals(xmlContent, chunk.getXml());
    }

    @Test
    public void testConstructorWithMismatchedSize() {
        byte[] bytes = "<xml>test</xml>".getBytes(StandardCharsets.UTF_8);
        assertThrows(ImagingException.class, () -> new WebPChunkXml(1, bytes.length + 1, bytes));
    }
}