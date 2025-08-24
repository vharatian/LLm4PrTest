package org.apache.commons.compress.archivers.sevenz;

import org.junit.Test;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AES256SHA256DecoderLLM_Test {

    @Test
    public void testDecodeWithNullCipherInputStream() throws IOException {
        final AES256SHA256Decoder aES256SHA256Decoder = new AES256SHA256Decoder();
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(null, 3138);
        final Coder coder = new Coder();
        final byte[] byteArray = new byte[8];
        byteArray[1] = (byte) (-72);
        coder.properties = byteArray;
        final InputStream inputStream = aES256SHA256Decoder.decode("x", bufferedInputStream, 3138, coder, coder.properties,
                Integer.MAX_VALUE);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            fail("Expecting exception: IOException");
        } catch(final Throwable e) {
            assertEquals("Salt size + IV size too long in x", e.getMessage());
            assertEquals("org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder$1", e.getStackTrace()[0].getClassName());
        }
    }

    @Test
    public void testDecodeWithUninitializedFields() throws IOException {
        final AES256SHA256Decoder aES256SHA256Decoder = new AES256SHA256Decoder();
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(null, 3138);
        final Coder coder = new Coder();
        final byte[] byteArray = new byte[8];
        byteArray[1] = (byte) (-72);
        coder.properties = byteArray;
        final InputStream inputStream = aES256SHA256Decoder.decode("x", bufferedInputStream, 3138, coder, coder.properties,
                Integer.MAX_VALUE);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            fail("Expecting exception: IOException");
        } catch(final Throwable e) {
            assertEquals("Salt size + IV size too long in x", e.getMessage());
            assertEquals("org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder$1", e.getStackTrace()[0].getClassName());
        }
    }
}