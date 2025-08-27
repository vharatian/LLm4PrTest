package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.FormatCompliance;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.apache.commons.imaging.common.bytesource.ByteSourceArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TiffReaderLLM_Test {

    @Test
    public void testReadFirstDirectoryWithEmptyDirectories() throws IOException {
        TiffReader tiffReader = new TiffReader(true);
        ByteSource byteSource = new ByteSourceArray(new byte[]{/* some valid TIFF header bytes */});
        Map<String, Object> params = Collections.emptyMap();
        FormatCompliance formatCompliance = new FormatCompliance("");

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            tiffReader.readFirstDirectory(byteSource, params, true, formatCompliance);
        });

        assertTrue(exception.getMessage().contains("Image did not contain any directories."));
    }

    @Test
    public void testReadDirectoriesWithEmptyDirectories() throws IOException {
        TiffReader tiffReader = new TiffReader(true);
        ByteSource byteSource = new ByteSourceArray(new byte[]{/* some valid TIFF header bytes */});
        FormatCompliance formatCompliance = new FormatCompliance("");

        ImageReadException exception = assertThrows(ImageReadException.class, () -> {
            tiffReader.readDirectories(byteSource, true, formatCompliance);
        });

        assertTrue(exception.getMessage().contains("Image did not contain any directories."));
    }
}