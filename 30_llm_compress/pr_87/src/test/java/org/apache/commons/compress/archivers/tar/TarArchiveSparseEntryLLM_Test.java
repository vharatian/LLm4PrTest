package org.apache.commons.compress.archivers.tar;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TarArchiveSparseEntryLLM_Test {

    @Test
    public void testSparseHeadersInitialization() throws IOException {
        byte[] headerBuf = new byte[512];
        // Initialize headerBuf with test data
        // Assuming SPARSE_HEADERS_IN_EXTENSION_HEADER = 4, SPARSE_OFFSET_LEN = 12, SPARSE_NUMBYTES_LEN = 12
        // and SPARSELEN_GNU_SPARSE = 512 for this example
        headerBuf[0] = 1; // Set some data to simulate a sparse header
        headerBuf[24] = 1; // Set some data to simulate another sparse header

        TarArchiveSparseEntry entry = new TarArchiveSparseEntry(headerBuf);
        List<TarArchiveStructSparse> sparseHeaders = entry.getSparseHeaders();

        assertNotNull(sparseHeaders, "Sparse headers list should not be null");
        assertEquals(2, sparseHeaders.size(), "Sparse headers list size should be 2");
    }

    @Test
    public void testIsExtended() throws IOException {
        byte[] headerBuf = new byte[512];
        // Initialize headerBuf with test data
        // Assuming the boolean value is at offset 512
        headerBuf[512] = 1; // Set to true

        TarArchiveSparseEntry entry = new TarArchiveSparseEntry(headerBuf);
        assertTrue(entry.isExtended(), "isExtended should be true");
    }

    @Test
    public void testEmptySparseHeaders() throws IOException {
        byte[] headerBuf = new byte[512];
        // Initialize headerBuf with test data where sparse headers are empty
        // Assuming SPARSE_HEADERS_IN_EXTENSION_HEADER = 4, SPARSE_OFFSET_LEN = 12, SPARSE_NUMBYTES_LEN = 12
        // and SPARSELEN_GNU_SPARSE = 512 for this example

        TarArchiveSparseEntry entry = new TarArchiveSparseEntry(headerBuf);
        List<TarArchiveStructSparse> sparseHeaders = entry.getSparseHeaders();

        assertNotNull(sparseHeaders, "Sparse headers list should not be null");
        assertEquals(0, sparseHeaders.size(), "Sparse headers list size should be 0");
    }
}