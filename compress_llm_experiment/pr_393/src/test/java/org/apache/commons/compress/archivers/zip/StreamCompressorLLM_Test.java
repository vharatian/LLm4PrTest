package org.apache.commons.compress.archivers.zip;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;

import org.junit.jupiter.api.Test;

public class StreamCompressorLLM_Test {

    @Test
    public void testDeflateWithLargeBuffer() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final StreamCompressor sc = StreamCompressor.create(baos);
        
        // Create a large buffer of 100000 'A' characters
        byte[] largeBuffer = new byte[100000];
        for (int i = 0; i < largeBuffer.length; i++) {
            largeBuffer[i] = 'A';
        }
        
        sc.deflate(new ByteArrayInputStream(largeBuffer), ZipEntry.DEFLATED);
        
        assertEquals(100000, sc.getBytesRead());
        assertNotNull(sc.getBytesWrittenForLastEntry());
        assertNotNull(sc.getCrc32());
        
        final byte[] actuals = baos.toByteArray();
        assertNotNull(actuals);
    }

    @Test
    public void testDeflateWithSmallBuffer() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final StreamCompressor sc = StreamCompressor.create(baos);
        
        // Create a small buffer of 10 'B' characters
        byte[] smallBuffer = new byte[10];
        for (int i = 0; i < smallBuffer.length; i++) {
            smallBuffer[i] = 'B';
        }
        
        sc.deflate(new ByteArrayInputStream(smallBuffer), ZipEntry.DEFLATED);
        
        assertEquals(10, sc.getBytesRead());
        assertNotNull(sc.getBytesWrittenForLastEntry());
        assertNotNull(sc.getCrc32());
        
        final byte[] actuals = baos.toByteArray();
        assertNotNull(actuals);
    }
}