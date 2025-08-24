package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.compress.utils.ZipSplitReadOnlySeekableByteChannel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import shaded.org.apache.commons.io.FileUtils;
import shaded.org.apache.commons.io.FilenameUtils;

public class ZipFileLLM_Test {
    private ZipFile zf = null;

    @After
    public void tearDown() {
        ZipFile.closeQuietly(zf);
    }

    @Test
    public void testIsSplitZipArchive() throws Exception {
        File lastFile = getFile("COMPRESS-477/split_zip_created_by_zip/split_zip_created_by_zip.zip");
        SeekableByteChannel splitInputStream = ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(lastFile);
        zf = new ZipFile(splitInputStream);
        assertTrue(zf.isSplitZipArchive());
    }

    @Test
    public void testSetSizesAndOffsetFromZip64Extra() throws Exception {
        File archive = getFile("COMPRESS-228.zip");
        zf = new ZipFile(archive);
        ZipArchiveEntry ze = zf.getEntry("src/main/java/org/apache/commons/compress/archivers/zip/ZipFile.java");
        assertEquals(26101, ze.getSize());
        assertEquals(26101, ze.getCompressedSize());
        assertEquals(0, ze.getDiskNumberStart());
    }

    @Test
    public void testPositionAtCentralDirectory64() throws Exception {
        File lastFile = getFile("COMPRESS-477/split_zip_created_by_zip/split_zip_created_by_zip_zip64.zip");
        SeekableByteChannel splitInputStream = ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(lastFile);
        zf = new ZipFile(splitInputStream);
        zf.positionAtCentralDirectory64();
        assertNotNull(zf.getEntries());
    }

    @Test
    public void testPositionAtCentralDirectory32() throws Exception {
        File lastFile = getFile("COMPRESS-477/split_zip_created_by_zip/split_zip_created_by_zip.zip");
        SeekableByteChannel splitInputStream = ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(lastFile);
        zf = new ZipFile(splitInputStream);
        zf.positionAtCentralDirectory32();
        assertNotNull(zf.getEntries());
    }

    @Test
    public void testSetDataOffsetForSplitZip() throws Exception {
        File lastFile = getFile("COMPRESS-477/split_zip_created_by_zip/split_zip_created_by_zip.zip");
        SeekableByteChannel splitInputStream = ZipSplitReadOnlySeekableByteChannel.buildFromLastSplitSegment(lastFile);
        zf = new ZipFile(splitInputStream);
        ZipArchiveEntry ze = zf.getEntry("commons-compress/src/main/java/org/apache/commons/compress/archivers/dump/UnsupportedCompressionAlgorithmException.java");
        zf.setDataOffset(ze);
        assertNotEquals(ZipArchiveEntry.OFFSET_UNKNOWN, ze.getDataOffset());
    }

    @Test
    public void testOffsetComparator() throws Exception {
        File archive = getFile("mixed.zip");
        zf = new ZipFile(archive);
        List<ZipArchiveEntry> entries = Collections.list(zf.getEntries());
        Collections.sort(entries, zf.offsetComparator);
        assertTrue(entries.get(0).getLocalHeaderOffset() <= entries.get(1).getLocalHeaderOffset());
    }
}