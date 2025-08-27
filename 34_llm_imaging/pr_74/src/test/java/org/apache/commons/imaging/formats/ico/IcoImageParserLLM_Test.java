package org.apache.commons.imaging.formats.ico;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.common.bytesource.ByteSource;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

import static org.apache.commons.imaging.common.BinaryFunctions.read2Bytes;
import static org.apache.commons.imaging.common.BinaryFunctions.readByte;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IcoImageParserLLM_Test {

    @Test
    public void testReadIconInfo() throws IOException {
        byte[] iconInfoBytes = new byte[]{
            16, 16, // width and height
            0, // color count
            0, // reserved
            1, 0, // planes
            1, 0, // bit count (monochrome)
            0, 0, 0, 0, // image size
            0, 0, 0, 0  // image offset
        };

        InputStream is = new ByteArrayInputStream(iconInfoBytes);
        IcoImageParser parser = new IcoImageParser();
        IcoImageParser.IconInfo iconInfo = parser.readIconInfo(is);

        assertEquals(16, iconInfo.width);
        assertEquals(16, iconInfo.height);
        assertEquals(0, iconInfo.colorCount);
        assertEquals(0, iconInfo.reserved);
        assertEquals(1, iconInfo.planes);
        assertEquals(1, iconInfo.bitCount);
        assertEquals(0, iconInfo.imageSize);
        assertEquals(0, iconInfo.imageOffset);
    }

    @Test
    public void testReadIconInfoInvalidPlanes() {
        byte[] iconInfoBytes = new byte[]{
            16, 16, // width and height
            0, // color count
            0, // reserved
            2, 0, // invalid planes (should be 1)
            1, 0, // bit count (monochrome)
            0, 0, 0, 0, // image size
            0, 0, 0, 0  // image offset
        };

        InputStream is = new ByteArrayInputStream(iconInfoBytes);
        IcoImageParser parser = new IcoImageParser();

        assertThrows(ImageReadException.class, () -> parser.readIconInfo(is));
    }

    @Test
    public void testReadIconInfoInvalidBitCount() {
        byte[] iconInfoBytes = new byte[]{
            16, 16, // width and height
            0, // color count
            0, // reserved
            1, 0, // planes
            2, 0, // invalid bit count (should be 1, 4, 8, 24, or 32)
            0, 0, 0, 0, // image size
            0, 0, 0, 0  // image offset
        };

        InputStream is = new ByteArrayInputStream(iconInfoBytes);
        IcoImageParser parser = new IcoImageParser();

        assertThrows(ImageReadException.class, () -> parser.readIconInfo(is));
    }
}