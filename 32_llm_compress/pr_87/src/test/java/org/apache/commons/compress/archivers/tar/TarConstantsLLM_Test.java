package org.apache.commons.compress.archivers.tar;

import org.junit.Test;
import static org.junit.Assert.*;

public class TarConstantsLLM_Test {

    @Test
    public void testSparseOffsetLen() {
        assertEquals(12, TarConstants.SPARSE_OFFSET_LEN);
    }

    @Test
    public void testSparseNumbytesLen() {
        assertEquals(12, TarConstants.SPARSE_NUMBYTES_LEN);
    }

    @Test
    public void testSparseHeadersInOldGnuHeader() {
        assertEquals(4, TarConstants.SPARSE_HEADERS_IN_OLDGNU_HEADER);
    }

    @Test
    public void testSparseHeadersInExtensionHeader() {
        assertEquals(21, TarConstants.SPARSE_HEADERS_IN_EXTENSION_HEADER);
    }
}