package org.apache.commons.compress.archivers.cpio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpioArchiveEntryLLM_Test {

    @Test
    public void testDefaultValues() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        assertEquals(0, entry.getChksum());
        assertEquals(0, entry.getSize());
        assertEquals(0, entry.getGID());
        assertEquals(0, entry.getInode());
        assertEquals(0, entry.getDeviceMaj());
        assertEquals(0, entry.getDeviceMin());
        assertEquals(0, entry.getMode());
        assertEquals(0, entry.getTime());
        assertEquals(0, entry.getNumberOfLinks());
        assertEquals(0, entry.getRemoteDeviceMaj());
        assertEquals(0, entry.getRemoteDeviceMin());
        assertEquals(0, entry.getUID());
    }

    @Test
    public void testSetAndGetChksum() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setChksum(12345L);
        assertEquals(12345L, entry.getChksum());
    }

    @Test
    public void testSetAndGetSize() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setSize(12345L);
        assertEquals(12345L, entry.getSize());
    }

    @Test
    public void testSetAndGetGID() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setGID(12345L);
        assertEquals(12345L, entry.getGID());
    }

    @Test
    public void testSetAndGetInode() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setInode(12345L);
        assertEquals(12345L, entry.getInode());
    }

    @Test
    public void testSetAndGetDeviceMaj() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setDeviceMaj(12345L);
        assertEquals(12345L, entry.getDeviceMaj());
    }

    @Test
    public void testSetAndGetDeviceMin() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setDeviceMin(12345L);
        assertEquals(12345L, entry.getDeviceMin());
    }

    @Test
    public void testSetAndGetMode() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setMode(12345L);
        assertEquals(12345L, entry.getMode());
    }

    @Test
    public void testSetAndGetTime() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setTime(12345L);
        assertEquals(12345L, entry.getTime());
    }

    @Test
    public void testSetAndGetNumberOfLinks() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setNumberOfLinks(12345L);
        assertEquals(12345L, entry.getNumberOfLinks());
    }

    @Test
    public void testSetAndGetRemoteDeviceMaj() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setRemoteDeviceMaj(12345L);
        assertEquals(12345L, entry.getRemoteDeviceMaj());
    }

    @Test
    public void testSetAndGetRemoteDeviceMin() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setRemoteDeviceMin(12345L);
        assertEquals(12345L, entry.getRemoteDeviceMin());
    }

    @Test
    public void testSetAndGetUID() {
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        entry.setUID(12345L);
        assertEquals(12345L, entry.getUID());
    }
}