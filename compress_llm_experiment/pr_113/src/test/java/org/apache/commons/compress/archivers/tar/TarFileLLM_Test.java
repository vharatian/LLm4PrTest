package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TarFileLLM_Test {

    @Test
    public void testSparseEntryReading() throws IOException {
        try (final TarFile tarFile = new TarFile(getPath("sparse-entry.tar"))) {
            final List<TarArchiveEntry> entries = tarFile.getEntries();
            assertEquals(1, entries.size());
            final TarArchiveEntry entry = entries.get(0);
            assertTrue(entry.isSparse());
            try (final InputStream input = tarFile.getInputStream(entry)) {
                byte[] content = IOUtils.toByteArray(input);
                assertEquals(entry.getRealSize(), content.length);
            }
        }
    }

    @Test
    public void testGlobalPaxHeaders() throws IOException {
        try (final TarFile tarFile = new TarFile(getPath("global-pax-headers.tar"))) {
            final List<TarArchiveEntry> entries = tarFile.getEntries();
            assertEquals(2, entries.size());
            final TarArchiveEntry entry = entries.get(1);
            assertEquals("file.txt", entry.getName());
            assertEquals("value", entry.getExtraPaxHeaders().get("key"));
        }
    }

    @Test
    public void testLongNameEntry() throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final String longName = "1234567890123456789012345678901234567890123456789012345678901234567890"
                + "1234567890123456789012345678901234567890123456789012345678901234567890"
                + "1234567890123456789012345678901234567890123456789012345678901234567890"
                + "1234567890123456789012345678901234567890.txt";
        try (final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
            TarArchiveEntry t = new TarArchiveEntry(longName);
            t.setSize(1);
            tos.putArchiveEntry(t);
            tos.write(30);
            tos.closeArchiveEntry();
        }
        final byte[] data = bos.toByteArray();
        try (final TarFile tarFile = new TarFile(data)) {
            List<TarArchiveEntry> entries = tarFile.getEntries();
            assertEquals(1, entries.size());
            assertEquals(longName, entries.get(0).getName());
        }
    }

    private Path getPath(String resource) {
        return new File("src/test/resources/" + resource).toPath();
    }
}