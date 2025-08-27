package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class EndianUtilsLLM_Test {

    @Test
    public void testReadSwappedUnsignedInteger() throws IOException {
        final byte[] bytes = { 0x04, 0x03, 0x02, 0x01 };
        assertEquals(0x0000000001020304L, EndianUtils.readSwappedUnsignedInteger(bytes, 0));
        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals(0x0000000001020304L, EndianUtils.readSwappedUnsignedInteger(input));
    }

    @Test
    public void testReadSwappedUnsignedShort() throws IOException {
        final byte[] bytes = { 0x02, 0x01 };
        assertEquals(0x00000102, EndianUtils.readSwappedUnsignedShort(bytes, 0));
        final ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        assertEquals(0x00000102, EndianUtils.readSwappedUnsignedShort(input));
    }
}