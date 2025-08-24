package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.utils.TimeUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.attribute.FileTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class X5455_ExtendedTimestampLLM_Test {

    @Test
    public void testFileTimeToZipLong() {
        FileTime fileTime = FileTime.fromMillis(1000L);
        ZipLong zipLong = X5455_ExtendedTimestamp.fileTimeToZipLong(fileTime);
        assertNotNull(zipLong);
        assertEquals(1, zipLong.getValue());
    }

    @Test
    public void testUnixTimeToFileTime() {
        ZipLong zipLong = new ZipLong(1);
        FileTime fileTime = X5455_ExtendedTimestamp.unixTimeToFileTime(zipLong);
        assertNotNull(fileTime);
        assertEquals(1000L, fileTime.toMillis());
    }

    @Test
    public void testGetAccessFileTime() {
        X5455_ExtendedTimestamp xf = new X5455_ExtendedTimestamp();
        xf.setAccessJavaTime(new Date(1000L));
        FileTime fileTime = xf.getAccessFileTime();
        assertNotNull(fileTime);
        assertEquals(1000L, fileTime.toMillis());
    }

    @Test
    public void testGetCreateFileTime() {
        X5455_ExtendedTimestamp xf = new X5455_ExtendedTimestamp();
        xf.setCreateJavaTime(new Date(1000L));
        FileTime fileTime = xf.getCreateFileTime();
        assertNotNull(fileTime);
        assertEquals(1000L, fileTime.toMillis());
    }

    @Test
    public void testGetModifyFileTime() {
        X5455_ExtendedTimestamp xf = new X5455_ExtendedTimestamp();
        xf.setModifyJavaTime(new Date(1000L));
        FileTime fileTime = xf.getModifyFileTime();
        assertNotNull(fileTime);
        assertEquals(1000L, fileTime.toMillis());
    }

    @Test
    public void testSetAccessFileTime() {
        X5455_ExtendedTimestamp xf = new X5455_ExtendedTimestamp();
        FileTime fileTime = FileTime.fromMillis(1000L);
        xf.setAccessFileTime(fileTime);
        assertEquals(1000L, xf.getAccessJavaTime().getTime());
    }

    @Test
    public void testSetCreateFileTime() {
        X5455_ExtendedTimestamp xf = new X5455_ExtendedTimestamp();
        FileTime fileTime = FileTime.fromMillis(1000L);
        xf.setCreateFileTime(fileTime);
        assertEquals(1000L, xf.getCreateJavaTime().getTime());
    }

    @Test
    public void testSetModifyFileTime() {
        X5455_ExtendedTimestamp xf = new X5455_ExtendedTimestamp();
        FileTime fileTime = FileTime.fromMillis(1000L);
        xf.setModifyFileTime(fileTime);
        assertEquals(1000L, xf.getModifyJavaTime().getTime());
    }
}