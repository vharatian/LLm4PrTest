package org.apache.commons.compress.archivers.sevenz;

import org.junit.Test;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import static org.junit.Assert.*;

public class AES256SHA256DecoderLLM_Test {

    @Test
    public void testDecodeWithMaxMemoryLimit() throws IOException {
        AES256SHA256Decoder aES256SHA256Decoder = new AES256SHA256Decoder();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(null, 3138);
        Coder coder = new Coder();
        byte[] byteArray = new byte[8];
        byteArray[1] = (byte) (-72);
        coder.properties = byteArray;
        InputStream inputStream = aES256SHA256Decoder.decode("x", bufferedInputStream, 3138, coder, coder.properties, 1024);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            fail("Expecting exception: IOException");
        } catch(Throwable e) {
            assertEquals("Salt size + IV size too long in x", e.getMessage());
            assertEquals("org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder$1", e.getStackTrace()[0].getClassName());
        }
    }

    @Test
    public void testDecodeWithZeroMaxMemoryLimit() throws IOException {
        AES256SHA256Decoder aES256SHA256Decoder = new AES256SHA256Decoder();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(null, 3138);
        Coder coder = new Coder();
        byte[] byteArray = new byte[8];
        byteArray[1] = (byte) (-72);
        coder.properties = byteArray;
        InputStream inputStream = aES256SHA256Decoder.decode("x", bufferedInputStream, 3138, coder, coder.properties, 0);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(inputStream);
            fail("Expecting exception: IOException");
        } catch(Throwable e) {
            assertEquals("Salt size + IV size too long in x", e.getMessage());
            assertEquals("org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder$1", e.getStackTrace()[0].getClassName());
        }
    }
}