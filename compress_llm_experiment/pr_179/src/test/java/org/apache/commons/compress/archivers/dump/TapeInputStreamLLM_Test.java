package org.apache.commons.compress.archivers.dump;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class TapeInputStreamLLM_Test {

    private TapeInputStream tapeInputStream;
    private final byte[] dummyData = new byte[DumpArchiveConstants.TP_SIZE * 2];

    @BeforeEach
    void setUp() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dummyData);
        tapeInputStream = new TapeInputStream(byteArrayInputStream);
    }

    @Test
    void testResetBlockSizeWithValidParams() throws IOException {
        tapeInputStream.resetBlockSize(2, true);
        assertEquals(DumpArchiveConstants.TP_SIZE * 2, tapeInputStream.available());
    }

    @Test
    void testResetBlockSizeWithInvalidParams() {
        IOException exception = assertThrows(IOException.class, () -> {
            tapeInputStream.resetBlockSize(0, true);
        });
        assertEquals("Block with 0 records found, must be at least 1", exception.getMessage());
    }

    @Test
    void testReadFully() throws IOException {
        byte[] buffer = new byte[DumpArchiveConstants.TP_SIZE];
        tapeInputStream.readFully(buffer, 0, DumpArchiveConstants.TP_SIZE);
        assertArrayEquals(new byte[DumpArchiveConstants.TP_SIZE], buffer);
    }

    @Test
    void testGetBytesRead() throws IOException {
        tapeInputStream.resetBlockSize(2, true);
        tapeInputStream.readRecord();
        assertEquals(DumpArchiveConstants.TP_SIZE * 2, tapeInputStream.getBytesRead());
    }

    @Test
    void testPeek() throws IOException {
        byte[] peekedData = tapeInputStream.peek();
        assertNotNull(peekedData);
        assertEquals(DumpArchiveConstants.TP_SIZE, peekedData.length);
    }

    @Test
    void testReadRecord() throws IOException {
        byte[] record = tapeInputStream.readRecord();
        assertNotNull(record);
        assertEquals(DumpArchiveConstants.TP_SIZE, record.length);
    }
}