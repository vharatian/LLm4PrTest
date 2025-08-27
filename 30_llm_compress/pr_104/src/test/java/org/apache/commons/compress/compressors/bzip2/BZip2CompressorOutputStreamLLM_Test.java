package org.apache.commons.compress.compressors.bzip2;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class BZip2CompressorOutputStreamLLM_Test {

    @Test
    public void testFinalizeWarningOnUnclosedStream() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bzip2Out = new BZip2CompressorOutputStream(baos);
        // Intentionally not closing the stream to trigger finalize warning
        bzip2Out = null;
        System.gc(); // Suggest garbage collection
        // No assertion here, just ensuring no exceptions are thrown
    }

    @Test
    public void testMagicBytesWritten() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bzip2Out = new BZip2CompressorOutputStream(baos);
        bzip2Out.close();
        byte[] outputBytes = baos.toByteArray();
        assertEquals('B', outputBytes[0]);
        assertEquals('Z', outputBytes[1]);
        assertEquals('h', outputBytes[2]);
        assertEquals('9', outputBytes[3]); // Assuming default block size of 9
    }
}