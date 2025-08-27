package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class FileSystemLLM_Test {

    @Test
    public void testNewReservedFileNamesWindows() {
        FileSystem fs = FileSystem.WINDOWS;
        // Test new reserved file names "CONIN$" and "CONOUT$"
        assertTrue(fs.isReservedFileName("CONIN$"));
        assertTrue(fs.isReservedFileName("CONOUT$"));
    }

    @Test
    public void testIsLegalFileNameWithNewReservedNames() {
        FileSystem fs = FileSystem.WINDOWS;
        // Ensure new reserved names are not considered legal file names
        assertFalse(fs.isLegalFileName("CONIN$"));
        assertFalse(fs.isLegalFileName("CONOUT$"));
    }
}