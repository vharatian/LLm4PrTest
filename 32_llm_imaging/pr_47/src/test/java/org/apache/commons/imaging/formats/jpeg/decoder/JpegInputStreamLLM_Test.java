package org.apache.commons.imaging.formats.jpeg.decoder;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JpegInputStreamLLM_Test {

    @Test
    void testNextBit() throws ImageReadException {
        int[] data = {0xFF, 0x00, 0x01, 0x02};
        JpegInputStream jpegInputStream = new JpegInputStream(data);

        // Test reading bits from the stream
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());
        assertEquals(1, jpegInputStream.nextBit());

        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
        assertEquals(0, jpegInputStream.nextBit());
    }

    @Test
    void testHasNext() {
        int[] data = {0xFF, 0x00, 0x01, 0x02};
        JpegInputStream jpegInputStream = new JpegInputStream(data);

        // Test hasNext method
        assertTrue(jpegInputStream.hasNext());
        jpegInputStream.read();
        assertTrue(jpegInputStream.hasNext());
        jpegInputStream.read();
        assertTrue(jpegInputStream.hasNext());
        jpegInputStream.read();
        assertTrue(jpegInputStream.hasNext());
        jpegInputStream.read();
        assertFalse(jpegInputStream.hasNext());
    }

    @Test
    void testRead() {
        int[] data = {0xFF, 0x00, 0x01, 0x02};
        JpegInputStream jpegInputStream = new JpegInputStream(data);

        // Test reading values from the stream
        assertEquals(0xFF, jpegInputStream.read());
        assertEquals(0x00, jpegInputStream.read());
        assertEquals(0x01, jpegInputStream.read());
        assertEquals(0x02, jpegInputStream.read());

        // Test reading beyond the end of the stream
        Exception exception = assertThrows(IllegalStateException.class, jpegInputStream::read);
        assertEquals("This stream hasn't any other value, all values were already read.", exception.getMessage());
    }

    @Test
    void testPrematureEndOfFile() {
        int[] data = {0xFF};
        JpegInputStream jpegInputStream = new JpegInputStream(data);

        // Test premature end of file
        Exception exception = assertThrows(ImageReadException.class, jpegInputStream::nextBit);
        assertEquals("Premature End of File", exception.getMessage());
    }

    @Test
    void testInvalidMarker() {
        int[] data = {0xFF, 0xD8};
        JpegInputStream jpegInputStream = new JpegInputStream(data);

        // Test invalid marker in entropy data
        Exception exception = assertThrows(ImageReadException.class, jpegInputStream::nextBit);
        assertEquals("Invalid marker found in entropy data: 0xFF d8", exception.getMessage());
    }
}