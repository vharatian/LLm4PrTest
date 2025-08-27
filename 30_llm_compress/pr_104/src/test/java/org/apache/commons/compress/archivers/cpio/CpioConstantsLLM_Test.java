package org.apache.commons.compress.archivers.cpio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpioConstantsLLM_Test {

    @Test
    public void testMagicNew() {
        assertEquals("070701", CpioConstants.MAGIC_NEW);
    }

    @Test
    public void testMagicNewCrc() {
        assertEquals("070702", CpioConstants.MAGIC_NEW_CRC);
    }

    @Test
    public void testMagicOldAscii() {
        assertEquals("070707", CpioConstants.MAGIC_OLD_ASCII);
    }

    @Test
    public void testMagicOldBinary() {
        assertEquals(070707, CpioConstants.MAGIC_OLD_BINARY);
    }

    @Test
    public void testFormatNew() {
        assertEquals(1, CpioConstants.FORMAT_NEW);
    }

    @Test
    public void testFormatNewCrc() {
        assertEquals(2, CpioConstants.FORMAT_NEW_CRC);
    }

    @Test
    public void testFormatOldAscii() {
        assertEquals(4, CpioConstants.FORMAT_OLD_ASCII);
    }

    @Test
    public void testFormatOldBinary() {
        assertEquals(8, CpioConstants.FORMAT_OLD_BINARY);
    }

    @Test
    public void testFormatNewMask() {
        assertEquals(3, CpioConstants.FORMAT_NEW_MASK);
    }

    @Test
    public void testFormatOldMask() {
        assertEquals(12, CpioConstants.FORMAT_OLD_MASK);
    }

    @Test
    public void testSIfmt() {
        assertEquals(0170000, CpioConstants.S_IFMT);
    }

    @Test
    public void testCIsSock() {
        assertEquals(0140000, CpioConstants.C_ISSOCK);
    }

    @Test
    public void testCIsLnk() {
        assertEquals(0120000, CpioConstants.C_ISLNK);
    }

    @Test
    public void testCIsNwk() {
        assertEquals(0110000, CpioConstants.C_ISNWK);
    }

    @Test
    public void testCIsReg() {
        assertEquals(0100000, CpioConstants.C_ISREG);
    }

    @Test
    public void testCIsBlk() {
        assertEquals(0060000, CpioConstants.C_ISBLK);
    }

    @Test
    public void testCIsDir() {
        assertEquals(0040000, CpioConstants.C_ISDIR);
    }

    @Test
    public void testCIsChr() {
        assertEquals(0020000, CpioConstants.C_ISCHR);
    }

    @Test
    public void testCIsFifo() {
        assertEquals(0010000, CpioConstants.C_ISFIFO);
    }

    @Test
    public void testCIsUid() {
        assertEquals(0004000, CpioConstants.C_ISUID);
    }

    @Test
    public void testCIsGid() {
        assertEquals(0002000, CpioConstants.C_ISGID);
    }

    @Test
    public void testCIsVtx() {
        assertEquals(0001000, CpioConstants.C_ISVTX);
    }

    @Test
    public void testCIrUsr() {
        assertEquals(0000400, CpioConstants.C_IRUSR);
    }

    @Test
    public void testCIwUsr() {
        assertEquals(0000200, CpioConstants.C_IWUSR);
    }

    @Test
    public void testCIxUsr() {
        assertEquals(0000100, CpioConstants.C_IXUSR);
    }

    @Test
    public void testCIrGrp() {
        assertEquals(0000040, CpioConstants.C_IRGRP);
    }

    @Test
    public void testCIwGrp() {
        assertEquals(0000020, CpioConstants.C_IWGRP);
    }

    @Test
    public void testCIxGrp() {
        assertEquals(0000010, CpioConstants.C_IXGRP);
    }

    @Test
    public void testCIrOth() {
        assertEquals(0000004, CpioConstants.C_IROTH);
    }

    @Test
    public void testCIwOth() {
        assertEquals(0000002, CpioConstants.C_IWOTH);
    }

    @Test
    public void testCIxOth() {
        assertEquals(0000001, CpioConstants.C_IXOTH);
    }

    @Test
    public void testCpioTrailer() {
        assertEquals("TRAILER!!!", CpioConstants.CPIO_TRAILER);
    }

    @Test
    public void testBlockSize() {
        assertEquals(512, CpioConstants.BLOCK_SIZE);
    }
}