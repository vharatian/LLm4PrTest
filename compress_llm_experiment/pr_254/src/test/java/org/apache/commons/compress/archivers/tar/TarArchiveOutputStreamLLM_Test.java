package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TarArchiveOutputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testAddFileTimePaxHeaderForBigNumber() throws Exception {
        final TarArchiveEntry t = new TarArchiveEntry("foo");
        t.setSize(10 * 1024);
        t.setLastModifiedTime(FileTime.from(Instant.now()));
        t.setLastAccessTime(FileTime.from(Instant.now()));
        t.setCreationTime(FileTime.from(Instant.now()));
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        tos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_POSIX);
        tos.putArchiveEntry(t);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();
        final byte[] data = bos.toByteArray();
        final TarArchiveInputStream tin = new TarArchiveInputStream(new ByteArrayInputStream(data));
        final TarArchiveEntry e = tin.getNextTarEntry();
        assertEquals(t.getLastModifiedTime().to(TimeUnit.SECONDS), e.getLastModifiedTime().to(TimeUnit.SECONDS));
        assertEquals(t.getLastAccessTime().to(TimeUnit.SECONDS), e.getLastAccessTime().to(TimeUnit.SECONDS));
        assertEquals(t.getCreationTime().to(TimeUnit.SECONDS), e.getCreationTime().to(TimeUnit.SECONDS));
        tin.close();
    }

    @Test
    public void testAddInstantPaxHeader() throws Exception {
        final TarArchiveEntry t = new TarArchiveEntry("foo");
        t.setSize(10 * 1024);
        t.setLastModifiedTime(FileTime.from(Instant.now()));
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        tos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_POSIX);
        tos.putArchiveEntry(t);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();
        final byte[] data = bos.toByteArray();
        final TarArchiveInputStream tin = new TarArchiveInputStream(new ByteArrayInputStream(data));
        final TarArchiveEntry e = tin.getNextTarEntry();
        assertEquals(t.getLastModifiedTime().to(TimeUnit.SECONDS), e.getLastModifiedTime().to(TimeUnit.SECONDS));
        tin.close();
    }

    @Test
    public void testTransferModTime() throws Exception {
        final TarArchiveEntry t = new TarArchiveEntry("foo");
        t.setSize(10 * 1024);
        t.setLastModifiedTime(FileTime.from(Instant.now()));
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        tos.putArchiveEntry(t);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();
        final byte[] data = bos.toByteArray();
        final TarArchiveInputStream tin = new TarArchiveInputStream(new ByteArrayInputStream(data));
        final TarArchiveEntry e = tin.getNextTarEntry();
        assertEquals(t.getLastModifiedTime().to(TimeUnit.SECONDS), e.getLastModifiedTime().to(TimeUnit.SECONDS));
        tin.close();
    }

    @Test
    public void testAddFileTimePaxHeader() throws Exception {
        final TarArchiveEntry t = new TarArchiveEntry("foo");
        t.setSize(10 * 1024);
        t.setLastModifiedTime(FileTime.from(Instant.now()));
        t.setLastAccessTime(FileTime.from(Instant.now()));
        t.setCreationTime(FileTime.from(Instant.now()));
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        tos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_POSIX);
        tos.putArchiveEntry(t);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();
        final byte[] data = bos.toByteArray();
        final TarArchiveInputStream tin = new TarArchiveInputStream(new ByteArrayInputStream(data));
        final TarArchiveEntry e = tin.getNextTarEntry();
        assertEquals(t.getLastModifiedTime().to(TimeUnit.SECONDS), e.getLastModifiedTime().to(TimeUnit.SECONDS));
        assertEquals(t.getLastAccessTime().to(TimeUnit.SECONDS), e.getLastAccessTime().to(TimeUnit.SECONDS));
        assertEquals(t.getCreationTime().to(TimeUnit.SECONDS), e.getCreationTime().to(TimeUnit.SECONDS));
        tin.close();
    }
}