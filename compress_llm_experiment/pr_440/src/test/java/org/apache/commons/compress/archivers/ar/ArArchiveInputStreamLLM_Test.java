package org.apache.commons.compress.archivers.ar;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class ArArchiveInputStreamLLM_Test {

    @Test
    public void testGetBSDLongNameThrowsIOExceptionOnInvalidNumber() throws Exception {
        byte[] invalidBSDLongName = ArchiveUtils.toAsciiBytes("#1/invalid");
        try (InputStream in = new ByteArrayInputStream(invalidBSDLongName);
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getBSDLongName("#1/invalid"));
        }
    }

    @Test
    public void testGetExtendedNameHandlesEmptyBuffer() throws Exception {
        byte[] emptyBuffer = new byte[0];
        try (InputStream in = new ByteArrayInputStream(emptyBuffer);
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            archive.namebuffer = emptyBuffer;
            assertThrows(IOException.class, () -> archive.getExtendedName(0));
        }
    }

    @Test
    public void testGetNextArEntryThrowsIOExceptionOnInvalidSize() throws Exception {
        byte[] invalidSizeMetaData = new byte[60];
        System.arraycopy(ArchiveUtils.toAsciiBytes("!<arch>\n"), 0, invalidSizeMetaData, 0, 8);
        System.arraycopy(ArchiveUtils.toAsciiBytes("file/           "), 0, invalidSizeMetaData, 8, 16);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1234567890  "), 0, invalidSizeMetaData, 24, 12);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1000  "), 0, invalidSizeMetaData, 36, 6);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1000  "), 0, invalidSizeMetaData, 42, 6);
        System.arraycopy(ArchiveUtils.toAsciiBytes("100644  "), 0, invalidSizeMetaData, 48, 8);
        System.arraycopy(ArchiveUtils.toAsciiBytes("invalidsize"), 0, invalidSizeMetaData, 56, 10);

        try (InputStream in = new ByteArrayInputStream(invalidSizeMetaData);
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextArEntry());
        }
    }

    @Test
    public void testReadGNUStringTableThrowsIOExceptionOnInvalidLength() throws Exception {
        byte[] invalidLengthMetaData = new byte[60];
        System.arraycopy(ArchiveUtils.toAsciiBytes("!<arch>\n"), 0, invalidLengthMetaData, 0, 8);
        System.arraycopy(ArchiveUtils.toAsciiBytes("/           "), 0, invalidLengthMetaData, 8, 16);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1234567890  "), 0, invalidLengthMetaData, 24, 12);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1000  "), 0, invalidLengthMetaData, 36, 6);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1000  "), 0, invalidLengthMetaData, 42, 6);
        System.arraycopy(ArchiveUtils.toAsciiBytes("100644  "), 0, invalidLengthMetaData, 48, 8);
        System.arraycopy(ArchiveUtils.toAsciiBytes("invalidlen"), 0, invalidLengthMetaData, 56, 10);

        try (InputStream in = new ByteArrayInputStream(invalidLengthMetaData);
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.readGNUStringTable(invalidLengthMetaData, 56, 10));
        }
    }

    @Test
    public void testGetNextArEntryThrowsIOExceptionOnInvalidUserId() throws Exception {
        byte[] invalidUserIdMetaData = new byte[60];
        System.arraycopy(ArchiveUtils.toAsciiBytes("!<arch>\n"), 0, invalidUserIdMetaData, 0, 8);
        System.arraycopy(ArchiveUtils.toAsciiBytes("file/           "), 0, invalidUserIdMetaData, 8, 16);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1234567890  "), 0, invalidUserIdMetaData, 24, 12);
        System.arraycopy(ArchiveUtils.toAsciiBytes("invalid"), 0, invalidUserIdMetaData, 36, 6);
        System.arraycopy(ArchiveUtils.toAsciiBytes("1000  "), 0, invalidUserIdMetaData, 42, 6);
        System.arraycopy(ArchiveUtils.toAsciiBytes("100644  "), 0, invalidUserIdMetaData, 48, 8);
        System.arraycopy(ArchiveUtils.toAsciiBytes("10"), 0, invalidUserIdMetaData, 56, 2);

        try (InputStream in = new ByteArrayInputStream(invalidUserIdMetaData);
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextArEntry());
        }
    }
}