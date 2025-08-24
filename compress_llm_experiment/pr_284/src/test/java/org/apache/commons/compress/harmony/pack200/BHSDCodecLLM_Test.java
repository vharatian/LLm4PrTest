package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BHSDCodecLLM_Test {

    @Test
    public void testToString() {
        BHSDCodec codec1 = new BHSDCodec(1, 256);
        assertEquals("(1,256)", codec1.toString());

        BHSDCodec codec2 = new BHSDCodec(2, 128, 1);
        assertEquals("(2,128,1)", codec2.toString());

        BHSDCodec codec3 = new BHSDCodec(3, 64, 2, 1);
        assertEquals("(3,64,2,1)", codec3.toString());

        BHSDCodec codec4 = new BHSDCodec(4, 32, 0, 1);
        assertEquals("(4,32,0,1)", codec4.toString());
    }
}