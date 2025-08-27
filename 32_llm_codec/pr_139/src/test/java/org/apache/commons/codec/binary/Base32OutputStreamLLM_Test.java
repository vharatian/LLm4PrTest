package org.apache.commons.codec.binary;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base32OutputStreamLLM_Test {

    private final static byte[] CR_LF = {(byte) '\r', (byte) '\n'};
    private final static byte[] LF = {(byte) '\n'};

    @Test
    public void testBase32OutputStreamWithCorrectedParameterName() throws Exception {
        byte[] encoded = StringUtils.getBytesUtf8(Base32TestData.BASE32_FIXTURE);
        byte[] decoded = StringUtils.getBytesUtf8(Base32TestData.STRING_FIXTURE);
        testByChunkWithCorrectedParameterName(encoded, decoded, BaseNCodec.MIME_CHUNK_SIZE, CR_LF);
        final BaseNCodec codec = new Base32();
        for (int i = 0; i <= 150; i++) {
            final byte[][] randomData = BaseNTestData.randomData(codec, i);
            encoded = randomData[1];
            decoded = randomData[0];
            testByChunkWithCorrectedParameterName(encoded, decoded, 0, LF);
        }
    }

    private void testByChunkWithCorrectedParameterName(final byte[] encoded, final byte[] decoded, final int chunkSize, final byte[] separator) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        OutputStream out = new Base32OutputStream(byteOut, true, chunkSize, separator);
        out.write(decoded);
        out.close();
        byte[] output = byteOut.toByteArray();
        assertArrayEquals(encoded, output, "Streaming chunked Base32 encode with corrected parameter name");

        byteOut = new ByteArrayOutputStream();
        out = new Base32OutputStream(byteOut, false);
        out.write(encoded);
        out.close();
        output = byteOut.toByteArray();
        assertArrayEquals(decoded, output, "Streaming chunked Base32 decode with corrected parameter name");

        byteOut = new ByteArrayOutputStream();
        out = byteOut;
        for (int i = 0; i < 10; i++) {
            out = new Base32OutputStream(out, false);
            out = new Base32OutputStream(out, true, chunkSize, separator);
        }
        out.write(decoded);
        out.close();
        output = byteOut.toByteArray();
        assertArrayEquals(decoded, byteOut.toByteArray(), "Streaming chunked Base32 wrap-wrap-wrap with corrected parameter name!");
    }

    @Test
    public void testBase32OutputStreamWithDecodingPolicy() throws Exception {
        byte[] encoded = StringUtils.getBytesUtf8(Base32TestData.BASE32_FIXTURE);
        byte[] decoded = StringUtils.getBytesUtf8(Base32TestData.STRING_FIXTURE);
        testByChunkWithDecodingPolicy(encoded, decoded, BaseNCodec.MIME_CHUNK_SIZE, CR_LF, CodecPolicy.STRICT);
        final BaseNCodec codec = new Base32();
        for (int i = 0; i <= 150; i++) {
            final byte[][] randomData = BaseNTestData.randomData(codec, i);
            encoded = randomData[1];
            decoded = randomData[0];
            testByChunkWithDecodingPolicy(encoded, decoded, 0, LF, CodecPolicy.STRICT);
        }
    }

    private void testByChunkWithDecodingPolicy(final byte[] encoded, final byte[] decoded, final int chunkSize, final byte[] separator, final CodecPolicy decodingPolicy) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        OutputStream out = new Base32OutputStream(byteOut, true, chunkSize, separator, decodingPolicy);
        out.write(decoded);
        out.close();
        byte[] output = byteOut.toByteArray();
        assertArrayEquals(encoded, output, "Streaming chunked Base32 encode with decoding policy");

        byteOut = new ByteArrayOutputStream();
        out = new Base32OutputStream(byteOut, false, chunkSize, separator, decodingPolicy);
        out.write(encoded);
        out.close();
        output = byteOut.toByteArray();
        assertArrayEquals(decoded, output, "Streaming chunked Base32 decode with decoding policy");

        byteOut = new ByteArrayOutputStream();
        out = byteOut;
        for (int i = 0; i < 10; i++) {
            out = new Base32OutputStream(out, false, chunkSize, separator, decodingPolicy);
            out = new Base32OutputStream(out, true, chunkSize, separator, decodingPolicy);
        }
        out.write(decoded);
        out.close();
        output = byteOut.toByteArray();
        assertArrayEquals(decoded, byteOut.toByteArray(), "Streaming chunked Base32 wrap-wrap-wrap with decoding policy!");
    }
}