package org.apache.commons.imaging.formats.pnm;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.Imaging;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PnmImageParserLLM_Test {

    @Test
    public void testWriteImage_noParams() throws ImageWriteException, ImageReadException, IOException {
        final BufferedImage srcImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        final Map<String, Object> params = Collections.emptyMap();
        final byte[] dstBytes = Imaging.writeImageToBytes(srcImage, ImageFormats.PNM, params);
        final BufferedImage dstImage = Imaging.getBufferedImage(dstBytes);
        assertTrue(srcImage.getWidth() == dstImage.getWidth());
        assertTrue(srcImage.getHeight() == dstImage.getHeight());
        final DataBufferInt srcData = (DataBufferInt) srcImage.getRaster().getDataBuffer();
        final DataBufferInt dstData = (DataBufferInt) dstImage.getRaster().getDataBuffer();
        for (int bank = 0; bank < srcData.getNumBanks(); bank++) {
            final int[] actual = srcData.getData(bank);
            final int[] expected = dstData.getData(bank);
            assertArrayEquals(actual, expected);
        }
    }

    @Test
    public void testWriteImage_withUnknownParam() {
        final BufferedImage srcImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        final Map<String, Object> params = new HashMap<>();
        params.put("UNKNOWN_PARAM", "UNKNOWN_VALUE");
        final PnmImageParser underTest = new PnmImageParser();
        Assertions.assertThrows(ImageWriteException.class, () -> {
            underTest.writeImage(srcImage, null, params);
        });
    }
}