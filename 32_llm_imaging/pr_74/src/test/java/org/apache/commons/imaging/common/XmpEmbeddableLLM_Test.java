package org.apache.commons.imaging.common;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class XmpEmbeddableLLM_Test {

    @Test
    public void testGetXmpXmlThrowsImageReadException() throws IOException {
        XmpEmbeddable xmpEmbeddable = mock(XmpEmbeddable.class);
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();

        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenThrow(new ImageReadException("Test Exception"));

        assertThrows(ImageReadException.class, () -> {
            xmpEmbeddable.getXmpXml(byteSource, params);
        });
    }

    @Test
    public void testGetXmpXmlThrowsIOException() throws ImageReadException {
        XmpEmbeddable xmpEmbeddable = mock(XmpEmbeddable.class);
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();

        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenThrow(new IOException("Test Exception"));

        assertThrows(IOException.class, () -> {
            xmpEmbeddable.getXmpXml(byteSource, params);
        });
    }

    @Test
    public void testGetXmpXmlReturnsValidXml() throws ImageReadException, IOException {
        XmpEmbeddable xmpEmbeddable = mock(XmpEmbeddable.class);
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();
        String expectedXml = "<xmpmeta>Test XML</xmpmeta>";

        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenReturn(expectedXml);

        String actualXml = xmpEmbeddable.getXmpXml(byteSource, params);
        assertEquals(expectedXml, actualXml);
    }
}