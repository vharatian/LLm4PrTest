package org.apache.commons.compress.archivers.dump;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DumpArchiveEntryLLM_Test {

    @Test
    public void testTypeEnumCode() {
        // Test to ensure the TYPE enum's code field is correctly set and immutable
        assertEquals(14, DumpArchiveEntry.TYPE.WHITEOUT.code);
        assertEquals(12, DumpArchiveEntry.TYPE.SOCKET.code);
        assertEquals(10, DumpArchiveEntry.TYPE.LINK.code);
        assertEquals(8, DumpArchiveEntry.TYPE.FILE.code);
        assertEquals(6, DumpArchiveEntry.TYPE.BLKDEV.code);
        assertEquals(4, DumpArchiveEntry.TYPE.DIRECTORY.code);
        assertEquals(2, DumpArchiveEntry.TYPE.CHRDEV.code);
        assertEquals(1, DumpArchiveEntry.TYPE.FIFO.code);
        assertEquals(15, DumpArchiveEntry.TYPE.UNKNOWN.code);
    }

    @Test
    public void testPermissionEnumCode() {
        // Test to ensure the PERMISSION enum's code field is correctly set and immutable
        assertEquals(04000, DumpArchiveEntry.PERMISSION.SETUID.code);
        assertEquals(02000, DumpArchiveEntry.PERMISSION.SETGUI.code);
        assertEquals(01000, DumpArchiveEntry.PERMISSION.STICKY.code);
        assertEquals(00400, DumpArchiveEntry.PERMISSION.USER_READ.code);
        assertEquals(00200, DumpArchiveEntry.PERMISSION.USER_WRITE.code);
        assertEquals(00100, DumpArchiveEntry.PERMISSION.USER_EXEC.code);
        assertEquals(00040, DumpArchiveEntry.PERMISSION.GROUP_READ.code);
        assertEquals(00020, DumpArchiveEntry.PERMISSION.GROUP_WRITE.code);
        assertEquals(00010, DumpArchiveEntry.PERMISSION.GROUP_EXEC.code);
        assertEquals(00004, DumpArchiveEntry.PERMISSION.WORLD_READ.code);
        assertEquals(00002, DumpArchiveEntry.PERMISSION.WORLD_WRITE.code);
        assertEquals(00001, DumpArchiveEntry.PERMISSION.WORLD_EXEC.code);
    }
}