package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BHSDCodecLLM_Test {

    @Test
    public void testEncodeWithNegativeValueAndCardinalityLessThan4294967296L() throws Pack200Exception {
        BHSDCodec codec = new BHSDCodec(2, 128, 0, 0);
        int value = -1;
        byte[] encoded = codec.encode(value, 0);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeWithNegativeValueAndCardinalityGreaterThan4294967296L() throws Pack200Exception {
        BHSDCodec codec = new BHSDCodec(5, 256, 0, 0);
        int value = -1;
        byte[] encoded = codec.encode(value, 0);
        assertNotNull(encoded);
    }

    @Test
    public void testEncodeWithNegativeValueAndCardinalityEqualTo4294967296L() throws Pack200Exception {
        BHSDCodec codec = new BHSDCodec(4, 256, 0, 0);
        int value = -1;
        byte[] encoded = codec.encode(value, 0);
        assertNotNull(encoded);
    }
}