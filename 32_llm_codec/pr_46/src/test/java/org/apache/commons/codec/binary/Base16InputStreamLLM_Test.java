package org.apache.commons.codec.binary;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class Base16InputStreamLLM_Test {

    private static final String ENCODED_B16 = "CAFEBABEFFFF";
    private static final String STRING_FIXTURE = "Hello World";

    @Test
    public void testBase16InputStreamWithDecodingPolicy() throws IOException {
        byte[] encoded = StringUtils.getBytesUtf8("48656C6C6F20576F726C64");
        byte[] decoded = StringUtils.getBytesUtf8(STRING_FIXTURE);
        testWithDecodingPolicy(encoded, decoded, CodecPolicy.STRICT);
        testWithDecodingPolicy(encoded, decoded, CodecPolicy.LENIENT);
    }

    private void testWithDecodingPolicy(final byte[] encoded, final byte[] decoded, final CodecPolicy policy) throws IOException {
        try (final InputStream in = new Base16InputStream(new ByteArrayInputStream(decoded), true, false, policy)) {
            final byte[] output = BaseNTestData.streamToBytes(in);
            assertEquals("EOF", -1, in.read());
            assertEquals("Still EOF", -1, in.read());
            assertArrayEquals("Streaming Base16 encode with policy " + policy, encoded, output);
        }
        try (final InputStream in = new Base16InputStream(new ByteArrayInputStream(encoded), false, false, policy)) {
            final byte[] output = BaseNTestData.streamToBytes(in);
            assertEquals("EOF", -1, in.read());
            assertEquals("Still EOF", -1, in.read());
            assertArrayEquals("Streaming Base16 decode with policy " + policy, decoded, output);
        }
        try (final InputStream in = new ByteArrayInputStream(decoded);
             final InputStream inEncode = new Base16InputStream(in, true, false, policy);
             final InputStream inDecode = new Base16InputStream(inEncode, false, false, policy)) {
            final byte[] output = BaseNTestData.streamToBytes(inDecode);
            assertEquals("EOF", -1, inDecode.read());
            assertEquals("Still EOF", -1, inDecode.read());
            assertArrayEquals("Streaming Base16 wrap-wrap with policy " + policy, decoded, output);
        }
    }

    @Test
    public void testBase16InputStreamWithLowerCaseAndDecodingPolicy() throws IOException {
        byte[] encoded = StringUtils.getBytesUtf8("48656c6c6f20576f726c64");
        byte[] decoded = StringUtils.getBytesUtf8(STRING_FIXTURE);
        testWithLowerCaseAndDecodingPolicy(encoded, decoded, CodecPolicy.STRICT);
        testWithLowerCaseAndDecodingPolicy(encoded, decoded, CodecPolicy.LENIENT);
    }

    private void testWithLowerCaseAndDecodingPolicy(final byte[] encoded, final byte[] decoded, final CodecPolicy policy) throws IOException {
        try (final InputStream in = new Base16InputStream(new ByteArrayInputStream(decoded), true, true, policy)) {
            final byte[] output = BaseNTestData.streamToBytes(in);
            assertEquals("EOF", -1, in.read());
            assertEquals("Still EOF", -1, in.read());
            assertArrayEquals("Streaming Base16 encode with lower case and policy " + policy, encoded, output);
        }
        try (final InputStream in = new Base16InputStream(new ByteArrayInputStream(encoded), false, true, policy)) {
            final byte[] output = BaseNTestData.streamToBytes(in);
            assertEquals("EOF", -1, in.read());
            assertEquals("Still EOF", -1, in.read());
            assertArrayEquals("Streaming Base16 decode with lower case and policy " + policy, decoded, output);
        }
        try (final InputStream in = new ByteArrayInputStream(decoded);
             final InputStream inEncode = new Base16InputStream(in, true, true, policy);
             final InputStream inDecode = new Base16InputStream(inEncode, false, true, policy)) {
            final byte[] output = BaseNTestData.streamToBytes(inDecode);
            assertEquals("EOF", -1, inDecode.read());
            assertEquals("Still EOF", -1, inDecode.read());
            assertArrayEquals("Streaming Base16 wrap-wrap with lower case and policy " + policy, decoded, output);
        }
    }
}