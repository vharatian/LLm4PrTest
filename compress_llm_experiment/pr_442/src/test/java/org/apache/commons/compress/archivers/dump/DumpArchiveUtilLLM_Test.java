package org.apache.commons.compress.archivers.dump;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.junit.jupiter.api.Test;

public class DumpArchiveUtilLLM_Test {

    @Test
    public void testDecodeValidOffsetLength() throws IOException {
        ZipEncoding encoding = ZipEncodingHelper.getZipEncoding("UTF-8");
        byte[] data = "hello world".getBytes("UTF-8");
        String result = DumpArchiveUtil.decode(encoding, data, 0, 5);
        assertEquals("hello", result);
    }

    @Test
    public void testDecodeInvalidOffsetLength() {
        ZipEncoding encoding = ZipEncodingHelper.getZipEncoding("UTF-8");
        byte[] data = "hello world".getBytes("UTF-8");
        assertThrows(IOException.class, () -> {
            DumpArchiveUtil.decode(encoding, data, 5, -1);
        });
    }

    @Test
    public void testVerifyNullBuffer() {
        assertFalse(DumpArchiveUtil.verify(null));
    }

    @Test
    public void testVerifyInvalidMagic() {
        byte[] buffer = new byte[32];
        buffer[24] = 0x00; // invalid magic number
        assertFalse(DumpArchiveUtil.verify(buffer));
    }

    @Test
    public void testVerifyValidBuffer() {
        byte[] buffer = new byte[1024];
        // Set up a valid buffer with correct magic number and checksum
        buffer[24] = (byte) (DumpArchiveConstants.NFS_MAGIC & 0xFF);
        buffer[25] = (byte) ((DumpArchiveConstants.NFS_MAGIC >> 8) & 0xFF);
        buffer[26] = (byte) ((DumpArchiveConstants.NFS_MAGIC >> 16) & 0xFF);
        buffer[27] = (byte) ((DumpArchiveConstants.NFS_MAGIC >> 24) & 0xFF);
        int checksum = DumpArchiveUtil.calculateChecksum(buffer);
        buffer[28] = (byte) (checksum & 0xFF);
        buffer[29] = (byte) ((checksum >> 8) & 0xFF);
        buffer[30] = (byte) ((checksum >> 16) & 0xFF);
        buffer[31] = (byte) ((checksum >> 24) & 0xFF);
        assertTrue(DumpArchiveUtil.verify(buffer));
    }
}