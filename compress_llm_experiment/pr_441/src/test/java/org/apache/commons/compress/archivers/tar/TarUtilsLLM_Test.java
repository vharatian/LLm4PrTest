package org.apache.commons.compress.archivers.tar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.ParsingUtils;
import org.junit.jupiter.api.Test;

public class TarUtilsLLM_Test {

    @Test
    public void testParseFromPAX01SparseHeadersWithParsingUtils() throws Exception {
        final String map = "0,10,20,0,20,5";
        final List<TarArchiveStructSparse> sparse = TarUtils.parseFromPAX01SparseHeaders(map);
        assertEquals(3, sparse.size());
        assertEquals(0, sparse.get(0).getOffset());
        assertEquals(10, sparse.get(0).getNumbytes());
        assertEquals(20, sparse.get(1).getOffset());
        assertEquals(0, sparse.get(1).getNumbytes());
        assertEquals(20, sparse.get(2).getOffset());
        assertEquals(5, sparse.get(2).getNumbytes());
    }

    @Test
    public void testParseFromPAX01SparseHeadersRejectsNegativeNumbytesWithParsingUtils() throws Exception {
        assertThrows(IOException.class, () -> TarUtils.parseFromPAX01SparseHeaders("0,10,20,0,20,-5"));
    }

    @Test
    public void testParseFromPAX01SparseHeadersRejectsNegativeOffsetWithParsingUtils() throws Exception {
        assertThrows(IOException.class, () -> TarUtils.parseFromPAX01SparseHeaders("0,10,20,0,-2,5"));
    }

    @Test
    public void testParseFromPAX01SparseHeadersRejectsNonNumericNumbytesWithParsingUtils() throws Exception {
        assertThrows(IOException.class, () -> TarUtils.parseFromPAX01SparseHeaders("0,10,20,0,20,b"));
    }

    @Test
    public void testParseFromPAX01SparseHeadersRejectsNonNumericOffsetWithParsingUtils() throws Exception {
        assertThrows(IOException.class, () -> TarUtils.parseFromPAX01SparseHeaders("0,10,20,0,2a,5"));
    }

    @Test
    public void testParsePaxHeadersWithParsingUtils() throws Exception {
        final String header = "23 GNU.sparse.offset=0\n26 GNU.sparse.numbytes=10\n";
        final List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        TarUtils.parsePaxHeaders(new ByteArrayInputStream(header.getBytes(UTF_8)), sparseHeaders, Collections.emptyMap());
        assertEquals(1, sparseHeaders.size());
        assertEquals(0, sparseHeaders.get(0).getOffset());
        assertEquals(10, sparseHeaders.get(0).getNumbytes());
    }

    @Test
    public void testParsePaxHeadersRejectsNegativeNumbytesWithParsingUtils() throws Exception {
        final String header = "23 GNU.sparse.offset=0\n26 GNU.sparse.numbytes=-1\n";
        assertThrows(IOException.class, () -> TarUtils.parsePaxHeaders(new ByteArrayInputStream(header.getBytes(UTF_8)), null, Collections.emptyMap()));
    }

    @Test
    public void testParsePaxHeadersRejectsNegativeOffsetWithParsingUtils() throws Exception {
        final String header = "24 GNU.sparse.offset=-1\n26 GNU.sparse.numbytes=10\n";
        assertThrows(IOException.class, () -> TarUtils.parsePaxHeaders(new ByteArrayInputStream(header.getBytes(UTF_8)), null, Collections.emptyMap()));
    }

    @Test
    public void testParsePaxHeadersRejectsNonNumericNumbytesWithParsingUtils() throws Exception {
        final String header = "23 GNU.sparse.offset=0\n26 GNU.sparse.numbytes=1a\n";
        assertThrows(IOException.class, () -> TarUtils.parsePaxHeaders(new ByteArrayInputStream(header.getBytes(UTF_8)), null, Collections.emptyMap()));
    }

    @Test
    public void testParsePaxHeadersRejectsNonNumericOffsetWithParsingUtils() throws Exception {
        final String header = "23 GNU.sparse.offset=a\n26 GNU.sparse.numbytes=10\n";
        assertThrows(IOException.class, () -> TarUtils.parsePaxHeaders(new ByteArrayInputStream(header.getBytes(UTF_8)), null, Collections.emptyMap()));
    }
}