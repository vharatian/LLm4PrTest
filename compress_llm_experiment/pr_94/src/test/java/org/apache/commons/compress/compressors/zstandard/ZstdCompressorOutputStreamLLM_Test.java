package org.apache.commons.compress.compressors.zstandard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.github.luben.zstd.ZstdOutputStream;

public class ZstdCompressorOutputStreamLLM_Test {

    @Test
    public void testConstructorWithCloseFrameOnFlushAndChecksum() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(baos, 3, true, true);
        assertNotNull(zcos);
        zcos.close();
    }

    @Test
    public void testConstructorWithCloseFrameOnFlush() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(baos, 3, true);
        assertNotNull(zcos);
        zcos.close();
    }

    @Test
    public void testWriteSingleByte() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(baos, 3);
        zcos.write(0);
        zcos.close();
        assertTrue(baos.size() > 0);
    }

    @Test
    public void testWriteByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(baos, 3);
        byte[] data = "test data".getBytes();
        zcos.write(data, 0, data.length);
        zcos.close();
        assertTrue(baos.size() > 0);
    }

    @Test
    public void testFlush() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(baos, 3);
        zcos.write(0);
        zcos.flush();
        assertTrue(baos.size() > 0);
        zcos.close();
    }

    @Test
    public void testToString() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZstdCompressorOutputStream zcos = new ZstdCompressorOutputStream(baos, 3);
        assertNotNull(zcos.toString());
        zcos.close();
    }
}