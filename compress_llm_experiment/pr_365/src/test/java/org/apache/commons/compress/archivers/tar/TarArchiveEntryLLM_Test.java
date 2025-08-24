package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TarArchiveEntryLLM_Test {

    @Test
    public void testGetLinkFlag() {
        // Create a TarArchiveEntry with a specific link flag
        TarArchiveEntry entry = new TarArchiveEntry("test", TarConstants.LF_SYMLINK);
        // Verify that the getLinkFlag method returns the correct value
        assertEquals(TarConstants.LF_SYMLINK, entry.getLinkFlag());

        // Create another TarArchiveEntry with a different link flag
        entry = new TarArchiveEntry("test", TarConstants.LF_DIR);
        // Verify that the getLinkFlag method returns the correct value
        assertEquals(TarConstants.LF_DIR, entry.getLinkFlag());
    }
}