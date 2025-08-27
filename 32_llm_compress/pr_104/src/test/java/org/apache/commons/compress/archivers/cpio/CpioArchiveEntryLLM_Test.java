package org.apache.commons.compress.archivers.cpio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpioArchiveEntryLLM_Test {

    @Test
    public void testFileFormatInitialization() {
        CpioArchiveEntry entryNew = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        assertEquals(CpioConstants.FORMAT_NEW, entryNew.getFormat());

        CpioArchiveEntry entryNewCrc = new CpioArchiveEntry(CpioConstants.FORMAT_NEW_CRC);
        assertEquals(CpioConstants.FORMAT_NEW_CRC, entryNewCrc.getFormat());

        CpioArchiveEntry entryOldAscii = new CpioArchiveEntry(CpioConstants.FORMAT_OLD_ASCII);
        assertEquals(CpioConstants.FORMAT_OLD_ASCII, entryOldAscii.getFormat());

        CpioArchiveEntry entryOldBinary = new CpioArchiveEntry(CpioConstants.FORMAT_OLD_BINARY);
        assertEquals(CpioConstants.FORMAT_OLD_BINARY, entryOldBinary.getFormat());
    }

    @Test
    public void testFileFormatInitializationWithName() {
        String name = "testfile";
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW, name);
        assertEquals(CpioConstants.FORMAT_NEW, entry.getFormat());
        assertEquals(name, entry.getName());
    }

    @Test
    public void testFileFormatInitializationWithNameAndSize() {
        String name = "testfile";
        long size = 1024L;
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW, name, size);
        assertEquals(CpioConstants.FORMAT_NEW, entry.getFormat());
        assertEquals(name, entry.getName());
        assertEquals(size, entry.getSize());
    }

    @Test
    public void testFileFormatInitializationWithFile() {
        File file = new File("testfile");
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW, file, "testfile");
        assertEquals(CpioConstants.FORMAT_NEW, entry.getFormat());
        assertEquals("testfile", entry.getName());
        assertEquals(file.length(), entry.getSize());
    }

    @Test
    public void testSetAndGetChksum() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long chksum = 12345L;
        entry.setChksum(chksum);
        assertEquals(chksum, entry.getChksum());
    }

    @Test
    public void testSetAndGetDevice() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_OLD_ASCII);
        long device = 12345L;
        entry.setDevice(device);
        assertEquals(device, entry.getDevice());
    }

    @Test
    public void testSetAndGetDeviceMaj() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long maj = 12345L;
        entry.setDeviceMaj(maj);
        assertEquals(maj, entry.getDeviceMaj());
    }

    @Test
    public void testSetAndGetDeviceMin() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long min = 12345L;
        entry.setDeviceMin(min);
        assertEquals(min, entry.getDeviceMin());
    }

    @Test
    public void testSetAndGetSize() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long size = 12345L;
        entry.setSize(size);
        assertEquals(size, entry.getSize());
    }

    @Test
    public void testSetAndGetGID() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long gid = 12345L;
        entry.setGID(gid);
        assertEquals(gid, entry.getGID());
    }

    @Test
    public void testSetAndGetInode() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long inode = 12345L;
        entry.setInode(inode);
        assertEquals(inode, entry.getInode());
    }

    @Test
    public void testSetAndGetMode() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long mode = CpioConstants.C_ISREG;
        entry.setMode(mode);
        assertEquals(mode, entry.getMode());
    }

    @Test
    public void testSetAndGetName() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        String name = "testfile";
        entry.setName(name);
        assertEquals(name, entry.getName());
    }

    @Test
    public void testSetAndGetNumberOfLinks() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long nlink = 2L;
        entry.setNumberOfLinks(nlink);
        assertEquals(nlink, entry.getNumberOfLinks());
    }

    @Test
    public void testSetAndGetRemoteDevice() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_OLD_ASCII);
        long device = 12345L;
        entry.setRemoteDevice(device);
        assertEquals(device, entry.getRemoteDevice());
    }

    @Test
    public void testSetAndGetRemoteDeviceMaj() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long rmaj = 12345L;
        entry.setRemoteDeviceMaj(rmaj);
        assertEquals(rmaj, entry.getRemoteDeviceMaj());
    }

    @Test
    public void testSetAndGetRemoteDeviceMin() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long rmin = 12345L;
        entry.setRemoteDeviceMin(rmin);
        assertEquals(rmin, entry.getRemoteDeviceMin());
    }

    @Test
    public void testSetAndGetTime() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long time = 12345L;
        entry.setTime(time);
        assertEquals(time, entry.getTime());
    }

    @Test
    public void testSetAndGetUID() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        long uid = 12345L;
        entry.setUID(uid);
        assertEquals(uid, entry.getUID());
    }
}