package org.apache.commons.imaging.common;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class XmpEmbeddableLLM_Test {

    private XmpEmbeddable xmpEmbeddable;
    private ByteSource byteSource;
    private Map<String, Object> params;

    @BeforeEach
    public void setUp() {
        xmpEmbeddable = mock(XmpEmbeddable.class);
        byteSource = mock(ByteSource.class);
        params = new HashMap<>();
    }

    @Test
    public void testGetXmpXmlReturnsValidString() throws ImageReadException, IOException {
        String expectedXmpXml = "<xmpmeta>...</xmpmeta>";
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenReturn(expectedXmpXml);

        String actualXmpXml = xmpEmbeddable.getXmpXml(byteSource, params);
        assertEquals(expectedXmpXml, actualXmpXml);
    }

    @Test
    public void testGetXmpXmlReturnsNullWhenNoMetadata() throws ImageReadException, IOException {
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenReturn(null);

        String actualXmpXml = xmpEmbeddable.getXmpXml(byteSource, params);
        assertNull(actualXmpXml);
    }

    @Test
    public void testGetXmpXmlThrowsImageReadException() throws ImageReadException, IOException {
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenThrow(new ImageReadException("Invalid format"));

        assertThrows(ImageReadException.class, () -> {
            xmpEmbeddable.getXmpXml(byteSource, params);
        });
    }

    @Test
    public void testGetXmpXmlThrowsIOException() throws ImageReadException, IOException {
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenThrow(new IOException("Read error"));

        assertThrows(IOException.class, () -> {
            xmpEmbeddable.getXmpXml(byteSource, params);
        });
    }
}