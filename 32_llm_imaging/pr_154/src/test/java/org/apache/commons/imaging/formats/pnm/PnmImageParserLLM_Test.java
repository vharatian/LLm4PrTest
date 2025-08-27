package org.apache.commons.imaging.formats.pnm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PnmImageParserLLM_Test {

    @Test
    public void testGetImageSize_happyCase() throws ImageReadException, IOException {
        final byte[] bytes = "P1\n3 2\n0 1 0\n1 0 1\n".getBytes(StandardCharsets.US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        final Dimension result = underTest.getImageSize(bytes, params);
        assertEquals(3, result.getWidth());
        assertEquals(2, result.getHeight());
    }

    @Test
    public void testGetImageSize_invalidHeader() {
        final byte[] bytes = "P1\na 2\n0 0 0 0 0 0 0 0 0 0 0\n1 1 1 1 1 1 1 1 1 1 1\n".getBytes(StandardCharsets.US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        Assertions.assertThrows(ImageReadException.class, () -> underTest.getImageSize(bytes, params));
    }

    @Test
    public void testGetImageInfo_happyCase() throws ImageReadException, IOException {
        final byte[] bytes = "P1\n3 2\n0 1 0\n1 0 1\n".getBytes(StandardCharsets.US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        final ImageInfo result = underTest.getImageInfo(bytes, params);
        assertEquals(1, result.getBitsPerPixel());
        assertEquals(3, result.getWidth());
        assertEquals(2, result.getHeight());
        assertEquals(1, result.getNumberOfImages());
    }

    @Test
    public void testGetImageInfo_invalidHeader() {
        final byte[] bytes = "P1\na 2\n0 0 0 0 0 0 0 0 0 0 0\n1 1 1 1 1 1 1 1 1 1 1\n".getBytes(StandardCharsets.US_ASCII);
        final Map<String, Object> params = Collections.emptyMap();
        final PnmImageParser underTest = new PnmImageParser();
        Assertions.assertThrows(ImageReadException.class, () -> underTest.getImageInfo(bytes, params));
    }
}