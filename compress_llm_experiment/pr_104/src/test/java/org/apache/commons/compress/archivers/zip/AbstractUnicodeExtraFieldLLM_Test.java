package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractUnicodeExtraFieldLLM_Test {

    @Test
    public void testConstructorWithOffsetAndLength() {
        String text = "test";
        byte[] bytes = {0x74, 0x65, 0x73, 0x74};
        int offset = 0;
        int length = 4;

        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField(text, bytes, offset, length) {};

        assertNotNull(field.getUnicodeName());
        assertEquals("test", new String(field.getUnicodeName()));
        assertEquals(0xD87F7E0C, field.getNameCRC32());
    }

    @Test
    public void testSetUnicodeName() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] unicodeName = {0x74, 0x65, 0x73, 0x74};

        field.setUnicodeName(unicodeName);

        assertNotNull(field.getUnicodeName());
        assertArrayEquals(unicodeName, field.getUnicodeName());
    }

    @Test
    public void testSetNameCRC32() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        long crc32 = 0xD87F7E0C;

        field.setNameCRC32(crc32);

        assertEquals(crc32, field.getNameCRC32());
    }

    @Test
    public void testAssembleData() {
        String text = "test";
        byte[] bytes = {0x74, 0x65, 0x73, 0x74};
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField(text, bytes) {};

        byte[] data = field.getCentralDirectoryData();

        assertNotNull(data);
        assertEquals(9, data.length);
        assertEquals(0x01, data[0]);
        assertEquals(0xD8, data[1]);
        assertEquals(0x7F, data[2]);
        assertEquals(0x7E, data[3]);
        assertEquals(0x0C, data[4]);
        assertEquals(0x74, data[5]);
        assertEquals(0x65, data[6]);
        assertEquals(0x73, data[7]);
        assertEquals(0x74, data[8]);
    }

    @Test
    public void testParseFromLocalFileData() throws ZipException {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] buffer = {0x01, (byte)0xD8, 0x7F, 0x7E, 0x0C, 0x74, 0x65, 0x73, 0x74};

        field.parseFromLocalFileData(buffer, 0, buffer.length);

        assertEquals(0xD87F7E0C, field.getNameCRC32());
        assertNotNull(field.getUnicodeName());
        assertEquals("test", new String(field.getUnicodeName()));
    }
}