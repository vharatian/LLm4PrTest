package org.apache.commons.imaging.formats.pnm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.imaging.ImageReadException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PnmImageParserLLM_Test {
    private static final Charset US_ASCII = StandardCharsets.US_ASCII;

    @Test(expected = ImageReadException.class)
    public void testGetImageInfo_invalidHeader() throws ImageReadException, IOException {
        final byte[] bytes = "P1\n3 2\n0 1 0\n1 0 1\n".getBytes(US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        underTest.getImageInfo(bytes, params);
    }

    @Test(expected = ImageReadException.class)
    public void testGetImageInfo_invalidPrefixByte2() throws ImageReadException, IOException {
        final byte[] bytes = "P9\n3 2\n0 1 0\n1 0 1\n".getBytes(US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        underTest.getImageInfo(bytes, params);
    }

    @Test
    public void testGetImageInfo_validPpmRaw() throws ImageReadException, IOException {
        final byte[] bytes = "P6\n3 2\n255\n".getBytes(US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        final ImageInfo results = underTest.getImageInfo(bytes, params);
        assertEquals(results.getBitsPerPixel(), 24);
        assertEquals(results.getWidth(), 3);
        assertEquals(results.getHeight(), 2);
        assertEquals(results.getNumberOfImages(), 1);
    }
}