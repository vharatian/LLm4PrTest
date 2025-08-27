package org.apache.commons.io.input;

import static org.junit.Assert.assertArrayEquals;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.Random;
import org.junit.Test;

public class MessageDigestCalculatingInputStreamLLM_Test {

    public static byte[] generateRandomByteStream(final int pSize) {
        final byte[] buffer = new byte[pSize];
        final Random rnd = new Random();
        rnd.nextBytes(buffer);
        return buffer;
    }

    @Test
    public void testSingleByteUpdate() throws Exception {
        final byte[] buffer = generateRandomByteStream(1024);
        final MessageDigest md5Sum = MessageDigest.getInstance("MD5");
        for (byte b : buffer) {
            md5Sum.update(b);
        }
        final byte[] expect = md5Sum.digest();
        try (final MessageDigestCalculatingInputStream md5InputStream =
                new MessageDigestCalculatingInputStream(new ByteArrayInputStream(buffer))) {
            for (byte b : buffer) {
                md5InputStream.read();
            }
            final byte[] got = md5InputStream.getMessageDigest().digest();
            assertArrayEquals(expect, got);
        }
    }

    @Test
    public void testByteArrayUpdate() throws Exception {
        final byte[] buffer = generateRandomByteStream(1024);
        final MessageDigest md5Sum = MessageDigest.getInstance("MD5");
        md5Sum.update(buffer, 0, buffer.length);
        final byte[] expect = md5Sum.digest();
        try (final MessageDigestCalculatingInputStream md5InputStream =
                new MessageDigestCalculatingInputStream(new ByteArrayInputStream(buffer))) {
            byte[] readBuffer = new byte[1024];
            while (md5InputStream.read(readBuffer) != -1) {
                // Continue reading
            }
            final byte[] got = md5InputStream.getMessageDigest().digest();
            assertArrayEquals(expect, got);
        }
    }
}