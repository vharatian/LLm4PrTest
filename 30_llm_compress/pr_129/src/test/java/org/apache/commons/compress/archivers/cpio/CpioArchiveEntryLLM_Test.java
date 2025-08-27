package org.apache.commons.compress.archivers.cpio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpioArchiveEntryLLM_Test {

    @Test
    public void testOldFormatHeaderChange() {
        // This test is to ensure that the header change from <h3> to <h2> does not affect functionality
        // Since this is a documentation change, we do not need to test functionality
        // We will simply ensure that the class can be instantiated and used as expected

        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        assertNotNull(entry);
    }
}