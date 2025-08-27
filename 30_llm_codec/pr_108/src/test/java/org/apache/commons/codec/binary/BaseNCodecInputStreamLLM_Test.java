package org.apache.commons.codec.binary;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BaseNCodecInputStreamLLM_Test {

    private BaseNCodecInputStream baseNCodecInputStream;
    private BaseNCodec baseNCodec;
    private InputStream inputStream;

    @BeforeEach
    public void setUp() {
        inputStream = new ByteArrayInputStream(new byte[]{});
        baseNCodec = Mockito.mock(BaseNCodec.class);
        baseNCodecInputStream = new BaseNCodecInputStream(inputStream, baseNCodec, false);
    }

    @Test
    public void testReadWithNullArray() {
        assertThrows(NullPointerException.class, () -> {
            baseNCodecInputStream.read(null, 0, 1);
        });
    }

    @Test
    public void testReadWithNegativeOffset() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            baseNCodecInputStream.read(new byte[10], -1, 1);
        });
    }

    @Test
    public void testReadWithNegativeLength() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            baseNCodecInputStream.read(new byte[10], 0, -1);
        });
    }

    @Test
    public void testReadWithOffsetGreaterThanArrayLength() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            baseNCodecInputStream.read(new byte[10], 11, 1);
        });
    }

    @Test
    public void testReadWithOffsetPlusLengthGreaterThanArrayLength() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            baseNCodecInputStream.read(new byte[10], 5, 6);
        });
    }

    @Test
    public void testReadWithZeroLength() throws IOException {
        byte[] array = new byte[10];
        int result = baseNCodecInputStream.read(array, 0, 0);
        assertEquals(0, result);
    }

    @Test
    public void testReadWithValidParameters() throws IOException {
        byte[] array = new byte[10];
        when(baseNCodec.hasData(any())).thenReturn(false);
        when(inputStream.read(any(byte[].class))).thenReturn(5);
        when(baseNCodec.readResults(any(byte[].class), anyInt(), anyInt(), any())).thenReturn(5);

        int result = baseNCodecInputStream.read(array, 0, 5);
        assertEquals(5, result);
    }
}