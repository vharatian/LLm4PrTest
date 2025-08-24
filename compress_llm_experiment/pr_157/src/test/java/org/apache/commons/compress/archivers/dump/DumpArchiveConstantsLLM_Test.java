package org.apache.commons.compress.archivers.dump;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DumpArchiveConstantsLLM_Test {

    @Test
    public void testSegmentTypeFind() {
        assertEquals(DumpArchiveConstants.SEGMENT_TYPE.TAPE, DumpArchiveConstants.SEGMENT_TYPE.find(1));
        assertEquals(DumpArchiveConstants.SEGMENT_TYPE.INODE, DumpArchiveConstants.SEGMENT_TYPE.find(2));
        assertEquals(DumpArchiveConstants.SEGMENT_TYPE.BITS, DumpArchiveConstants.SEGMENT_TYPE.find(3));
        assertEquals(DumpArchiveConstants.SEGMENT_TYPE.ADDR, DumpArchiveConstants.SEGMENT_TYPE.find(4));
        assertEquals(DumpArchiveConstants.SEGMENT_TYPE.END, DumpArchiveConstants.SEGMENT_TYPE.find(5));
        assertEquals(DumpArchiveConstants.SEGMENT_TYPE.CLRI, DumpArchiveConstants.SEGMENT_TYPE.find(6));
        assertNull(DumpArchiveConstants.SEGMENT_TYPE.find(7));
    }

    @Test
    public void testCompressionTypeFind() {
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.ZLIB, DumpArchiveConstants.COMPRESSION_TYPE.find(0));
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.BZLIB, DumpArchiveConstants.COMPRESSION_TYPE.find(1));
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.LZO, DumpArchiveConstants.COMPRESSION_TYPE.find(2));
        assertNull(DumpArchiveConstants.COMPRESSION_TYPE.find(3));
    }
}