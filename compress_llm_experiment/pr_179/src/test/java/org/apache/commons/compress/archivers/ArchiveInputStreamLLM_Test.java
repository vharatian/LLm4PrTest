package org.apache.commons.compress.archivers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArchiveInputStreamLLM_Test {

    @Test
    public void testBytesReadInitialization() {
        ArchiveInputStream archiveInputStream = new ArchiveInputStream() {
            @Override
            public ArchiveEntry getNextEntry() throws IOException {
                return null;
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return -1;
            }
        };
        
        // Ensure bytesRead is initialized to 0
        assertEquals(0, archiveInputStream.getBytesRead());
    }
}