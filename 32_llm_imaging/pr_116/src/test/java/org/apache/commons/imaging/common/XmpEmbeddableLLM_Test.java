package org.apache.commons.imaging.common;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class XmpEmbeddableLLM_Test {

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        // Mocking ByteSource and XmpImagingParameters
        ByteSource byteSource = mock(ByteSource.class);
        XmpImagingParameters params = mock(XmpImagingParameters.class);

        // Creating a mock implementation of XmpEmbeddable
        XmpEmbeddable xmpEmbeddable = mock(XmpEmbeddable.class);
        String expectedXml = "<xmp>sample</xmp>";
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenReturn(expectedXml);

        // Calling the method and asserting the result
        String actualXml = xmpEmbeddable.getXmpXml(byteSource, params);
        assertEquals(expectedXml, actualXml);

        // Verifying the interaction with the mocks
        verify(xmpEmbeddable).getXmpXml(byteSource, params);
    }

    @Test
    public void testGetXmpXmlThrowsImageReadException() throws IOException {
        // Mocking ByteSource and XmpImagingParameters
        ByteSource byteSource = mock(ByteSource.class);
        XmpImagingParameters params = mock(XmpImagingParameters.class);

        // Creating a mock implementation of XmpEmbeddable
        XmpEmbeddable xmpEmbeddable = mock(XmpEmbeddable.class);
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenThrow(new ImageReadException("Error reading image"));

        // Asserting that the method throws ImageReadException
        assertThrows(ImageReadException.class, () -> xmpEmbeddable.getXmpXml(byteSource, params));

        // Verifying the interaction with the mocks
        verify(xmpEmbeddable).getXmpXml(byteSource, params);
    }

    @Test
    public void testGetXmpXmlThrowsIOException() throws ImageReadException {
        // Mocking ByteSource and XmpImagingParameters
        ByteSource byteSource = mock(ByteSource.class);
        XmpImagingParameters params = mock(XmpImagingParameters.class);

        // Creating a mock implementation of XmpEmbeddable
        XmpEmbeddable xmpEmbeddable = mock(XmpEmbeddable.class);
        when(xmpEmbeddable.getXmpXml(byteSource, params)).thenThrow(new IOException("IO error"));

        // Asserting that the method throws IOException
        assertThrows(IOException.class, () -> xmpEmbeddable.getXmpXml(byteSource, params));

        // Verifying the interaction with the mocks
        verify(xmpEmbeddable).getXmpXml(byteSource, params);
    }
}