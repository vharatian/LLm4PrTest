package org.apache.commons.codec.binary;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class Base16OutputStreamLLM_Test {

    private static final String STRING_FIXTURE = "Hello World";

    @Test
    public void testBase16OutputStreamWithDecodingPolicy() throws IOException {
        byte[] encoded = StringUtils.getBytesUtf8("48656C6C6F20576F726C64");
        byte[] decoded = StringUtils.getBytesUtf8(STRING_FIXTURE);

        testWithDecodingPolicy(encoded, decoded, CodecPolicy.LENIENT);
        testWithDecodingPolicy(encoded, decoded, CodecPolicy.STRICT);
    }

    private void testWithDecodingPolicy(final byte[] encoded, final byte[] decoded, final CodecPolicy decodingPolicy) throws IOException {
        try (final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             final OutputStream out = new Base16OutputStream(byteOut, true, false, decodingPolicy)) {
            out.write(decoded);
            final byte[] output = byteOut.toByteArray();
            assertArrayEquals("Streaming base16 encode with decoding policy", encoded, output);
        }

        try (final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             final OutputStream out = new Base16OutputStream(byteOut, false, false, decodingPolicy)) {
            out.write(encoded);
            final byte[] output = byteOut.toByteArray();
            assertArrayEquals("Streaming base16 decode with decoding policy", decoded, output);
        }

        try (final ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
            final OutputStream decoderOut = new Base16OutputStream(byteOut, false, false, decodingPolicy);
            final OutputStream encoderOut = new Base16OutputStream(decoderOut, true, false, decodingPolicy);
            encoderOut.write(decoded);
            final byte[] output = byteOut.toByteArray();
            assertArrayEquals("Streaming base16 wrap-wrap with decoding policy", decoded, output);
        }
    }

    @Test
    public void testBase16OutputStreamLowerCaseWithDecodingPolicy() throws IOException {
        byte[] encoded = StringUtils.getBytesUtf8("48656c6c6f20576f726c64");
        byte[] decoded = StringUtils.getBytesUtf8(STRING_FIXTURE);

        testLowerCaseWithDecodingPolicy(encoded, decoded, CodecPolicy.LENIENT);
        testLowerCaseWithDecodingPolicy(encoded, decoded, CodecPolicy.STRICT);
    }

    private void testLowerCaseWithDecodingPolicy(final byte[] encoded, final byte[] decoded, final CodecPolicy decodingPolicy) throws IOException {
        try (final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             final OutputStream out = new Base16OutputStream(byteOut, true, true, decodingPolicy)) {
            out.write(decoded);
            final byte[] output = byteOut.toByteArray();
            assertArrayEquals("Streaming base16 encode lower-case with decoding policy", encoded, output);
        }

        try (final ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             final OutputStream out = new Base16OutputStream(byteOut, false, true, decodingPolicy)) {
            out.write(encoded);
            final byte[] output = byteOut.toByteArray();
            assertArrayEquals("Streaming base16 decode lower-case with decoding policy", decoded, output);
        }

        try (final ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
            final OutputStream decoderOut = new Base16OutputStream(byteOut, false, true, decodingPolicy);
            final OutputStream encoderOut = new Base16OutputStream(decoderOut, true, true, decodingPolicy);
            encoderOut.write(decoded);
            final byte[] output = byteOut.toByteArray();
            assertArrayEquals("Streaming base16 wrap-wrap lower-case with decoding policy", decoded, output);
        }
    }
}