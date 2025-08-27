package org.apache.commons.imaging.formats.pcx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PcxConstantsLLM_Test {

    @Test
    public void testPcxCompressionUncompressed() {
        assertEquals(0, PcxConstants.PCX_COMPRESSION_UNCOMPRESSED);
    }

    @Test
    public void testPcxCompressionRle() {
        assertEquals(1, PcxConstants.PCX_COMPRESSION_RLE);
    }
}