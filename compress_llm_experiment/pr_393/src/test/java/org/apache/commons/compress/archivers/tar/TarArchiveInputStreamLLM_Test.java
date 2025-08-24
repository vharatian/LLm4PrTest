package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void testCorruptedStructSparseDetected() throws IOException {
        // Create a TAR entry with a corrupted sparse header
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            TarArchiveEntry entry = new TarArchiveEntry("test");
            entry.setSize(1024);
            tos.putArchiveEntry(entry);
            // Write a sparse header with a negative offset
            byte[] sparseHeader = new byte[512];
            sparseHeader[0] = 1; // Indicate sparse header
            sparseHeader[1] = -1; // Negative offset
            tos.write(sparseHeader);
            tos.closeArchiveEntry();
        }

        final byte[] data = bos.toByteArray();
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);

        // Verify that an IOException is thrown when reading the corrupted sparse header
        try (TarArchiveInputStream tis = new TarArchiveInputStream(bis)) {
            assertThrows(IOException.class, () -> {
                while (tis.getNextTarEntry() != null) {
                    IOUtils.toByteArray(tis);
                }
            });
        }
    }

    @Test
    public void testTryToConsumeSecondEOFRecord() throws IOException {
        // Create a TAR entry with only one EOF record
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            TarArchiveEntry entry = new TarArchiveEntry("test");
            entry.setSize(1024);
            tos.putArchiveEntry(entry);
            tos.write(new byte[1024]);
            tos.closeArchiveEntry();
            // Write only one EOF record
            tos.write(new byte[512]);
        }

        final byte[] data = bos.toByteArray();
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);

        // Verify that the stream rewinds correctly when only one EOF record is present
        try (TarArchiveInputStream tis = new TarArchiveInputStream(bis)) {
            while (tis.getNextTarEntry() != null) {
                IOUtils.toByteArray(tis);
            }
            // The stream should be at EOF after reading the single EOF record
            assertThrows(IOException.class, () -> tis.read());
        }
    }
}