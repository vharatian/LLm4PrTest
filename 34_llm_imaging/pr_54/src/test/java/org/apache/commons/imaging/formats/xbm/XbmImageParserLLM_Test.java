package org.apache.commons.imaging.formats.xbm;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class XbmImageParserLLM_Test {

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        // Arrange
        XbmImageParser parser = new XbmImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();

        // Act
        String xmpXml = parser.getXmpXml(byteSource, params);

        // Assert
        assertNull(xmpXml);
    }
}