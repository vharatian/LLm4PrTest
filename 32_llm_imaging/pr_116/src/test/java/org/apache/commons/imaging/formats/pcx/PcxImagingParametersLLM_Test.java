package org.apache.commons.imaging.formats.pcx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PcxImagingParametersLLM_Test {

    @Test
    public void testDefaultValues() {
        PcxImagingParameters params = new PcxImagingParameters();
        assertEquals(-1, params.getPlanes());
        assertEquals(-1, params.getBitDepth());
        assertEquals(PcxConstants.PCX_COMPRESSION_UNCOMPRESSED, params.getCompression());
    }

    @Test
    public void testSetAndGetPlanes() {
        PcxImagingParameters params = new PcxImagingParameters();
        params.setPlanes(3);
        assertEquals(3, params.getPlanes());
    }

    @Test
    public void testSetAndGetBitDepth() {
        PcxImagingParameters params = new PcxImagingParameters();
        params.setBitDepth(8);
        assertEquals(8, params.getBitDepth());
    }

    @Test
    public void testSetAndGetCompression() {
        PcxImagingParameters params = new PcxImagingParameters();
        params.setCompression(PcxConstants.PCX_COMPRESSION_RLE);
        assertEquals(PcxConstants.PCX_COMPRESSION_RLE, params.getCompression());
    }
}