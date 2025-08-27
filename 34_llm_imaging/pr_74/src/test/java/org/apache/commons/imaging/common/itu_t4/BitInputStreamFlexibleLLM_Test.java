package org.apache.commons.imaging.common.itu_t4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class BitInputStreamFlexibleLLM_Test {

    private BitInputStreamFlexible bitInputStreamFlexible;
    private InputStream inputStream;

    @BeforeEach
    void setUp() {
        byte[] data = {0b01010101, 0b01101100}; // Example data
        inputStream = new ByteArrayInputStream(data);
        bitInputStreamFlexible = new BitInputStreamFlexible(inputStream);
    }

    @Test
    void testRead() throws IOException {
        // Test reading a full byte
        int result = bitInputStreamFlexible.read();
        assertEquals(0b01010101, result);

        // Test reading another full byte
        result = bitInputStreamFlexible.read();
        assertEquals(0b01101100, result);
    }

    @Test
    void testReadBits() throws IOException {
        // Test reading 4 bits
        int result = bitInputStreamFlexible.readBits(4);
        assertEquals(0b0101, result);

        // Test reading another 4 bits
        result = bitInputStreamFlexible.readBits(4);
        assertEquals(0b0101, result);

        // Test reading 8 bits
        result = bitInputStreamFlexible.readBits(8);
        assertEquals(0b01101100, result);
    }

    @Test
    void testFlushCache() throws IOException {
        // Read some bits to fill the cache
        bitInputStreamFlexible.readBits(4);

        // Flush the cache
        bitInputStreamFlexible.flushCache();

        // Read a full byte after flushing cache
        int result = bitInputStreamFlexible.read();
        assertEquals(0b01101100, result);
    }

    @Test
    void testGetBytesRead() throws IOException {
        // Initially, bytesRead should be 0
        assertEquals(0, bitInputStreamFlexible.getBytesRead());

        // Read some bits
        bitInputStreamFlexible.readBits(4);
        assertEquals(0, bitInputStreamFlexible.getBytesRead());

        // Read another 4 bits to complete a byte
        bitInputStreamFlexible.readBits(4);
        assertEquals(1, bitInputStreamFlexible.getBytesRead());

        // Read another byte
        bitInputStreamFlexible.readBits(8);
        assertEquals(2, bitInputStreamFlexible.getBytesRead());
    }

    @Test
    void testReadIncompleteBitThrowsException() {
        // Read some bits to fill the cache
        assertThrows(IOException.class, () -> {
            bitInputStreamFlexible.readBits(4);
            bitInputStreamFlexible.read();
        });
    }
}