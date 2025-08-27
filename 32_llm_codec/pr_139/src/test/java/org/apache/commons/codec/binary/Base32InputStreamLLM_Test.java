package org.apache.commons.codec.binary;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base32InputStreamLLM_Test {

    private static final String ENCODED_FOO = "MZXW6===";
    private final static byte[] CRLF = { (byte) '\r', (byte) '\n' };
    private final static byte[] LF = { (byte) '\n' };
    private static final String STRING_FIXTURE = "Hello World";

    @Test
    public void testLineLengthAndSeparatorIgnoredWhenDecoding() throws IOException {
        final byte[] encoded = StringUtils.getBytesUtf8(ENCODED_FOO);
        final ByteArrayInputStream bis = new ByteArrayInputStream(encoded);
        final Base32InputStream ins = new Base32InputStream(bis, false, 8, CRLF);
        final byte[] decodedBytes = BaseNTestData.streamToBytes(ins, new byte[64]);
        final String str = StringUtils.newStringUtf8(decodedBytes);
        assertEquals("foo", str);
    }

    @Test
    public void testLineLengthAndSeparatorUsedWhenEncoding() throws IOException {
        final byte[] decoded = StringUtils.getBytesUtf8("foo");
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (final Base32OutputStream base32os = new Base32OutputStream(bos, true, 8, CRLF)) {
            base32os.write(decoded);
        }
        final String encodedString = bos.toString();
        assertEquals("MZXW6===\r\n", encodedString);
    }

    @Test
    public void testStrictDecodingWithLineLengthAndSeparator() throws Exception {
        final String impossibleCase = "MZXW6===";
        final byte[] encoded = StringUtils.getBytesUtf8(impossibleCase);
        final Base32InputStream in = new Base32InputStream(new ByteArrayInputStream(encoded), false, 8, CRLF, CodecPolicy.STRICT);
        assertTrue(in.isStrictDecoding());
        assertThrows(IllegalArgumentException.class, () -> BaseNTestData.streamToBytes(in));
    }
}