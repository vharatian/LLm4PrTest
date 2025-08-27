package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class PopulationCodecLLM_Test {

    @Test
    public void testDecodeIntsWithCorrectResultArrayInitialization() throws IOException, Pack200Exception {
        Codec favouredCodec = new Codec() {
            @Override
            public int decode(InputStream in) throws IOException, Pack200Exception {
                return 0;
            }

            @Override
            public int decode(InputStream in, long last) throws IOException, Pack200Exception {
                return 0;
            }

            @Override
            public int[] decodeInts(int n, InputStream in) throws IOException, Pack200Exception {
                return new int[n];
            }

            @Override
            public byte[] encode(int value, int last) throws Pack200Exception {
                return new byte[0];
            }

            @Override
            public byte[] encode(int value) throws Pack200Exception {
                return new byte[0];
            }
        };

        Codec unfavouredCodec = new Codec() {
            @Override
            public int decode(InputStream in) throws IOException, Pack200Exception {
                return 0;
            }

            @Override
            public int decode(InputStream in, long last) throws IOException, Pack200Exception {
                return 0;
            }

            @Override
            public int[] decodeInts(int n, InputStream in) throws IOException, Pack200Exception {
                return new int[n];
            }

            @Override
            public byte[] encode(int value, int last) throws Pack200Exception {
                return new byte[0];
            }

            @Override
            public byte[] encode(int value) throws Pack200Exception {
                return new byte[0];
            }
        };

        PopulationCodec populationCodec = new PopulationCodec(favouredCodec, 1, unfavouredCodec);
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[0]);
        int[] result = populationCodec.decodeInts(10, in);

        assertNotNull(result);
        assertEquals(10, result.length);
    }
}