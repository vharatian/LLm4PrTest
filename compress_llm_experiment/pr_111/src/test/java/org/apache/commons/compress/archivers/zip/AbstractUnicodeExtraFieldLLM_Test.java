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
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField(text, bytes) {};
        
        CRC32 crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        long expectedCRC32 = crc32.getValue();
        
        assertEquals(expectedCRC32, field.getNameCRC32());
        assertArrayEquals(text.getBytes(StandardCharsets.UTF_8), field.getUnicodeName());
    }

    @Test
    public void testSetNameCRC32() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        long crc32Value = 123456789L;
        field.setNameCRC32(crc32Value);
        assertEquals(crc32Value, field.getNameCRC32());
    }

    @Test
    public void testSetUnicodeName() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] unicodeName = "test".getBytes(StandardCharsets.UTF_8);
        field.setUnicodeName(unicodeName);
        assertArrayEquals(unicodeName, field.getUnicodeName());
    }

    @Test
    public void testGetCentralDirectoryData() {
        String text = "test";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField(text, bytes) {};
        byte[] centralDirectoryData = field.getCentralDirectoryData();
        assertNotNull(centralDirectoryData);
        assertEquals(5 + text.getBytes(StandardCharsets.UTF_8).length, centralDirectoryData.length);
    }

    @Test
    public void testParseFromLocalFileData() throws ZipException {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] buffer = new byte[] {0x01, 0x00, 0x00, 0x00, 0x00, 't', 'e', 's', 't'};
        field.parseFromLocalFileData(buffer, 0, buffer.length);
        assertEquals(0x00, field.getNameCRC32());
        assertArrayEquals("test".getBytes(StandardCharsets.UTF_8), field.getUnicodeName());
    }

    @Test
    public void testParseFromCentralDirectoryData() throws ZipException {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] buffer = new byte[] {0x01, 0x00, 0x00, 0x00, 0x00, 't', 'e', 's', 't'};
        field.parseFromCentralDirectoryData(buffer, 0, buffer.length);
        assertEquals(0x00, field.getNameCRC32());
        assertArrayEquals("test".getBytes(StandardCharsets.UTF_8), field.getUnicodeName());
    }

    @Test
    public void testParseFromLocalFileDataWithInvalidLength() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] buffer = new byte[] {0x01, 0x00, 0x00, 0x00, 0x00};
        assertThrows(ZipException.class, () -> field.parseFromLocalFileData(buffer, 0, 4));
    }

    @Test
    public void testParseFromLocalFileDataWithUnsupportedVersion() {
        AbstractUnicodeExtraField field = new AbstractUnicodeExtraField() {};
        byte[] buffer = new byte[] {0x02, 0x00, 0x00, 0x00, 0x00, 't', 'e', 's', 't'};
        assertThrows(ZipException.class, () -> field.parseFromLocalFileData(buffer, 0, buffer.length));
    }
}