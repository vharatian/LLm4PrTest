package org.apache.commons.imaging.formats.png;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PngConstantsLLM_Test {

    @Test
    public void testParamKeyPngTextChunks() {
        assertEquals("PNG_TEXT_CHUNKS", PngConstants.PARAM_KEY_PNG_TEXT_CHUNKS);
    }

    @Test
    public void testParamKeyPhysicalScale() {
        assertEquals("PHYSICAL_SCALE_CHUNK", PngConstants.PARAM_KEY_PHYSICAL_SCALE);
    }
}