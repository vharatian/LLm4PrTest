package org.apache.commons.compress.archivers.arj;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocalFileHeaderLLM_Test {

    @Test
    void testExtendedHeadersNotNull() {
        LocalFileHeader header = new LocalFileHeader();
        assertNotNull(header.extendedHeaders, "extendedHeaders should not be null");
    }

    @Test
    void testExtendedHeadersDefaultInitialization() {
        LocalFileHeader header = new LocalFileHeader();
        assertNull(header.extendedHeaders, "extendedHeaders should be null by default");
    }

    @Test
    void testExtendedHeadersEquality() {
        LocalFileHeader header1 = new LocalFileHeader();
        LocalFileHeader header2 = new LocalFileHeader();
        assertArrayEquals(header1.extendedHeaders, header2.extendedHeaders, "extendedHeaders should be equal by default");
    }

    @Test
    void testExtendedHeadersHashCode() {
        LocalFileHeader header = new LocalFileHeader();
        assertEquals(0, header.hashCode(), "hashCode should be 0 when name is null");
    }

    @Test
    void testExtendedHeadersToString() {
        LocalFileHeader header = new LocalFileHeader();
        String expectedString = "LocalFileHeader [archiverVersionNumber=0, minVersionToExtract=0, hostOS=0, arjFlags=0, method=0, fileType=0, reserved=0, dateTimeModified=0, compressedSize=0, originalSize=0, originalCrc32=0, fileSpecPosition=0, fileAccessMode=0, firstChapter=0, lastChapter=0, extendedFilePosition=0, dateTimeAccessed=0, dateTimeCreated=0, originalSizeEvenForVolumes=0, name=null, comment=null, extendedHeaders=null]";
        assertEquals(expectedString, header.toString(), "toString output should match expected string");
    }
}