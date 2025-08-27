package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void testSparseHeadersSorting() throws Exception {
        // Create a TarArchiveEntry with sparse headers
        TarArchiveEntry entry = new TarArchiveEntry("test");
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        sparseHeaders.add(new TarArchiveStructSparse(10, 5));
        sparseHeaders.add(new TarArchiveStructSparse(5, 5));
        sparseHeaders.add(new TarArchiveStructSparse(15, 5));
        entry.setSparseHeaders(sparseHeaders);

        // Create a TarArchiveInputStream and set the current entry
        InputStream is = new ByteArrayInputStream(new byte[1]);
        TarArchiveInputStream tais = new TarArchiveInputStream(is);
        tais.setCurrentEntry(entry);

        // Invoke buildSparseInputStreams to trigger sorting
        tais.buildSparseInputStreams();

        // Verify the sparse headers are sorted by offset
        List<TarArchiveStructSparse> sortedSparseHeaders = entry.getSparseHeaders();
        assertEquals(5, sortedSparseHeaders.get(0).getOffset());
        assertEquals(10, sortedSparseHeaders.get(1).getOffset());
        assertEquals(15, sortedSparseHeaders.get(2).getOffset());

        tais.close();
    }
}