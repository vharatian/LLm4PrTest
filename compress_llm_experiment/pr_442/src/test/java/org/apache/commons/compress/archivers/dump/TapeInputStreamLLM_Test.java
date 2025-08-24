package org.apache.commons.compress.archivers.dump;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.junit.Assert.*;

public class TapeInputStreamLLM_Test {

    @Test(expected = IOException.class)
    public void testResetBlockSizeWithZeroRecords() throws IOException {
        TapeInputStream tapeInputStream = new TapeInputStream(new ByteArrayInputStream(new byte[1024]));
        tapeInputStream.resetBlockSize(0, false);
    }

    @Test(expected = IOException.class)
    public void testResetBlockSizeWithNegativeRecords() throws IOException {
        TapeInputStream tapeInputStream = new TapeInputStream(new ByteArrayInputStream(new byte[1024]));
        tapeInputStream.resetBlockSize(-1, false);
    }

    @Test(expected = IOException.class)
    public void testResetBlockSizeWithZeroBlockSize() throws IOException {
        TapeInputStream tapeInputStream = new TapeInputStream(new ByteArrayInputStream(new byte[1024]));
        tapeInputStream.resetBlockSize(1, false);
        tapeInputStream.resetBlockSize(0, false);
    }

    @Test(expected = IOException.class)
    public void testResetBlockSizeWithNegativeBlockSize() throws IOException {
        TapeInputStream tapeInputStream = new TapeInputStream(new ByteArrayInputStream(new byte[1024]));
        tapeInputStream.resetBlockSize(1, false);
        tapeInputStream.resetBlockSize(-1, false);
    }

    @Test
    public void testResetBlockSizeWithValidRecords() throws IOException {
        TapeInputStream tapeInputStream = new TapeInputStream(new ByteArrayInputStream(new byte[1024]));
        tapeInputStream.resetBlockSize(1, false);
        assertEquals(DumpArchiveConstants.TP_SIZE, tapeInputStream.blockSize);
    }
}