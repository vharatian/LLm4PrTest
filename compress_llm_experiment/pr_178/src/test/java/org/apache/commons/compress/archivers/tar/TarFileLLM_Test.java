package org.apache.commons.compress.archivers.tar;

import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TarFileLLM_Test {

    @Test
    public void testSparseHeaderComparator() throws IOException {
        // Create a TAR file with sparse headers in wrong order
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
            TarArchiveEntry entry = new TarArchiveEntry("sparse-file");
            entry.setSize(1024);
            entry.setSparse(true);
            entry.addSparseEntry(new TarArchiveStructSparse(512, 512));
            entry.addSparseEntry(new TarArchiveStructSparse(0, 512));
            tos.putArchiveEntry(entry);
            tos.write(new byte[1024]);
            tos.closeArchiveEntry();
        }

        final byte[] data = bos.toByteArray();
        try (final TarFile tarFile = new TarFile(data)) {
            List<TarArchiveEntry> entries = tarFile.getEntries();
            assertEquals(1, entries.size());
            TarArchiveEntry entry = entries.get(0);
            List<TarArchiveStructSparse> sparseHeaders = entry.getSparseHeaders();
            assertEquals(2, sparseHeaders.size());
            // Ensure the sparse headers are sorted correctly
            assertEquals(0, sparseHeaders.get(0).getOffset());
            assertEquals(512, sparseHeaders.get(1).getOffset());
        }
    }
}