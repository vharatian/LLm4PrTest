package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.ByteOrderMark;
import org.junit.jupiter.api.Test;

public class BOMInputStreamLLM_Test {

    private InputStream createUtf8DataStream(final byte[] baseData, final boolean addBOM) {
        byte[] data = baseData;
        if (addBOM) {
            data = new byte[baseData.length + 3];
            data[0] = (byte) 0xEF;
            data[1] = (byte) 0xBB;
            data[2] = (byte) 0xBF;
            System.arraycopy(baseData, 0, data, 3, baseData.length);
        }
        return new ByteArrayInputStream(data);
    }

    @Test
    public void testConstructorWithUtf8BOM() throws IOException {
        final byte[] data = { 'A', 'B', 'C' };
        try (BOMInputStream in = new BOMInputStream(createUtf8DataStream(data, true))) {
            assertEquals(ByteOrderMark.UTF_8, in.getBOM(), "getBOM");
        }
    }

    @Test
    public void testConstructorWithUtf8BOMInclude() throws IOException {
        final byte[] data = { 'A', 'B', 'C' };
        try (BOMInputStream in = new BOMInputStream(createUtf8DataStream(data, true), true)) {
            assertEquals(ByteOrderMark.UTF_8, in.getBOM(), "getBOM");
        }
    }

    @Test
    public void testConstructorWithNoBOMs() {
        final byte[] data = { 'A', 'B', 'C' };
        assertThrows(IllegalArgumentException.class, () -> new BOMInputStream(createUtf8DataStream(data, true), false, (ByteOrderMark[]) null).close());
        assertThrows(IllegalArgumentException.class, () -> new BOMInputStream(createUtf8DataStream(data, true), false, new ByteOrderMark[0]).close());
    }
}