package org.apache.commons.io.output;

import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractByteArrayOutputStreamLLM_Test {

    // Test for toInputStream method
    @Test
    public void testToInputStream() throws Exception {
        AbstractByteArrayOutputStream stream = new AbstractByteArrayOutputStream() {
            @Override
            public void reset() {
                resetImpl();
            }

            @Override
            public int size() {
                return count;
            }

            @Override
            public byte[] toByteArray() {
                return toByteArrayImpl();
            }

            @Override
            public InputStream toInputStream() {
                return toInputStream(buf -> new InputStream() {
                    private int pos = 0;
                    private int mark = 0;

                    @Override
                    public int read() {
                        return (pos < buf.length) ? (buf[pos++] & 0xff) : -1;
                    }

                    @Override
                    public int read(byte[] b, int off, int len) {
                        if (pos >= buf.length) {
                            return -1;
                        }
                        int avail = buf.length - pos;
                        if (len > avail) {
                            len = avail;
                        }
                        if (len <= 0) {
                            return 0;
                        }
                        System.arraycopy(buf, pos, b, off, len);
                        pos += len;
                        return len;
                    }

                    @Override
                    public long skip(long n) {
                        long k = buf.length - pos;
                        if (n < k) {
                            k = n < 0 ? 0 : n;
                        }
                        pos += k;
                        return k;
                    }

                    @Override
                    public int available() {
                        return buf.length - pos;
                    }

                    @Override
                    public void mark(int readAheadLimit) {
                        mark = pos;
                    }

                    @Override
                    public void reset() {
                        pos = mark;
                    }

                    @Override
                    public boolean markSupported() {
                        return true;
                    }
                });
            }

            @Override
            public void write(byte[] b, int off, int len) {
                writeImpl(b, off, len);
            }

            @Override
            public int write(InputStream in) throws IOException {
                return writeImpl(in);
            }

            @Override
            public void write(int b) {
                writeImpl(b);
            }

            @Override
            public void writeTo(OutputStream out) throws IOException {
                writeToImpl(out);
            }
        };

        String testString = "Test String";
        stream.write(testString.getBytes(StandardCharsets.UTF_8));
        InputStream inputStream = stream.toInputStream();
        byte[] result = new byte[testString.length()];
        int bytesRead = inputStream.read(result);
        assertEquals(testString.length(), bytesRead);
        assertArrayEquals(testString.getBytes(StandardCharsets.UTF_8), result);
    }
}