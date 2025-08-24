package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;

public class TarConstantsLLM_Test {

    @Test
    public void testLF_OFFSET() {
        assertEquals(156, TarConstants.LF_OFFSET);
    }

    @Test
    public void testLF_MULTIVOLUME() {
        assertEquals((byte) 'M', TarConstants.LF_MULTIVOLUME);
    }

    @Test
    public void testXSTAR_MULTIVOLUME_OFFSET() {
        assertEquals(464, TarConstants.XSTAR_MULTIVOLUME_OFFSET);
    }

    @Test
    public void testXSTAR_PREFIX_OFFSET() {
        assertEquals(345, TarConstants.XSTAR_PREFIX_OFFSET);
    }

    @Test
    public void testXSTAR_ATIME_OFFSET() {
        assertEquals(476, TarConstants.XSTAR_ATIME_OFFSET);
    }

    @Test
    public void testXSTAR_CTIME_OFFSET() {
        assertEquals(488, TarConstants.XSTAR_CTIME_OFFSET);
    }
}