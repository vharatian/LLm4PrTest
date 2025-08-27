package org.apache.commons.imaging.formats.png.chunks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.png.InterlaceMethod;
import org.apache.commons.imaging.formats.png.PngColorType;
import org.junit.jupiter.api.Test;

public class PngChunkIhdrLLM_Test {

    @Test
    public void testValidInterlaceMethod() throws ImageReadException, IOException {
        byte[] validBytes = new byte[] {
            0, 0, 0, 10, // width
            0, 0, 0, 10, // height
            8, // bit depth
            2, // color type
            0, // compression method
            0, // filter method
            0  // interlace method
        };
        PngChunkIhdr chunk = new PngChunkIhdr(13, 0, 0, validBytes);
        assertEquals(InterlaceMethod.None, chunk.interlaceMethod);
    }

    @Test
    public void testInvalidInterlaceMethod() {
        byte[] invalidBytes = new byte[] {
            0, 0, 0, 10, // width
            0, 0, 0, 10, // height
            8, // bit depth
            2, // color type
            0, // compression method
            0, // filter method
            -1 // invalid interlace method
        };
        assertThrows(ImageReadException.class, () -> {
            new PngChunkIhdr(13, 0, 0, invalidBytes);
        });

        byte[] invalidBytes2 = new byte[] {
            0, 0, 0, 10, // width
            0, 0, 0, 10, // height
            8, // bit depth
            2, // color type
            0, // compression method
            0, // filter method
            3  // invalid interlace method
        };
        assertThrows(ImageReadException.class, () -> {
            new PngChunkIhdr(13, 0, 0, invalidBytes2);
        });
    }
}