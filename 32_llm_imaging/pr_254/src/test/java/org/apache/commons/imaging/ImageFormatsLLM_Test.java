package org.apache.commons.imaging;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageFormatsLLM_Test {

    @Test
    public void testWebpExtension() {
        ImageFormats format = ImageFormats.WEBP;
        assertNotNull(format.getExtensions());
        assertEquals("webp", format.getDefaultExtension());
        assertArrayEquals(new String[]{"webp"}, format.getExtensions());
    }

    @Test
    public void testWebpName() {
        ImageFormats format = ImageFormats.WEBP;
        assertEquals("WEBP", format.getName());
    }
}