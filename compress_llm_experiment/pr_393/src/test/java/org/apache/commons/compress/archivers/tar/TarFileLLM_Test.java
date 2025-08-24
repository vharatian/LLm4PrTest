package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class TarFileLLM_Test {

    /**
     * Test to ensure that an IOException is thrown when a corrupted sparse header is detected.
     */
    @Test
    public void testCorruptedSparseHeader() {
        assertThrows(IOException.class, () -> {
            // Create a TarFile instance with a corrupted sparse header
            byte[] corruptedSparseHeader = new byte[] { /* corrupted sparse header bytes */ };
            try (TarFile tarFile = new TarFile(corruptedSparseHeader)) {
                // Attempt to build sparse input streams which should throw an IOException
                tarFile.buildSparseInputStreams();
            }
        });
    }

    /**
     * Test to ensure that an IOException is thrown when trying to move backwards inside the archive.
     */
    @Test
    public void testMoveBackwardsInsideArchive() {
        assertThrows(IOException.class, () -> {
            // Create a TarFile instance with an entry that requires moving backwards
            byte[] moveBackwardsArchive = new byte[] { /* bytes that simulate moving backwards inside the archive */ };
            try (TarFile tarFile = new TarFile(moveBackwardsArchive)) {
                // Attempt to read the next entry which should throw an IOException
                tarFile.getNextTarEntry();
            }
        });
    }
}