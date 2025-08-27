package org.apache.commons.imaging.formats.png.chunks;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

public class PngChunkLLM_Test {

    @Test
    public void testConstructorClonesBytes() {
        byte[] originalBytes = {1, 2, 3, 4};
        PngChunk chunk = new PngChunk(4, 0x12345678, 0x9abcdef0, originalBytes);
        byte[] chunkBytes = chunk.getBytes();
        assertNotSame(originalBytes, chunkBytes);
        assertArrayEquals(originalBytes, chunkBytes);
    }

    @Test
    public void testGetBytesReturnsClone() {
        byte[] originalBytes = {1, 2, 3, 4};
        PngChunk chunk = new PngChunk(4, 0x12345678, 0x9abcdef0, originalBytes);
        byte[] bytes1 = chunk.getBytes();
        byte[] bytes2 = chunk.getBytes();
        assertNotSame(bytes1, bytes2);
        assertArrayEquals(bytes1, bytes2);
    }

    @Test
    public void testGetPropertyBitsReturnsClone() {
        byte[] originalBytes = {1, 2, 3, 4};
        PngChunk chunk = new PngChunk(4, 0x12345678, 0x9abcdef0, originalBytes);
        boolean[] propertyBits1 = chunk.getPropertyBits();
        boolean[] propertyBits2 = chunk.getPropertyBits();
        assertNotSame(propertyBits1, propertyBits2);
        assertArrayEquals(propertyBits1, propertyBits2);
    }

    @Test
    public void testGetDataStream() {
        byte[] originalBytes = {1, 2, 3, 4};
        PngChunk chunk = new PngChunk(4, 0x12345678, 0x9abcdef0, originalBytes);
        ByteArrayInputStream dataStream = chunk.getDataStream();
        assertNotNull(dataStream);
        byte[] streamBytes = dataStream.readAllBytes();
        assertArrayEquals(originalBytes, streamBytes);
    }
}