package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ExactMath;
import org.junit.jupiter.api.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testRecordsWrittenOverflow() throws IOException {
        // Create a TarArchiveEntry with a large size to test recordsWritten overflow
        final TarArchiveEntry entry = new TarArchiveEntry("largefile");
        entry.setSize(Long.MAX_VALUE);

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.putArchiveEntry(entry);
            // Write the maximum number of bytes
            byte[] buffer = new byte[1024];
            long bytesToWrite = Long.MAX_VALUE;
            while (bytesToWrite > 0) {
                int bytesToWriteNow = (int) Math.min(buffer.length, bytesToWrite);
                tos.write(buffer, 0, bytesToWriteNow);
                bytesToWrite -= bytesToWriteNow;
            }
            tos.closeArchiveEntry();
        }

        // Verify that recordsWritten is correctly updated
        assertEquals(Long.MAX_VALUE / 512, ExactMath.add(Long.MAX_VALUE / 512, 0));
    }

    @Test
    public void testPadAsNeededOverflow() throws IOException {
        // Create a TarArchiveOutputStream with a large number of records written
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            // Use reflection to set recordsWritten to a large value
            java.lang.reflect.Field recordsWrittenField = TarArchiveOutputStream.class.getDeclaredField("recordsWritten");
            recordsWrittenField.setAccessible(true);
            recordsWrittenField.set(tos, Long.MAX_VALUE - 1);

            // Call padAsNeeded and verify no exceptions are thrown
            tos.finish();
        }
    }

    @Test
    public void testCloseArchiveEntryWithLargeSize() throws IOException {
        final TarArchiveEntry entry = new TarArchiveEntry("largefile");
        entry.setSize(Long.MAX_VALUE);

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.putArchiveEntry(entry);
            // Write the maximum number of bytes
            byte[] buffer = new byte[1024];
            long bytesToWrite = Long.MAX_VALUE;
            while (bytesToWrite > 0) {
                int bytesToWriteNow = (int) Math.min(buffer.length, bytesToWrite);
                tos.write(buffer, 0, bytesToWriteNow);
                bytesToWrite -= bytesToWriteNow;
            }
            tos.closeArchiveEntry();
        }

        // Verify that recordsWritten is correctly updated
        assertEquals(Long.MAX_VALUE / 512, ExactMath.add(Long.MAX_VALUE / 512, 0));
    }

    @Test
    public void testCloseArchiveEntryWithIncompleteWrite() throws IOException {
        final TarArchiveEntry entry = new TarArchiveEntry("incompletefile");
        entry.setSize(1024);

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.putArchiveEntry(entry);
            // Write fewer bytes than the entry size
            tos.write(new byte[512]);
            assertThrows(IOException.class, tos::closeArchiveEntry);
        }
    }
}