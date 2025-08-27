package org.apache.commons.codec.digest;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PureJavaCrc32CLLM_Test {

    private final PureJavaCrc32C crc = new PureJavaCrc32C();
    private final byte[] data = new byte[32];

    @Test
    public void testZeros() {
        Arrays.fill(data, (byte) 0);
        check(0x8a9136aa);
    }

    @Test
    public void testOnes() {
        Arrays.fill(data, (byte) 0xFF);
        check(0x62a8ab43);
    }

    @Test
    public void testIncreasing() {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        check(0x46dd794e);
    }

    @Test
    public void testDecreasing() {
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (31 - i);
        }
        check(0x113fdb5c);
    }

    private void check(final int expected) {
        crc.reset();
        crc.update(data, 0, data.length);
        final int actual = (int) crc.getValue();
        assertEquals(Integer.toHexString(expected), Integer.toHexString(actual));
    }
}