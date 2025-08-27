package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractUnicodeExtraFieldLLM_Test {

    @Test
    public void testConstructorWithTextAndBytes() {
        String text = "test";
        byte[] bytes = "testBytes".getBytes(StandardCharsets.UTF_8);
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField(text, bytes, 0, bytes.length) {};

        CRC32 crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        long expectedCRC32 = crc32.getValue();

        assertEquals(expectedCRC32, field.getNameCRC32());
        assertArrayEquals(text.getBytes(StandardCharsets.UTF_8), field.getUnicodeName());
    }

    @Test
    public void testSetUnicodeName() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] unicodeName = "unicodeName".getBytes(StandardCharsets.UTF_8);
        field.setUnicodeName(unicodeName);

        assertArrayEquals(unicodeName, field.getUnicodeName());
    }

    @Test
    public void testParseFromLocalFileData() throws ZipException {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] buffer = new byte[] {0x01, 0, 0, 0, 0, 'u', 'n', 'i', 'c', 'o', 'd', 'e'};
        field.parseFromLocalFileData(buffer, 0, buffer.length);

        assertEquals(0, field.getNameCRC32());
        assertArrayEquals("unicode".getBytes(StandardCharsets.UTF_8), field.getUnicodeName());
    }
}