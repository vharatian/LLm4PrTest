package org.apache.commons.codec.net;

import static org.junit.Assert.*;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Test;

public class QuotedPrintableCodecLLM_Test {

    @Test
    public void testDecodeWithLineBreaks() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String encoded = "This=20is=20a=20test=0D=0Astring.";
        final String expected = "This is a test\r\nstring.";
        assertEquals("Decoding with CRLF line breaks", expected, qpcodec.decode(encoded));
    }

    @Test
    public void testDecodeWithSoftLineBreaks() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String encoded = "This=20is=20a=20test=20=\r\nstring.";
        final String expected = "This is a test string.";
        assertEquals("Decoding with soft line breaks", expected, qpcodec.decode(encoded));
    }

    @Test
    public void testDecodeWithMixedLineBreaks() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String encoded = "This=20is=20a=20test=0D=0A=20=\r\nstring.";
        final String expected = "This is a test\r\n string.";
        assertEquals("Decoding with mixed line breaks", expected, qpcodec.decode(encoded));
    }

    @Test
    public void testDecodeWithInvalidSoftLineBreak() {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String encoded = "This=20is=20a=20test=20=\rstring.";
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (final DecoderException e) {
            // Expected exception
        }
    }

    @Test
    public void testDecodeWithInvalidCRLF() {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String encoded = "This=20is=20a=20test=0Dstring.";
        try {
            qpcodec.decode(encoded);
            fail("DecoderException should have been thrown");
        } catch (final DecoderException e) {
            // Expected exception
        }
    }
}