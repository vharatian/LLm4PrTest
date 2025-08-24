package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ZipArchiveEntryLLM_Test {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDiskNumberStart() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        entry.setDiskNumberStart(5);
        assertEquals(5, entry.getDiskNumberStart());
    }

    @Test
    public void testSetDiskNumberStart() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        entry.setDiskNumberStart(10);
        assertEquals(10, entry.getDiskNumberStart());
    }

    @Test
    public void testDefaultDiskNumberStart() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test");
        assertEquals(0, entry.getDiskNumberStart());
    }
}