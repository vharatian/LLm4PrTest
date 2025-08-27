package org.apache.commons.imaging.palette;

import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaletteLLM_Test {

    @Test
    public void testGetPaletteIndexThrowsImageWriteException() {
        Palette palette = new Palette() {
            @Override
            public int getPaletteIndex(int rgb) throws ImageWriteException {
                throw new ImageWriteException("Test exception");
            }

            @Override
            public int getEntry(int index) {
                return 0;
            }

            @Override
            public int length() {
                return 0;
            }
        };

        assertThrows(ImageWriteException.class, () -> {
            palette.getPaletteIndex(0xFFFFFF);
        });
    }

    @Test
    public void testGetPaletteIndex() {
        Palette palette = new Palette() {
            @Override
            public int getPaletteIndex(int rgb) {
                return 1;
            }

            @Override
            public int getEntry(int index) {
                return 0;
            }

            @Override
            public int length() {
                return 0;
            }
        };

        try {
            assertEquals(1, palette.getPaletteIndex(0xFFFFFF));
        } catch (ImageWriteException e) {
            fail("ImageWriteException should not be thrown");
        }
    }
}