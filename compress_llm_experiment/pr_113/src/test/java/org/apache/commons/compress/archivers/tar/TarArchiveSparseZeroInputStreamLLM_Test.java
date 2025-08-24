package org.apache.commons.compress.archivers.tar;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarArchiveSparseZeroInputStreamLLM_Test {

    @Test
    public void testReadReturnsZero() throws IOException {
        TarArchiveSparseZeroInputStream inputStream = new TarArchiveSparseZeroInputStream();
        assertEquals(0, inputStream.read(), "The read method should return 0");
    }

    @Test
    public void testSkipReturnsInputValue() {
        TarArchiveSparseZeroInputStream inputStream = new TarArchiveSparseZeroInputStream();
        long n = 10L;
        assertEquals(n, inputStream.skip(n), "The skip method should return the input value");
    }
}