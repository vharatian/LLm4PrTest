package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.Deflater;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testIsZip64Required() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        ZipArchiveOutputStream zipOutputStream = new ZipArchiveOutputStream(new ByteArrayOutputStream());

        // Test for Zip64Mode.Always
        assertTrue(zipOutputStream.isZip64Required(entry, Zip64Mode.Always));

        // Test for Zip64Mode.AlwaysWithCompatibility
        assertTrue(zipOutputStream.isZip64Required(entry, Zip64Mode.AlwaysWithCompatibility));

        // Test for Zip64Mode.Never
        assertFalse(zipOutputStream.isZip64Required(entry, Zip64Mode.Never));
    }

    @Test
    public void testShouldAddZip64Extra() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        ZipArchiveOutputStream zipOutputStream = new ZipArchiveOutputStream(new ByteArrayOutputStream());

        // Test for Zip64Mode.Always
        assertTrue(zipOutputStream.shouldAddZip64Extra(entry, Zip64Mode.Always));

        // Test for Zip64Mode.AlwaysWithCompatibility
        assertTrue(zipOutputStream.shouldAddZip64Extra(entry, Zip64Mode.AlwaysWithCompatibility));

        // Test for Zip64Mode.Never
        assertFalse(zipOutputStream.shouldAddZip64Extra(entry, Zip64Mode.Never));
    }

    @Test
    public void testCreateCentralFileHeader() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        ZipArchiveOutputStream zipOutputStream = new ZipArchiveOutputStream(new ByteArrayOutputStream());

        // Test for Zip64Mode.Always
        zipOutputStream.setUseZip64(Zip64Mode.Always);
        byte[] header = zipOutputStream.createCentralFileHeader(entry);
        assertNotNull(header);

        // Test for Zip64Mode.AlwaysWithCompatibility
        zipOutputStream.setUseZip64(Zip64Mode.AlwaysWithCompatibility);
        header = zipOutputStream.createCentralFileHeader(entry);
        assertNotNull(header);

        // Test for Zip64Mode.Never
        zipOutputStream.setUseZip64(Zip64Mode.Never);
        header = zipOutputStream.createCentralFileHeader(entry);
        assertNotNull(header);
    }

    @Test
    public void testHandleZip64Extra() throws IOException {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        ZipArchiveOutputStream zipOutputStream = new ZipArchiveOutputStream(new ByteArrayOutputStream());

        // Test for Zip64Mode.Always
        zipOutputStream.setUseZip64(Zip64Mode.Always);
        zipOutputStream.handleZip64Extra(entry, 0, true);
        assertNotNull(entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID));

        // Test for Zip64Mode.AlwaysWithCompatibility
        zipOutputStream.setUseZip64(Zip64Mode.AlwaysWithCompatibility);
        zipOutputStream.handleZip64Extra(entry, 0, true);
        assertNotNull(entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID));

        // Test for Zip64Mode.Never
        zipOutputStream.setUseZip64(Zip64Mode.Never);
        zipOutputStream.handleZip64Extra(entry, 0, false);
        assertNull(entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID));
    }
}