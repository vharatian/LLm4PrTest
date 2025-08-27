package org.apache.commons.imaging.common.bytesource;

import org.apache.commons.imaging.ImagingTest;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.assertEquals;

public abstract class ByteSourceLLM_Test extends ImagingTest {

    protected File createTempFile(final byte src[]) throws IOException {
        final File file = createTempFile("raw_", ".bin");
        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStream os = new BufferedOutputStream(fos)) {
            os.write(src);
        }
        assertTrue(src.length == file.length());
        return file;
    }

    protected static byte[][] getTestByteArrays() {
        final byte emptyArray[] = (new byte[0]);
        final byte single[] = new byte[1];
        for (int i = 0; i < single.length; i++) {
            single[i] = (byte) i;
        }
        final byte simple[] = new byte[256];
        for (int i = 0; i < simple.length; i++) {
            simple[i] = (byte) i;
        }
        final byte zeroes[] = new byte[256];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < 256 * 256; i++) {
            baos.write(0xff & i);
            baos.write(0xff & (i >> 8));
        }
        final byte longArray[] = (baos.toByteArray());
        return new byte[][]{emptyArray, single, simple, zeroes, longArray,};
    }

    @Test(expected = NullPointerException.class)
    public void testGetInputStreamThrowsNullPointerException() throws IOException {
        final ByteSourceArray byteSourceArray = new ByteSourceArray(null);
        byteSourceArray.getInputStream(0L);
    }

    @Test
    public void testGetLengthThrowsIOException() {
        ByteSource byteSource = new ByteSource("testfile") {
            @Override
            public InputStream getInputStream() throws IOException {
                throw new IOException("Test IOException");
            }

            @Override
            public byte[] getBlock(long start, int length) throws IOException {
                return new byte[0];
            }

            @Override
            public byte[] getAll() throws IOException {
                return new byte[0];
            }

            @Override
            public long getLength() throws IOException {
                throw new IOException("Test IOException");
            }

            @Override
            public String getDescription() {
                return "Test ByteSource";
            }
        };

        try {
            byteSource.getLength();
        } catch (IOException e) {
            assertEquals("Test IOException", e.getMessage());
        }
    }
}