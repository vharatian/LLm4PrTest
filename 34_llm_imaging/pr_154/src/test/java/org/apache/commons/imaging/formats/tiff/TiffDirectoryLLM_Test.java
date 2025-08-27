package org.apache.commons.imaging.formats.tiff;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiffDirectoryLLM_Test {

    @Test
    public void testGetTiffImageWithByteOrder() throws ImageReadException, IOException {
        // Mock dependencies
        TiffImageParser mockParser = mock(TiffImageParser.class);
        TiffDirectory directory = mock(TiffDirectory.class);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        BufferedImage mockImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        // Mock behavior
        when(directory.getTiffImage(byteOrder, null)).thenCallRealMethod();
        when(mockParser.getBufferedImage(directory, byteOrder, null)).thenReturn(mockImage);

        // Call the method under test
        BufferedImage result = directory.getTiffImage(byteOrder);

        // Verify the result
        assertNotNull(result);
        assertEquals(mockImage, result);
    }
}