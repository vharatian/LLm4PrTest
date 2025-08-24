package org.apache.commons.compress.compressors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class CompressorStreamFactoryLLM_Test {

    private CompressorStreamFactory factory;

    @Before
    public void setUp() {
        factory = new CompressorStreamFactory();
    }

    @Test
    public void testCreateCompressorInputStreamAutoDetect() throws Exception {
        byte[] gzipSignature = new byte[] { (byte) 0x1f, (byte) 0x8b, (byte) 0x08 };
        InputStream gzipStream = new ByteArrayInputStream(gzipSignature);
        InputStream bufferedGzipStream = IOUtils.toBufferedInputStream(gzipStream);

        assertEquals(GzipCompressorInputStream.class, factory.createCompressorInputStream(bufferedGzipStream).getClass());
    }

    @Test
    public void testCreateCompressorInputStreamWithNullName() {
        InputStream in = mock(InputStream.class);
        assertThrows(IllegalArgumentException.class, () -> factory.createCompressorInputStream(null, in));
    }

    @Test
    public void testCreateCompressorInputStreamWithNullStream() {
        assertThrows(IllegalArgumentException.class, () -> factory.createCompressorInputStream("gz", null));
    }

    @Test
    public void testCreateCompressorInputStreamWithUnsupportedStream() throws Exception {
        InputStream in = mock(InputStream.class);
        when(in.markSupported()).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> factory.createCompressorInputStream(in));
    }

    @Test
    public void testCreateCompressorInputStreamWithIOException() throws Exception {
        InputStream in = mock(InputStream.class);
        when(in.markSupported()).thenReturn(true);
        when(in.read(new byte[12])).thenThrow(new IOException("Test IOException"));
        assertThrows(CompressorException.class, () -> factory.createCompressorInputStream(in));
    }

    @Test
    public void testCreateCompressorInputStreamWithBzip2() throws Exception {
        byte[] bzip2Signature = new byte[] { (byte) 0x42, (byte) 0x5a, (byte) 0x68 };
        InputStream bzip2Stream = new ByteArrayInputStream(bzip2Signature);
        InputStream bufferedBzip2Stream = IOUtils.toBufferedInputStream(bzip2Stream);

        assertEquals(BZip2CompressorInputStream.class, factory.createCompressorInputStream(bufferedBzip2Stream).getClass());
    }

    @Test
    public void testCreateCompressorInputStreamWithDeflate() throws Exception {
        byte[] deflateSignature = new byte[] { (byte) 0x78, (byte) 0x9c };
        InputStream deflateStream = new ByteArrayInputStream(deflateSignature);
        InputStream bufferedDeflateStream = IOUtils.toBufferedInputStream(deflateStream);

        assertEquals(DeflateCompressorInputStream.class, factory.createCompressorInputStream(bufferedDeflateStream).getClass());
    }

    @Test
    public void testCreateCompressorInputStreamWithXZ() throws Exception {
        byte[] xzSignature = new byte[] { (byte) 0xfd, (byte) 0x37, (byte) 0x7a, (byte) 0x58, (byte) 0x5a, (byte) 0x00 };
        InputStream xzStream = new ByteArrayInputStream(xzSignature);
        InputStream bufferedXzStream = IOUtils.toBufferedInputStream(xzStream);

        assertEquals(XZCompressorInputStream.class, factory.createCompressorInputStream(bufferedXzStream).getClass());
    }

    @Test
    public void testCreateCompressorInputStreamWithZstd() throws Exception {
        byte[] zstdSignature = new byte[] { (byte) 0x28, (byte) 0xb5, (byte) 0x2f, (byte) 0xfd };
        InputStream zstdStream = new ByteArrayInputStream(zstdSignature);
        InputStream bufferedZstdStream = IOUtils.toBufferedInputStream(zstdStream);

        assertEquals(ZstdCompressorInputStream.class, factory.createCompressorInputStream(bufferedZstdStream).getClass());
    }
}