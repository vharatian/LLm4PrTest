package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;
import java.util.zip.ZipException;

import static org.apache.commons.compress.archivers.zip.ZipConstants.DATA_DESCRIPTOR_MIN_VERSION;
import static org.apache.commons.compress.archivers.zip.ZipConstants.DEFLATE_MIN_VERSION;
import static org.apache.commons.compress.archivers.zip.ZipConstants.DWORD;
import static org.apache.commons.compress.archivers.zip.ZipConstants.INITIAL_VERSION;
import static org.apache.commons.compress.archivers.zip.ZipConstants.SHORT;
import static org.apache.commons.compress.archivers.zip.ZipConstants.WORD;
import static org.apache.commons.compress.archivers.zip.ZipConstants.ZIP64_MAGIC;
import static org.apache.commons.compress.archivers.zip.ZipConstants.ZIP64_MAGIC_SHORT;
import static org.apache.commons.compress.archivers.zip.ZipConstants.ZIP64_MIN_VERSION;
import static org.apache.commons.compress.archivers.zip.ZipLong.putLong;
import static org.apache.commons.compress.archivers.zip.ZipShort.putShort;
import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    private ZipArchiveOutputStream zipArchiveOutputStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @BeforeEach
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        zipArchiveOutputStream = new ZipArchiveOutputStream(byteArrayOutputStream);
    }

    @Test
    public void testCreateCentralFileHeaderWithDiskNumberStart() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(1024);
        entry.setCompressedSize(512);
        entry.setDiskNumberStart(ZIP64_MAGIC_SHORT + 1);

        zipArchiveOutputStream.putArchiveEntry(entry);
        zipArchiveOutputStream.closeArchiveEntry();

        byte[] centralFileHeader = zipArchiveOutputStream.createCentralFileHeader(entry);
        assertNotNull(centralFileHeader);
        assertTrue(centralFileHeader.length > 0);
    }

    @Test
    public void testHandleZip64ExtraWithDiskNumberStart() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(1024);
        entry.setCompressedSize(512);
        entry.setDiskNumberStart(ZIP64_MAGIC_SHORT + 1);

        zipArchiveOutputStream.putArchiveEntry(entry);
        zipArchiveOutputStream.closeArchiveEntry();

        zipArchiveOutputStream.handleZip64Extra(entry, 0, true);
        assertNotNull(entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID));
    }

    @Test
    public void testWriteCentralDirectoryEndWithZip64Validation() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(1024);
        entry.setCompressedSize(512);

        zipArchiveOutputStream.putArchiveEntry(entry);
        zipArchiveOutputStream.closeArchiveEntry();

        zipArchiveOutputStream.writeCentralDirectoryEnd();
        byte[] output = byteArrayOutputStream.toByteArray();
        assertNotNull(output);
        assertTrue(output.length > 0);
    }

    @Test
    public void testValidateIfZip64IsNeededInEOCD() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(1024);
        entry.setCompressedSize(512);

        zipArchiveOutputStream.entries.add(entry);
        zipArchiveOutputStream.cdLength = ZIP64_MAGIC + 1;

        assertThrows(Zip64RequiredException.class, () -> zipArchiveOutputStream.validateIfZip64IsNeededInEOCD());
    }

    @Test
    public void testShouldUseZip64EOCD() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        entry.setSize(1024);
        entry.setCompressedSize(512);

        zipArchiveOutputStream.entries.add(entry);
        zipArchiveOutputStream.cdLength = ZIP64_MAGIC + 1;

        assertTrue(zipArchiveOutputStream.shouldUseZip64EOCD());
    }
}