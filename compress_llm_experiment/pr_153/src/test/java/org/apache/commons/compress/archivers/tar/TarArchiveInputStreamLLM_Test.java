package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.junit.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void testCanReadEntryDataWithNonSparseTarArchiveEntry() throws Exception {
        // Create a non-sparse TarArchiveEntry
        TarArchiveEntry entry = new TarArchiveEntry("test.txt");
        entry.setSize(100);

        // Create a TarArchiveInputStream
        InputStream is = new ByteArrayInputStream(new byte[1]);
        TarArchiveInputStream tais = new TarArchiveInputStream(is);

        // Test canReadEntryData method
        assertTrue(tais.canReadEntryData(entry));

        tais.close();
    }

    @Test
    public void testCanReadEntryDataWithSparseTarArchiveEntry() throws Exception {
        // Create a sparse TarArchiveEntry
        TarArchiveEntry entry = new TarArchiveEntry("sparsefile");
        entry.setSize(100);
        entry.setSparseHeaders(new ArrayList<>());
        entry.getSparseHeaders().add(new TarArchiveStructSparse(0, 10));
        entry.getSparseHeaders().add(new TarArchiveStructSparse(20, 10));

        // Create a TarArchiveInputStream
        InputStream is = new ByteArrayInputStream(new byte[1]);
        TarArchiveInputStream tais = new TarArchiveInputStream(is);

        // Test canReadEntryData method
        assertTrue(tais.canReadEntryData(entry));

        tais.close();
    }

    @Test
    public void testCanReadEntryDataWithNonTarArchiveEntry() throws Exception {
        // Create a non-TarArchiveEntry
        ArchiveEntry entry = new ArchiveEntry() {
            @Override
            public String getName() {
                return "nonTarEntry";
            }

            @Override
            public long getSize() {
                return 100;
            }

            @Override
            public boolean isDirectory() {
                return false;
            }
        };

        // Create a TarArchiveInputStream
        InputStream is = new ByteArrayInputStream(new byte[1]);
        TarArchiveInputStream tais = new TarArchiveInputStream(is);

        // Test canReadEntryData method
        assertFalse(tais.canReadEntryData(entry));

        tais.close();
    }
}