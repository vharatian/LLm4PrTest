package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import java.util.zip.ZipException;
import static org.junit.Assert.*;

public class X0017_StrongEncryptionHeaderLLM_Test {

    @Test
    public void testParseCentralDirectoryFormat() throws ZipException {
        X0017_StrongEncryptionHeader header = new X0017_StrongEncryptionHeader();
        byte[] data = new byte[20];
        data[0] = 0x00;
        data[1] = 0x17;
        data[2] = 0x00;
        data[3] = 0x01;
        data[4] = 0x00;
        data[5] = 0x02;
        data[6] = 0x00;
        data[7] = 0x03;
        data[8] = 0x00;
        data[9] = 0x04;
        data[10] = 0x00;
        data[11] = 0x05;
        data[12] = 0x00;
        data[13] = 0x06;
        data[14] = 0x00;
        data[15] = 0x07;
        data[16] = 0x00;
        data[17] = 0x08;
        data[18] = 0x00;
        data[19] = 0x09;
        header.parseCentralDirectoryFormat(data, 0, data.length);
        assertEquals(0x0017, header.getEncryptionAlgorithm().getCode());
        assertEquals(0x0001, header.getHashAlgorithm().getCode());
    }

    @Test
    public void testParseFileFormat() throws ZipException {
        X0017_StrongEncryptionHeader header = new X0017_StrongEncryptionHeader();
        byte[] data = new byte[30];
        data[0] = 0x00;
        data[1] = 0x04;
        data[2] = 0x00;
        data[3] = 0x05;
        data[4] = 0x00;
        data[5] = 0x06;
        data[6] = 0x00;
        data[7] = 0x07;
        data[8] = 0x00;
        data[9] = 0x08;
        data[10] = 0x00;
        data[11] = 0x09;
        data[12] = 0x00;
        data[13] = 0x0A;
        data[14] = 0x00;
        data[15] = 0x0B;
        data[16] = 0x00;
        data[17] = 0x0C;
        data[18] = 0x00;
        data[19] = 0x0D;
        data[20] = 0x00;
        data[21] = 0x0E;
        data[22] = 0x00;
        data[23] = 0x0F;
        data[24] = 0x00;
        data[25] = 0x10;
        data[26] = 0x00;
        data[27] = 0x11;
        data[28] = 0x00;
        data[29] = 0x12;
        header.parseFileFormat(data, 0, data.length);
        assertNotNull(header.ivData);
        assertNotNull(header.erdData);
        assertNotNull(header.recipientKeyHash);
        assertNotNull(header.keyBlob);
        assertNotNull(header.vData);
        assertNotNull(header.vCRC32);
    }

    @Test(expected = ZipException.class)
    public void testParseFileFormatWithInvalidData() throws ZipException {
        X0017_StrongEncryptionHeader header = new X0017_StrongEncryptionHeader();
        byte[] data = new byte[10];
        header.parseFileFormat(data, 0, data.length);
    }

    @Test(expected = ZipException.class)
    public void testParseCentralDirectoryFormatWithInvalidData() throws ZipException {
        X0017_StrongEncryptionHeader header = new X0017_StrongEncryptionHeader();
        byte[] data = new byte[10];
        header.parseCentralDirectoryFormat(data, 0, data.length);
    }
}