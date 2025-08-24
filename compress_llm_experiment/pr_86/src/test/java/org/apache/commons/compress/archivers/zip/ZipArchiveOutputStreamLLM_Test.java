package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipArchiveOutputStreamLLM_Test {

    @Test
    public void testSplitZipCreation() throws IOException {
        File tempFile = Files.createTempFile("splitZipTest", ".zip").toFile();
        tempFile.deleteOnExit();

        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, 1024 * 1024)) {
            ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
            entry.setSize(1024);
            entry.setCompressedSize(1024);
            entry.setCrc(123456789L);
            zos.putArchiveEntry(entry);
            zos.write(new byte[1024]);
            zos.closeArchiveEntry();
            zos.finish();
        }

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(tempFile.toPath()))) {
            ZipEntry entry = zis.getNextEntry();
            assertNotNull(entry);
            assertEquals("testEntry", entry.getName());
            assertEquals(1024, entry.getSize());
            assertEquals(123456789L, entry.getCrc());
        }
    }

    @Test
    public void testWriteCentralDirectoryEndForSplitZip() throws IOException {
        File tempFile = Files.createTempFile("splitZipTest", ".zip").toFile();
        tempFile.deleteOnExit();

        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, 1024 * 1024)) {
            ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
            entry.setSize(1024);
            entry.setCompressedSize(1024);
            entry.setCrc(123456789L);
            zos.putArchiveEntry(entry);
            zos.write(new byte[1024]);
            zos.closeArchiveEntry();
            zos.finish();
        }

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(tempFile.toPath()))) {
            ZipEntry entry = zis.getNextEntry();
            assertNotNull(entry);
            assertEquals("testEntry", entry.getName());
        }
    }

    @Test
    public void testWriteZip64CentralDirectoryForSplitZip() throws IOException {
        File tempFile = Files.createTempFile("splitZipTest", ".zip").toFile();
        tempFile.deleteOnExit();

        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, 1024 * 1024)) {
            ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
            entry.setSize(1024);
            entry.setCompressedSize(1024);
            entry.setCrc(123456789L);
            zos.putArchiveEntry(entry);
            zos.write(new byte[1024]);
            zos.closeArchiveEntry();
            zos.finish();
        }

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(tempFile.toPath()))) {
            ZipEntry entry = zis.getNextEntry();
            assertNotNull(entry);
            assertEquals("testEntry", entry.getName());
        }
    }

    @Test
    public void testAddRawArchiveEntryForSplitZip() throws IOException {
        File tempFile = Files.createTempFile("splitZipTest", ".zip").toFile();
        tempFile.deleteOnExit();

        try (ZipArchiveOutputStream zos = new ZipArchiveOutputStream(tempFile, 1024 * 1024)) {
            ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
            entry.setSize(1024);
            entry.setCompressedSize(1024);
            entry.setCrc(123456789L);
            zos.putArchiveEntry(entry);
            zos.write(new byte[1024]);
            zos.closeArchiveEntry();

            ZipArchiveEntry rawEntry = new ZipArchiveEntry("rawEntry");
            rawEntry.setSize(512);
            rawEntry.setCompressedSize(512);
            rawEntry.setCrc(987654321L);
            ByteArrayInputStream rawStream = new ByteArrayInputStream(new byte[512]);
            zos.addRawArchiveEntry(rawEntry, rawStream);
            zos.finish();
        }

        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(tempFile.toPath()))) {
            ZipEntry entry = zis.getNextEntry();
            assertNotNull(entry);
            assertEquals("testEntry", entry.getName());

            entry = zis.getNextEntry();
            assertNotNull(entry);
            assertEquals("rawEntry", entry.getName());
        }
    }
}