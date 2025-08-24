package org.apache.commons.compress.archivers.dump;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DumpArchiveConstantsLLM_Test {

    @Test
    public void testFindCompressionType() {
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.ZLIB, DumpArchiveConstants.COMPRESSION_TYPE.find(0));
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.BZLIB, DumpArchiveConstants.COMPRESSION_TYPE.find(1));
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.LZO, DumpArchiveConstants.COMPRESSION_TYPE.find(2));
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.UNKNOWN, DumpArchiveConstants.COMPRESSION_TYPE.find(-1));
        assertEquals(DumpArchiveConstants.COMPRESSION_TYPE.UNKNOWN, DumpArchiveConstants.COMPRESSION_TYPE.find(999));
    }
}