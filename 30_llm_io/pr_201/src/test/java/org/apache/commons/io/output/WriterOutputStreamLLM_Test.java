package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class WriterOutputStreamLLM_Test {

    private static final String TEST_STRING = "\u00e0 peine arriv\u00e9s nous entr\u00e2mes dans sa chambre";

    @Test
    public void testUTF16WithSingleByteWrite() throws IOException {
        try {
            testWithSingleByteWrite(TEST_STRING, "UTF-16");
        } catch (final UnsupportedOperationException e){
            if (!System.getProperty("java.vendor").contains("IBM")){
                fail("This test should only throw UOE on IBM JDKs with broken UTF-16");
            }
        }
    }

    @Test
    public void testUTF16WithBufferedWrite() throws IOException {
        try {
            testWithBufferedWrite(TEST_STRING, "UTF-16");
        } catch (final UnsupportedOperationException e) {
            if (!System.getProperty("java.vendor").contains("IBM")) {
                fail("This test should only throw UOE on IBM JDKs with broken UTF-16");
            }
        }
    }

    private void testWithSingleByteWrite(final String testString, final String charsetName) throws IOException {
        final byte[] bytes = testString.getBytes(charsetName);
        final StringWriter writer = new StringWriter();
        try (final WriterOutputStream out = new WriterOutputStream(writer, charsetName)) {
            for (final byte b : bytes) {
                out.write(b);
            }
        }
        assertEquals(testString, writer.toString());
    }

    private void testWithBufferedWrite(final String testString, final String charsetName) throws IOException {
        final byte[] expected = testString.getBytes(charsetName);
        final StringWriter writer = new StringWriter();
        try (final WriterOutputStream out = new WriterOutputStream(writer, charsetName)) {
            int offset = 0;
            while (offset < expected.length) {
                final int length = Math.min(128, expected.length - offset);
                out.write(expected, offset, length);
                offset += length;
            }
        }
        assertEquals(testString, writer.toString());
    }
}