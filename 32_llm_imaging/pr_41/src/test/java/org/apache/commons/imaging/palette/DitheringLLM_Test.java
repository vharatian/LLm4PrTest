package org.apache.commons.imaging.palette;

import java.awt.image.BufferedImage;
import org.apache.commons.imaging.ImageWriteException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DitheringLLM_Test {

    @Test
    public void testApplyFloydSteinbergDithering() throws ImageWriteException {
        BufferedImage image = mock(BufferedImage.class);
        Palette palette = mock(Palette.class);

        when(image.getHeight()).thenReturn(2);
        when(image.getWidth()).thenReturn(2);
        when(image.getRGB(anyInt(), anyInt())).thenReturn(0xFFFFFFFF);
        when(palette.getPaletteIndex(anyInt())).thenReturn(0);
        when(palette.getEntry(anyInt())).thenReturn(0xFF000000);

        Dithering.applyFloydSteinbergDithering(image, palette);

        verify(image, times(4)).setRGB(anyInt(), anyInt(), anyInt());
    }

    @Test
    public void testApplyFloydSteinbergDitheringThrowsImageWriteException() {
        BufferedImage image = mock(BufferedImage.class);
        Palette palette = mock(Palette.class);

        when(image.getHeight()).thenReturn(2);
        when(image.getWidth()).thenReturn(2);
        when(image.getRGB(anyInt(), anyInt())).thenReturn(0xFFFFFFFF);
        when(palette.getPaletteIndex(anyInt())).thenThrow(new ImageWriteException("Failed to read the palette index"));

        ImageWriteException exception = assertThrows(ImageWriteException.class, () -> {
            Dithering.applyFloydSteinbergDithering(image, palette);
        });

        assertEquals("Failed to read the palette index", exception.getMessage());
    }
}