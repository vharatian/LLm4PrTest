package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class CodecLLM_Test {

    private final Codec codec = new Codec() {
        @Override
        public int decode(InputStream in) throws IOException, Pack200Exception {
            return 0;
        }

        @Override
        public byte[] encode(int value, int last) throws Pack200Exception {
            return new byte[0];
        }

        @Override
        public byte[] encode(int value) throws Pack200Exception {
            return new byte[0];
        }

        @Override
        public int decode(InputStream in, long last) throws IOException, Pack200Exception {
            return 0;
        }
    };

    @Test
    public void testDecodeIntsWithN() throws IOException, Pack200Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2, 3, 4});
        int[] result = codec.decodeInts(5, in);
        assertEquals(5, result.length);
    }

    @Test
    public void testDecodeIntsWithNAndFirstValue() throws IOException, Pack200Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{0, 1, 2, 3, 4});
        int[] result = codec.decodeInts(5, in, 10);
        assertEquals(6, result.length);
        assertEquals(10, result[0]);
    }
}