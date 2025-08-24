package org.apache.commons.compress.compressors.snappy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PureJavaCrc32CLLM_Test {

    @Test
    public void testUpdateWithSingleByte() {
        PureJavaCrc32C crc32c = new PureJavaCrc32C();
        crc32c.update(0x00);
        assertEquals(0x527D5351, crc32c.getValue());
    }

    @Test
    public void testUpdateWithByteArray() {
        PureJavaCrc32C crc32c = new PureJavaCrc32C();
        byte[] data = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07};
        crc32c.update(data, 0, data.length);
        assertEquals(0xE3069283, crc32c.getValue());
    }

    @Test
    public void testReset() {
        PureJavaCrc32C crc32c = new PureJavaCrc32C();
        crc32c.update(0x00);
        crc32c.reset();
        assertEquals(0xFFFFFFFFL, crc32c.getValue());
    }

    @Test
    public void testGetValue() {
        PureJavaCrc32C crc32c = new PureJavaCrc32C();
        assertEquals(0xFFFFFFFFL, crc32c.getValue());
    }
}