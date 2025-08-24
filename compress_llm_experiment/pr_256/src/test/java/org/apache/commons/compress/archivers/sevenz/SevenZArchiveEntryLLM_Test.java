package org.apache.commons.compress.archivers.sevenz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.attribute.FileTime;
import java.util.Date;

import org.apache.commons.compress.utils.TimeUtils;
import org.junit.jupiter.api.Test;

public class SevenZArchiveEntryLLM_Test {

    @Test
    public void shouldThrowIfNoAccessTimeIsSet() {
        assertThrows(UnsupportedOperationException.class, () -> new SevenZArchiveEntry().getAccessTime());
    }

    @Test
    public void shouldThrowIfNoCreationTimeIsSet() {
        assertThrows(UnsupportedOperationException.class, () -> new SevenZArchiveEntry().getCreationTime());
    }

    @Test
    public void shouldThrowIfNoLastModifiedTimeIsSet() {
        assertThrows(UnsupportedOperationException.class, () -> new SevenZArchiveEntry().getLastModifiedTime());
    }

    @Test
    public void testSetAndGetCreationTime() {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setCreationTime(fileTime);
        assertEquals(fileTime, entry.getCreationTime());
    }

    @Test
    public void testSetAndGetLastModifiedTime() {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setLastModifiedTime(fileTime);
        assertEquals(fileTime, entry.getLastModifiedTime());
    }

    @Test
    public void testSetAndGetAccessTime() {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry.setAccessTime(fileTime);
        assertEquals(fileTime, entry.getAccessTime());
    }

    @Test
    public void testSetAndGetCreationDate() {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        Date date = new Date();
        entry.setCreationDate(date);
        assertEquals(TimeUtils.dateToFileTime(date), entry.getCreationTime());
    }

    @Test
    public void testSetAndGetLastModifiedDate() {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        Date date = new Date();
        entry.setLastModifiedDate(date);
        assertEquals(TimeUtils.dateToFileTime(date), entry.getLastModifiedTime());
    }

    @Test
    public void testSetAndGetAccessDate() {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        Date date = new Date();
        entry.setAccessDate(date);
        assertEquals(TimeUtils.dateToFileTime(date), entry.getAccessTime());
    }

    @Test
    public void testEqualsWithFileTime() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
        entry1.setCreationTime(fileTime);
        entry2.setCreationTime(fileTime);
        assertEquals(entry1, entry2);
    }

    @Test
    public void testNotEqualsWithDifferentFileTime() {
        SevenZArchiveEntry entry1 = new SevenZArchiveEntry();
        SevenZArchiveEntry entry2 = new SevenZArchiveEntry();
        entry1.setCreationTime(FileTime.fromMillis(System.currentTimeMillis()));
        entry2.setCreationTime(FileTime.fromMillis(System.currentTimeMillis() + 1000));
        assertNotEquals(entry1, entry2);
    }
}