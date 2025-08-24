package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;

import org.apache.commons.compress.utils.ByteUtils;
import org.junit.jupiter.api.Test;

public class ZipArchiveEntryLLM_Test {

    @Test
    public void testSetAttributesFromFile() throws IOException {
        File inputFile = getFile("test.txt");
        ZipArchiveEntry entry = new ZipArchiveEntry(inputFile, "test.txt");
        BasicFileAttributes attributes = Files.readAttributes(inputFile.toPath(), BasicFileAttributes.class);
        assertEquals(attributes.size(), entry.getSize());
        assertEquals(attributes.lastModifiedTime().toMillis(), entry.getTime());
    }

    @Test
    public void testSetAttributesFromPath() throws IOException {
        Path inputPath = getFile("test.txt").toPath();
        ZipArchiveEntry entry = new ZipArchiveEntry(inputPath, "test.txt");
        BasicFileAttributes attributes = Files.readAttributes(inputPath, BasicFileAttributes.class);
        assertEquals(attributes.size(), entry.getSize());
        assertEquals(attributes.lastModifiedTime(), entry.getLastModifiedTime());
        assertEquals(attributes.creationTime(), entry.getCreationTime());
        assertEquals(attributes.lastAccessTime(), entry.getLastAccessTime());
    }

    @Test
    public void testSetTime() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        long currentTime = System.currentTimeMillis();
        entry.setTime(currentTime);
        assertEquals(currentTime, entry.getTime());
    }

    @Test
    public void testSetLastModifiedTime() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setLastModifiedTime(fileTime);
        assertEquals(fileTime, entry.getLastModifiedTime());
    }

    @Test
    public void testSetLastAccessTime() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setLastAccessTime(fileTime);
        assertEquals(fileTime, entry.getLastAccessTime());
    }

    @Test
    public void testSetCreationTime() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setCreationTime(fileTime);
        assertEquals(fileTime, entry.getCreationTime());
    }

    @Test
    public void testRemoveExtraField() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        AsiExtraField a = new AsiExtraField();
        a.setDirectory(true);
        a.setMode(0755);
        entry.addExtraField(a);
        assertEquals(1, entry.getExtraFields().length);
        entry.removeExtraField(a.getHeaderId());
        assertEquals(0, entry.getExtraFields().length);
    }

    @Test
    public void testRemoveUnparseableExtraFieldData() {
        ZipArchiveEntry entry = new ZipArchiveEntry("test.txt");
        UnparseableExtraFieldData u = new UnparseableExtraFieldData();
        entry.addExtraField(u);
        assertEquals(1, entry.getExtraFields().length);
        entry.removeUnparseableExtraFieldData();
        assertEquals(0, entry.getExtraFields().length);
    }

    @Test
    public void testEqualsWithTimeFields() {
        ZipArchiveEntry entry1 = new ZipArchiveEntry("test.txt");
        ZipArchiveEntry entry2 = new ZipArchiveEntry("test.txt");
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry1.setLastModifiedTime(fileTime);
        entry2.setLastModifiedTime(fileTime);
        entry1.setLastAccessTime(fileTime);
        entry2.setLastAccessTime(fileTime);
        entry1.setCreationTime(fileTime);
        entry2.setCreationTime(fileTime);
        assertEquals(entry1, entry2);
    }

    @Test
    public void testNotEqualsWithDifferentTimeFields() {
        ZipArchiveEntry entry1 = new ZipArchiveEntry("test.txt");
        ZipArchiveEntry entry2 = new ZipArchiveEntry("test.txt");
        FileTime fileTime1 = FileTime.fromMillis(System.currentTimeMillis());
        FileTime fileTime2 = FileTime.fromMillis(System.currentTimeMillis() + 1000);
        entry1.setLastModifiedTime(fileTime1);
        entry2.setLastModifiedTime(fileTime2);
        assertNotEquals(entry1, entry2);
    }
}