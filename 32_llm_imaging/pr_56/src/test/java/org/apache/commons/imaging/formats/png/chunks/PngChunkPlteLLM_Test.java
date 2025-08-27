package org.apache.commons.imaging.formats.png.chunks;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.png.GammaCorrection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class PngChunkPlteLLM_Test {

    @Test
    public void testGetRgbReturnsClone() throws ImageReadException, IOException {
        byte[] bytes = new byte[]{(byte) 255, 0, 0, 0, (byte) 255, 0, 0, 0, (byte) 255}; // Red, Green, Blue
        PngChunkPlte chunk = new PngChunkPlte(9, 0, 0, bytes);
        int[] rgb1 = chunk.getRgb();
        int[] rgb2 = chunk.getRgb();
        
        assertNotSame(rgb1, rgb2, "The getRgb method should return a clone of the rgb array.");
        assertArrayEquals(rgb1, rgb2, "The cloned rgb arrays should be equal.");
    }
}