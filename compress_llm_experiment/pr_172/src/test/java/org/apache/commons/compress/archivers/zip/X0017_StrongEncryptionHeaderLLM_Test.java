package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.zip.ZipException;

public class X0017_StrongEncryptionHeaderLLM_Test {

    @Test
    public void testParseCentralDirectoryFormatWithRecordCount() throws ZipException {
        X0017_StrongEncryptionHeader header = new X0017_StrongEncryptionHeader();
        byte[] data = new byte[16];
        data[0] = 0x00; data[1] = 0x01; // format
        data[2] = 0x00; data[3] = 0x02; // algId
        data[4] = 0x00; data[5] = 0x03; // bitlen
        data[6] = 0x00; data[7] = 0x04; // flags
        data[8] = 0x00; data[9] = 0x00; data[10] = 0x00; data[11] = 0x01; // rcount
        data[12] = 0x00; data[13] = 0x05; // hashAlg
        data[14] = 0x00; data[15] = 0x06; // hashSize

        header.parseCentralDirectoryFormat(data, 0, data.length);

        assertEquals(1, header.getRecordCount());
        assertEquals(HashAlgorithm.getAlgorithmByCode(5), header.getHashAlgorithm());
        assertEquals(6, header.hashSize);
    }

    @Test
    public void testParseCentralDirectoryFormatWithoutRecordCount() throws ZipException {
        X0017_StrongEncryptionHeader header = new X0017_StrongEncryptionHeader();
        byte[] data = new byte[12];
        data[0] = 0x00; data[1] = 0x01; // format
        data[2] = 0x00; data[3] = 0x02; // algId
        data[4] = 0x00; data[5] = 0x03; // bitlen
        data[6] = 0x00; data[7] = 0x04; // flags
        data[8] = 0x00; data[9] = 0x00; data[10] = 0x00; data[11] = 0x00; // rcount

        header.parseCentralDirectoryFormat(data, 0, data.length);

        assertEquals(0, header.getRecordCount());
    }
}