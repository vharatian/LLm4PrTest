package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class AES256SHA256DecoderLLM_Test {

    @Test
    public void testSha256PasswordWithBytes() {
        byte[] password = "password".getBytes(StandardCharsets.UTF_8);
        byte[] salt = "salt".getBytes(StandardCharsets.UTF_8);
        int numCyclesPower = 10;

        byte[] result = AES256SHA256Decoder.sha256Password(password, numCyclesPower, salt);

        // Expected result is not known, but we can check the length of the result
        assertEquals(32, result.length);
    }

    @Test
    public void testSha256PasswordWithChars() {
        char[] password = "password".toCharArray();
        byte[] salt = "salt".getBytes(StandardCharsets.UTF_8);
        int numCyclesPower = 10;

        byte[] result = AES256SHA256Decoder.sha256Password(password, numCyclesPower, salt);

        // Expected result is not known, but we can check the length of the result
        assertEquals(32, result.length);
    }

    @Test
    public void testUtf16Decode() {
        char[] chars = "password".toCharArray();
        byte[] expected = "password".getBytes(StandardCharsets.UTF_16LE);

        byte[] result = AES256SHA256Decoder.utf16Decode(chars);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncode() throws IOException {
        AES256SHA256Decoder decoder = new AES256SHA256Decoder();
        AES256Options options = new AES256Options();
        options.setCipher(Cipher.getInstance("AES/CBC/NoPadding"));
        options.setSalt(new byte[16]);
        options.setIv(new byte[16]);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream encodedStream = decoder.encode(baos, options);

        byte[] data = "test data".getBytes(StandardCharsets.UTF_8);
        encodedStream.write(data);
        encodedStream.close();

        byte[] encodedData = baos.toByteArray();
        // We can't assert the exact value of encodedData, but we can check its length
        assertEquals(16, encodedData.length % 16);
    }

    @Test
    public void testGetOptionsAsProperties() throws IOException {
        AES256SHA256Decoder decoder = new AES256SHA256Decoder();
        AES256Options options = new AES256Options();
        options.setNumCyclesPower(10);
        options.setSalt(new byte[16]);
        options.setIv(new byte[16]);

        byte[] props = decoder.getOptionsAsProperties(options);

        assertEquals(34, props.length);
        assertEquals((byte) 0xC0, props[0]);
        assertEquals((byte) 0xFF, props[1]);
    }
}