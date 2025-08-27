package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.junit.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testConstructorWithDataOffset() throws IOException {
        byte[] headerBuf = new byte[512];
        ZipEncoding encoding = ZipEncodingHelper.getZipEncoding("UTF-8");
        long dataOffset = 12345L;
        TarArchiveEntry entry = new TarArchiveEntry(headerBuf, encoding, false, dataOffset);
        assertEquals(dataOffset, entry.getDataOffset());
    }

    @Test
    public void testSetDataOffset() {
        TarArchiveEntry entry = new TarArchiveEntry("test.txt");
        long dataOffset = 12345L;
        entry.setDataOffset(dataOffset);
        assertEquals(dataOffset, entry.getDataOffset());
    }

    @Test
    public void testSetDataOffsetNegative() {
        TarArchiveEntry entry = new TarArchiveEntry("test.txt");
        try {
            entry.setDataOffset(-1);
            fail("Should have generated IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            assertEquals("The offset can not be smaller than 0", expected.getMessage());
        }
    }

    @Test
    public void testIsStreamContiguous() {
        TarArchiveEntry entry = new TarArchiveEntry("test.txt");
        assertTrue(entry.isStreamContiguous());
    }
}