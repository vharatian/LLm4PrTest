package org.apache.commons.compress.compressors.gzip;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GzipCompressorOutputStreamLLM_Test {

    @Test
    public void testWriteHeaderWithFilenameAndComment() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GzipParameters parameters = new GzipParameters();
        parameters.setFilename("testfile.txt");
        parameters.setComment("This is a test file");

        GzipCompressorOutputStream gzipOut = new GzipCompressorOutputStream(byteArrayOutputStream, parameters);
        gzipOut.close();

        byte[] expectedHeader = new byte[]{
            (byte) GZIPInputStream.GZIP_MAGIC, (byte) (GZIPInputStream.GZIP_MAGIC >> 8),
            Deflater.DEFLATED,
            (byte) (GzipCompressorOutputStream.FNAME | GzipCompressorOutputStream.FCOMMENT),
            0, 0, 0, 0, // Modification time
            0, // Extra flags
            (byte) parameters.getOperatingSystem(),
            't', 'e', 's', 't', 'f', 'i', 'l', 'e', '.', 't', 'x', 't', 0,
            'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't', 'e', 's', 't', ' ', 'f', 'i', 'l', 'e', 0
        };

        byte[] actualHeader = new byte[expectedHeader.length];
        System.arraycopy(byteArrayOutputStream.toByteArray(), 0, actualHeader, 0, expectedHeader.length);

        assertArrayEquals(expectedHeader, actualHeader);
    }

    @Test
    public void testWriteHeaderWithFilenameOnly() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GzipParameters parameters = new GzipParameters();
        parameters.setFilename("testfile.txt");

        GzipCompressorOutputStream gzipOut = new GzipCompressorOutputStream(byteArrayOutputStream, parameters);
        gzipOut.close();

        byte[] expectedHeader = new byte[]{
            (byte) GZIPInputStream.GZIP_MAGIC, (byte) (GZIPInputStream.GZIP_MAGIC >> 8),
            Deflater.DEFLATED,
            (byte) GzipCompressorOutputStream.FNAME,
            0, 0, 0, 0, // Modification time
            0, // Extra flags
            (byte) parameters.getOperatingSystem(),
            't', 'e', 's', 't', 'f', 'i', 'l', 'e', '.', 't', 'x', 't', 0
        };

        byte[] actualHeader = new byte[expectedHeader.length];
        System.arraycopy(byteArrayOutputStream.toByteArray(), 0, actualHeader, 0, expectedHeader.length);

        assertArrayEquals(expectedHeader, actualHeader);
    }

    @Test
    public void testWriteHeaderWithCommentOnly() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GzipParameters parameters = new GzipParameters();
        parameters.setComment("This is a test file");

        GzipCompressorOutputStream gzipOut = new GzipCompressorOutputStream(byteArrayOutputStream, parameters);
        gzipOut.close();

        byte[] expectedHeader = new byte[]{
            (byte) GZIPInputStream.GZIP_MAGIC, (byte) (GZIPInputStream.GZIP_MAGIC >> 8),
            Deflater.DEFLATED,
            (byte) GzipCompressorOutputStream.FCOMMENT,
            0, 0, 0, 0, // Modification time
            0, // Extra flags
            (byte) parameters.getOperatingSystem(),
            'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't', 'e', 's', 't', ' ', 'f', 'i', 'l', 'e', 0
        };

        byte[] actualHeader = new byte[expectedHeader.length];
        System.arraycopy(byteArrayOutputStream.toByteArray(), 0, actualHeader, 0, expectedHeader.length);

        assertArrayEquals(expectedHeader, actualHeader);
    }
}