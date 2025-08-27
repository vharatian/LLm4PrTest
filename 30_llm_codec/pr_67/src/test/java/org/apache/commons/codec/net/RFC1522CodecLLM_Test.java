package org.apache.commons.codec.net;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.DecoderException;
import org.junit.Test;

public class RFC1522CodecLLM_Test {
    static class RFC1522TestCodec extends RFC1522Codec {
        @Override
        protected byte[] doDecoding(final byte[] bytes) {
            return bytes;
        }

        @Override
        protected byte[] doEncoding(final byte[] bytes) {
            return bytes;
        }

        @Override
        protected String getEncoding() {
            return "T";
        }
    }

    @Test
    public void testNullInput() throws Exception {
        final RFC1522TestCodec testcodec = new RFC1522TestCodec();
        assertNull(testcodec.decodeText(null));
        assertNull(testcodec.encodeText(null, CharEncoding.UTF_8));
    }

    private void assertExpectedDecoderException(final String s) throws Exception {
        final RFC1522TestCodec testcodec = new RFC1522TestCodec();
        try {
            testcodec.decodeText(s);
            fail("DecoderException should have been thrown");
        } catch (final DecoderException e) {
        }
    }

    @Test
    public void testDecodeInvalid() throws Exception {
        assertExpectedDecoderException("whatever");
        assertExpectedDecoderException("=?");
        assertExpectedDecoderException("?=");
        assertExpectedDecoderException("==");
        assertExpectedDecoderException("=??=");
        assertExpectedDecoderException("=?stuff?=");
        assertExpectedDecoderException("=?UTF-8??=");
        assertExpectedDecoderException("=?UTF-8?stuff?=");
        assertExpectedDecoderException("=?UTF-8?T?stuff");
        assertExpectedDecoderException("=??T?stuff?=");
        assertExpectedDecoderException("=?UTF-8??stuff?=");
        assertExpectedDecoderException("=?UTF-8?W?stuff?=");
    }

    @Test
    public void testEncodeTextWithCharset() throws Exception {
        final RFC1522TestCodec testcodec = new RFC1522TestCodec();
        String encoded = testcodec.encodeText("test", CharEncoding.UTF_8);
        // Verify the encoded string format
        assert(encoded.startsWith("=?UTF-8?T?"));
        assert(encoded.endsWith("?="));
    }

    @Test
    public void testEncodeTextWithCharsetName() throws Exception {
        final RFC1522TestCodec testcodec = new RFC1522TestCodec();
        String encoded = testcodec.encodeText("test", "UTF-8");
        // Verify the encoded string format
        assert(encoded.startsWith("=?UTF-8?T?"));
        assert(encoded.endsWith("?="));
    }

    @Test
    public void testDecodeTextValid() throws Exception {
        final RFC1522TestCodec testcodec = new RFC1522TestCodec();
        String decoded = testcodec.decodeText("=?UTF-8?T?test?=");
        // Verify the decoded string
        assert("test".equals(decoded));
    }
}