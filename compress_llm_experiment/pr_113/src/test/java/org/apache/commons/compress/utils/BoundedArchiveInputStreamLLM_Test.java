package org.apache.commons.compress.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.*;

class BoundedArchiveInputStreamLLM_Test {

    private BoundedArchiveInputStream boundedArchiveInputStream;

    @BeforeEach
    void setUp() {
        boundedArchiveInputStream = new BoundedArchiveInputStream(0, 10) {
            @Override
            protected int read(long pos, ByteBuffer buf) throws IOException {
                if (pos >= 10) {
                    return -1;
                }
                buf.put((byte) pos);
                return 1;
            }
        };
    }

    @Test
    void testReadSingleByte() throws IOException {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, boundedArchiveInputStream.read());
        }
        assertEquals(-1, boundedArchiveInputStream.read());
    }

    @Test
    void testReadByteArray() throws IOException {
        byte[] buffer = new byte[5];
        assertEquals(5, boundedArchiveInputStream.read(buffer, 0, buffer.length));
        for (int i = 0; i < 5; i++) {
            assertEquals(i, buffer[i]);
        }
        assertEquals(5, boundedArchiveInputStream.read(buffer, 0, buffer.length));
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 5, buffer[i]);
        }
        assertEquals(-1, boundedArchiveInputStream.read(buffer, 0, buffer.length));
    }

    @Test
    void testReadByteArrayWithOffsetAndLength() throws IOException {
        byte[] buffer = new byte[10];
        assertEquals(5, boundedArchiveInputStream.read(buffer, 2, 5));
        for (int i = 2; i < 7; i++) {
            assertEquals(i - 2, buffer[i]);
        }
        assertEquals(5, boundedArchiveInputStream.read(buffer, 2, 5));
        for (int i = 2; i < 7; i++) {
            assertEquals(i + 3, buffer[i]);
        }
        assertEquals(-1, boundedArchiveInputStream.read(buffer, 2, 5));
    }

    @Test
    void testInvalidLengthThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new BoundedArchiveInputStream(Long.MAX_VALUE, 1) {
                @Override
                protected int read(long pos, ByteBuffer buf) throws IOException {
                    return 0;
                }
            };
        });
        assertEquals("Invalid length of stream at offset=9223372036854775807, length=1", exception.getMessage());
    }
}