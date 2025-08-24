package org.apache.commons.compress.compressors.gzip;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import static org.junit.jupiter.api.Assertions.*;

public class GzipCompressorOutputStreamLLM_Test {

    @Test
    public void testWriteHeaderWithFilenameAndComment() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipParameters parameters = new GzipParameters();
        parameters.setFilename("testfile.txt");
        parameters.setComment("This is a test file");
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos, parameters);

        byte[] header = baos.toByteArray();
        assertTrue(header.length > 10); // Ensure header is written
        assertEquals("testfile.txt", new String(header, 10, "testfile.txt".length()));
        assertEquals("This is a test file", new String(header, 10 + "testfile.txt".length() + 1, "This is a test file".length()));
    }

    @Test
    public void testWriteHeaderWithoutFilenameAndComment() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipParameters parameters = new GzipParameters();
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos, parameters);

        byte[] header = baos.toByteArray();
        assertEquals(10, header.length); // Ensure header is written
    }

    @Test
    public void testWrite() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos);

        gcos.write('a');
        gcos.close();

        byte[] compressedData = baos.toByteArray();
        assertTrue(compressedData.length > 0); // Ensure data is written
    }

    @Test
    public void testFinish() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos);

        gcos.write('a');
        gcos.finish();

        byte[] compressedData = baos.toByteArray();
        assertTrue(compressedData.length > 0); // Ensure data is written
    }

    @Test
    public void testClose() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos);

        gcos.write('a');
        gcos.close();

        byte[] compressedData = baos.toByteArray();
        assertTrue(compressedData.length > 0); // Ensure data is written
    }

    @Test
    public void testWriteWithDeflaterFinished() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GzipCompressorOutputStream gcos = new GzipCompressorOutputStream(baos);

        gcos.write('a');
        gcos.finish();

        IOException exception = assertThrows(IOException.class, () -> {
            gcos.write('b');
        });

        assertEquals("Cannot write more data, the end of the compressed data stream has been reached", exception.getMessage());
    }
}