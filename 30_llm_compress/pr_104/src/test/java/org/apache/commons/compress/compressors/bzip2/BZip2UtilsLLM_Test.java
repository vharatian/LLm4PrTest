package org.apache.commons.compress.compressors.bzip2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BZip2UtilsLLM_Test {

    @Test
    public void testIsCompressedFilename() {
        assertTrue(BZip2Utils.isCompressedFilename("file.tar.bz2"));
        assertTrue(BZip2Utils.isCompressedFilename("file.tbz2"));
        assertTrue(BZip2Utils.isCompressedFilename("file.tbz"));
        assertTrue(BZip2Utils.isCompressedFilename("file.bz2"));
        assertTrue(BZip2Utils.isCompressedFilename("file.bz"));
        assertFalse(BZip2Utils.isCompressedFilename("file.tar"));
        assertFalse(BZip2Utils.isCompressedFilename("file.txt"));
    }

    @Test
    public void testGetUncompressedFilename() {
        assertEquals("file.tar", BZip2Utils.getUncompressedFilename("file.tar.bz2"));
        assertEquals("file.tar", BZip2Utils.getUncompressedFilename("file.tbz2"));
        assertEquals("file.tar", BZip2Utils.getUncompressedFilename("file.tbz"));
        assertEquals("file", BZip2Utils.getUncompressedFilename("file.bz2"));
        assertEquals("file", BZip2Utils.getUncompressedFilename("file.bz"));
        assertEquals("file.tar", BZip2Utils.getUncompressedFilename("file.tar"));
        assertEquals("file.txt", BZip2Utils.getUncompressedFilename("file.txt"));
    }

    @Test
    public void testGetCompressedFilename() {
        assertEquals("file.tar.bz2", BZip2Utils.getCompressedFilename("file.tar"));
        assertEquals("file.bz2", BZip2Utils.getCompressedFilename("file"));
        assertEquals("file.txt.bz2", BZip2Utils.getCompressedFilename("file.txt"));
    }
}