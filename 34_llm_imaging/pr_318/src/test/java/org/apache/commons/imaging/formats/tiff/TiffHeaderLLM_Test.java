package org.apache.commons.imaging.formats.tiff;

import org.junit.jupiter.api.Test;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class TiffHeaderLLM_Test {

    @Test
    public void testTiffHeaderInitialization() {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        int tiffVersion = 42;
        long offsetToFirstIFD = 8L;
        boolean bigTiff = true;

        TiffHeader tiffHeader = new TiffHeader(byteOrder, tiffVersion, offsetToFirstIFD, bigTiff);

        assertEquals(byteOrder, tiffHeader.byteOrder);
        assertEquals(tiffVersion, tiffHeader.tiffVersion);
        assertEquals(offsetToFirstIFD, tiffHeader.offsetToFirstIFD);
        assertEquals(bigTiff, tiffHeader.bigTiff);
    }

    @Test
    public void testGetElementDescription() {
        TiffHeader tiffHeader = new TiffHeader(ByteOrder.BIG_ENDIAN, 42, 8L, true);
        assertEquals("TIFF Header", tiffHeader.getElementDescription());
    }
}