package org.apache.commons.compress.archivers.tar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TarArchiveStructSparseLLM_Test {

    @Test
    public void testConstructorAndGetters() {
        long offset = 100L;
        long numbytes = 200L;
        TarArchiveStructSparse struct = new TarArchiveStructSparse(offset, numbytes);
        assertEquals(offset, struct.getOffset());
        assertEquals(numbytes, struct.getNumbytes());
    }

    @Test
    public void testSetters() {
        long offset = 100L;
        long numbytes = 200L;
        TarArchiveStructSparse struct = new TarArchiveStructSparse(offset, numbytes);
        
        long newOffset = 300L;
        long newNumbytes = 400L;
        struct.setOffset(newOffset);
        struct.setNumbytes(newNumbytes);
        
        assertEquals(newOffset, struct.getOffset());
        assertEquals(newNumbytes, struct.getNumbytes());
    }

    @Test
    public void testEquals() {
        TarArchiveStructSparse struct1 = new TarArchiveStructSparse(100L, 200L);
        TarArchiveStructSparse struct2 = new TarArchiveStructSparse(100L, 200L);
        TarArchiveStructSparse struct3 = new TarArchiveStructSparse(300L, 400L);
        
        assertTrue(struct1.equals(struct2));
        assertFalse(struct1.equals(struct3));
        assertFalse(struct1.equals(null));
        assertFalse(struct1.equals(new Object()));
    }

    @Test
    public void testHashCode() {
        TarArchiveStructSparse struct1 = new TarArchiveStructSparse(100L, 200L);
        TarArchiveStructSparse struct2 = new TarArchiveStructSparse(100L, 200L);
        TarArchiveStructSparse struct3 = new TarArchiveStructSparse(300L, 400L);
        
        assertEquals(struct1.hashCode(), struct2.hashCode());
        assertNotEquals(struct1.hashCode(), struct3.hashCode());
    }

    @Test
    public void testToString() {
        TarArchiveStructSparse struct = new TarArchiveStructSparse(100L, 200L);
        String expected = "TarArchiveStructSparse{offset=100, numbytes=200}";
        assertEquals(expected, struct.toString());
    }
}