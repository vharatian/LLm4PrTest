package org.apache.commons.compress.archivers.tar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TarConstantsLLM_Test {

    @Test
    public void testVersionGnuSpace() {
        assertEquals(" \0", TarConstants.VERSION_GNU_SPACE, "VERSION_GNU_SPACE should be ' \\0'");
    }

    @Test
    public void testVersionGnuZero() {
        assertEquals("0\0", TarConstants.VERSION_GNU_ZERO, "VERSION_GNU_ZERO should be '0\\0'");
    }
}