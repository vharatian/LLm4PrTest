package org.apache.commons.imaging.common.mylzw;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class MyBitOutputStreamLLM_Test {

    @Test
    public void testWriteBitsBigEndian() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MyBitOutputStream stream = new MyBitOutputStream(baos, ByteOrder.BIG_ENDIAN);
        stream.writeBits(0b101, 3);
        stream.flushCache();
        byte[] result = baos.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0b10100000, result[0]);
    }

    @Test
    public void testWriteBitsLittleEndian() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MyBitOutputStream stream = new MyBitOutputStream(baos, ByteOrder.LITTLE_ENDIAN);
        stream.writeBits(0b101, 3);
        stream.flushCache();
        byte[] result = baos.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0b00000101, result[0]);
    }

    @Test
    public void testFlushCacheBigEndian() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MyBitOutputStream stream = new MyBitOutputStream(baos, ByteOrder.BIG_ENDIAN);
        stream.writeBits(0b101, 3);
        stream.flushCache();
        byte[] result = baos.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0b10100000, result[0]);
    }

    @Test
    public void testFlushCacheLittleEndian() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MyBitOutputStream stream = new MyBitOutputStream(baos, ByteOrder.LITTLE_ENDIAN);
        stream.writeBits(0b101, 3);
        stream.flushCache();
        byte[] result = baos.toByteArray();
        assertEquals(1, result.length);
        assertEquals(0b00000101, result[0]);
    }

    @Test
    public void testGetBytesWritten() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MyBitOutputStream stream = new MyBitOutputStream(baos, ByteOrder.BIG_ENDIAN);
        stream.writeBits(0b101, 3);
        assertEquals(0, stream.getBytesWritten());
        stream.flushCache();
        assertEquals(1, stream.getBytesWritten());
    }
}