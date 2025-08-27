package org.apache.commons.codec.binary;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64InputStreamLLM_Test {

    private static final byte[] CRLF = { (byte) '\r', (byte) '\n' };
    private static final byte[] LF = { (byte) '\n' };
    private static final String STRING_FIXTURE = "Hello World";

    /**
     * Test to ensure that the constructor with lineLength <= 0 behaves correctly.
     */
    @Test
    public void testConstructorWithLineLengthZero() throws Exception {
        final byte[] decoded = STRING_FIXTURE.getBytes("UTF-8");
        final ByteArrayInputStream bin = new ByteArrayInputStream(decoded);
        try (final Base64InputStream in = new Base64InputStream(bin, true, 0, CRLF)) {
            byte[] output = new byte[decoded.length];
            int bytesRead = in.read(output);
            assertEquals(decoded.length, bytesRead, "Bytes read should match the length of the input");
            assertArrayEquals(decoded, output, "Output should match the input when lineLength is 0");
        }
    }

    /**
     * Test to ensure that the constructor with lineLength > 0 behaves correctly.
     */
    @Test
    public void testConstructorWithPositiveLineLength() throws Exception {
        final byte[] decoded = STRING_FIXTURE.getBytes("UTF-8");
        final ByteArrayInputStream bin = new ByteArrayInputStream(decoded);
        try (final Base64InputStream in = new Base64InputStream(bin, true, 4, CRLF)) {
            byte[] output = new byte[decoded.length];
            int bytesRead = in.read(output);
            assertEquals(decoded.length, bytesRead, "Bytes read should match the length of the input");
            assertArrayEquals(decoded, output, "Output should match the input when lineLength is positive");
        }
    }

    /**
     * Test to ensure that the constructor with strict decoding policy behaves correctly.
     */
    @Test
    public void testConstructorWithStrictDecodingPolicy() throws Exception {
        final byte[] encoded = "SGVsbG8gV29ybGQ=".getBytes("UTF-8");
        final ByteArrayInputStream bin = new ByteArrayInputStream(encoded);
        try (final Base64InputStream in = new Base64InputStream(bin, false, 0, null, CodecPolicy.STRICT)) {
            byte[] output = new byte[STRING_FIXTURE.length()];
            int bytesRead = in.read(output);
            assertEquals(STRING_FIXTURE.length(), bytesRead, "Bytes read should match the length of the decoded string");
            assertEquals(STRING_FIXTURE, new String(output, "UTF-8"), "Output should match the decoded string");
        }
    }

    /**
     * Test to ensure that the constructor with lenient decoding policy behaves correctly.
     */
    @Test
    public void testConstructorWithLenientDecodingPolicy() throws Exception {
        final byte[] encoded = "SGVsbG8gV29ybGQ=".getBytes("UTF-8");
        final ByteArrayInputStream bin = new ByteArrayInputStream(encoded);
        try (final Base64InputStream in = new Base64InputStream(bin, false, 0, null, CodecPolicy.LENIENT)) {
            byte[] output = new byte[STRING_FIXTURE.length()];
            int bytesRead = in.read(output);
            assertEquals(STRING_FIXTURE.length(), bytesRead, "Bytes read should match the length of the decoded string");
            assertEquals(STRING_FIXTURE, new String(output, "UTF-8"), "Output should match the decoded string");
        }
    }
}