package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TiffImageParserLLM_Test {

    @Test
    public void testCollectRawImageData() throws ImageReadException, IOException {
        TiffImageParser parser = new TiffImageParser();
        ByteSource byteSource = ByteSource.file("path/to/tiff/file.tiff");
        Map<String, Object> params = new HashMap<>();

        // Collect raw image data
        List<byte[]> rawData = parser.collectRawImageData(byteSource, params);

        // Verify that raw data is not null
        assertNotNull(rawData, "Raw image data should not be null");
    }
}