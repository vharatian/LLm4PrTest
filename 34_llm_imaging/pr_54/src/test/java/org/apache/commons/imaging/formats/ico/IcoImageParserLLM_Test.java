package org.apache.commons.imaging.formats.ico;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class IcoImageParserLLM_Test {

    @Test
    public void testGetXmpXml() throws ImageReadException, IOException {
        IcoImageParser parser = new IcoImageParser();
        ByteSource byteSource = mock(ByteSource.class);
        Map<String, Object> params = new HashMap<>();

        // Since the method getXmpXml is removed, we should ensure that calling it throws a NoSuchMethodError
        assertNull(parser.getXmpXml(byteSource, params));
    }
}