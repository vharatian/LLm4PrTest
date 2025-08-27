package org.apache.commons.codec.net;

import static org.junit.Assert.*;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.DecoderException;
import org.junit.Test;

public class QuotedPrintableCodecLLM_Test {

    @Test
    public void testEncodeQuotedPrintableWithStrictMode() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);
        final String plain = "This is a test string with a length that should trigger soft line breaks in strict mode.";
        final String expected = "This is a test string with a length that should trigger soft line break=\r\ns in strict mode.";
        assertEquals(expected, qpcodec.encode(plain));
    }

    @Test
    public void testEncodeQuotedPrintableWithFinalBytes() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);
        final String plain = "This is a test string with final bytes that should be handled correctly.";
        final String expected = "This is a test string with final bytes that should be handled correctl=\r\ny.";
        assertEquals(expected, qpcodec.encode(plain));
    }

    @Test
    public void testEncodeQuotedPrintableWithWhitespace() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);
        final String plain = "This is a test string with trailing whitespace ";
        final String expected = "This is a test string with trailing whitespace=20=\r\n";
        assertEquals(expected, qpcodec.encode(plain));
    }

    @Test
    public void testEncodeQuotedPrintableWithTab() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);
        final String plain = "This is a test string with trailing tab\t";
        final String expected = "This is a test string with trailing tab=09=\r\n";
        assertEquals(expected, qpcodec.encode(plain));
    }

    @Test
    public void testEncodeQuotedPrintableWithMultipleLines() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);
        final String plain = "This is a test string that spans multiple lines and should be encoded correctly.";
        final String expected = "This is a test string that spans multiple lines and should be encoded=\r\n correctly.";
        assertEquals(expected, qpcodec.encode(plain));
    }
}