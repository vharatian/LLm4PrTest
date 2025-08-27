package org.apache.commons.compress.archivers.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import java.util.zip.ZipException;

public class AsiExtraFieldLLM_Test implements UnixStat {

    @Test
    public void testParseFromLocalFileDataWithZeroLengthLink() throws Exception {
        byte[] data = {(byte)0xC6, 0x02, 0x78, (byte)0xB6, 0123, (byte)0x80, 0, 0, 0, 0, 5, 0, 6, 0};
        AsiExtraField a = new AsiExtraField();
        a.parseFromLocalFileData(data, 0, data.length);
        assertEquals("length plain file", data.length, a.getLocalFileDataLength().getValue());
        assertEquals("link should be empty", "", a.getLinkedFile());
    }

    @Test
    public void testParseFromLocalFileDataWithInvalidLinkLength() {
        byte[] data = {(byte)0xC6, 0x02, 0x78, (byte)0xB6, 0123, (byte)0x80, 0, 0, 0, 0, 5, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        AsiExtraField a = new AsiExtraField();
        try {
            a.parseFromLocalFileData(data, 0, data.length);
            fail("should raise bad symbolic link name length exception");
        } catch (ZipException e) {
            assertEquals("Bad symbolic link name length 8 in ASI extra field", e.getMessage());
        }
    }

    @Test
    public void testParseFromLocalFileDataWithValidLinkLength() throws Exception {
        byte[] data = {0x75, (byte)0x8E, 0x41, (byte)0xFD, 0123, (byte)0xA0, 4, 0, 0, 0, 5, 0, 6, 0, (byte)'t', (byte)'e', (byte)'s', (byte)'t'};
        AsiExtraField a = new AsiExtraField();
        a.parseFromLocalFileData(data, 0, data.length);
        assertEquals("length link", data.length, a.getLocalFileDataLength().getValue());
        assertEquals("link should be 'test'", "test", a.getLinkedFile());
    }
}