package org.apache.commons.codec.binary;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64OutputStreamLLM_Test {

    private final static byte[] CR_LF = {(byte) '\r', (byte) '\n'};
    private final static byte[] LF = {(byte) '\n'};
    private static final String STRING_FIXTURE = "Hello World";

    @Test
    public void testBase64OutputStreamWithLineLengthAndSeparator() throws Exception {
        byte[] encoded = StringUtils.getBytesUtf8("SGVsbG8gV29ybGQ=\r\n");
        byte[] decoded = StringUtils.getBytesUtf8(STRING_FIXTURE);
        testByChunk(encoded, decoded, 76, CR_LF);
    }

    @Test
    public void testBase64OutputStreamWithLineLengthAndSeparatorStrict() throws Exception {
        byte[] encoded = StringUtils.getBytesUtf8("SGVsbG8gV29ybGQ=\r\n");
        byte[] decoded = StringUtils.getBytesUtf8(STRING_FIXTURE);
        testByChunkStrict(encoded, decoded, 76, CR_LF, CodecPolicy.STRICT);
    }

    private void testByChunk(final byte[] encoded, final byte[] decoded, final int chunkSize, final byte[] separator) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        OutputStream out = new Base64OutputStream(byteOut, true, chunkSize, separator);
        out.write(decoded);
        out.close();
        byte[] output = byteOut.toByteArray();
        assertArrayEquals(encoded, output, "Streaming chunked base64 encode");

        byteOut = new ByteArrayOutputStream();
        out = new Base64OutputStream(byteOut, false);
        out.write(encoded);
        out.close();
        output = byteOut.toByteArray();
        assertArrayEquals(decoded, output, "Streaming chunked base64 decode");
    }

    private void testByChunkStrict(final byte[] encoded, final byte[] decoded, final int chunkSize, final byte[] separator, final CodecPolicy policy) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        OutputStream out = new Base64OutputStream(byteOut, true, chunkSize, separator, policy);
        out.write(decoded);
        out.close();
        byte[] output = byteOut.toByteArray();
        assertArrayEquals(encoded, output, "Streaming chunked base64 encode with strict policy");

        byteOut = new ByteArrayOutputStream();
        out = new Base64OutputStream(byteOut, false, chunkSize, separator, policy);
        out.write(encoded);
        out.close();
        output = byteOut.toByteArray();
        assertArrayEquals(decoded, output, "Streaming chunked base64 decode with strict policy");
    }
}