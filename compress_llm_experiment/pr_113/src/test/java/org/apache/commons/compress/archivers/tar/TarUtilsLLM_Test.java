package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TarUtilsLLM_Test {

    @Test
    public void testParsePaxHeadersWithSparseHeaders() throws Exception {
        String paxHeader = "30 GNU.sparse.offset=12345\n30 GNU.sparse.numbytes=67890\n";
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        Map<String, String> globalPaxHeaders = new HashMap<>();
        Map<String, String> headers = TarUtils.parsePaxHeaders(
                new ByteArrayInputStream(paxHeader.getBytes(StandardCharsets.UTF_8)),
                sparseHeaders, globalPaxHeaders);

        assertEquals(0, headers.size());
        assertEquals(1, sparseHeaders.size());
        assertEquals(12345, sparseHeaders.get(0).getOffset());
        assertEquals(67890, sparseHeaders.get(0).getNumbytes());
    }

    @Test
    public void testParsePAX01SparseHeaders() {
        String sparseMap = "12345,67890,54321,9876";
        List<TarArchiveStructSparse> sparseHeaders = TarUtils.parsePAX01SparseHeaders(sparseMap);

        assertEquals(2, sparseHeaders.size());
        assertEquals(12345, sparseHeaders.get(0).getOffset());
        assertEquals(67890, sparseHeaders.get(0).getNumbytes());
        assertEquals(54321, sparseHeaders.get(1).getOffset());
        assertEquals(9876, sparseHeaders.get(1).getNumbytes());
    }

    @Test
    public void testParsePAX1XSparseHeaders() throws Exception {
        String sparseHeaderData = "2\n12345\n67890\n54321\n9876\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(sparseHeaderData.getBytes(StandardCharsets.UTF_8));
        List<TarArchiveStructSparse> sparseHeaders = TarUtils.parsePAX1XSparseHeaders(inputStream, 512);

        assertEquals(2, sparseHeaders.size());
        assertEquals(12345, sparseHeaders.get(0).getOffset());
        assertEquals(67890, sparseHeaders.get(0).getNumbytes());
        assertEquals(54321, sparseHeaders.get(1).getOffset());
        assertEquals(9876, sparseHeaders.get(1).getNumbytes());
    }

    @Test
    public void testReadLineOfNumberForPax1X() throws Exception {
        String numberData = "12345\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(numberData.getBytes(StandardCharsets.UTF_8));
        long[] result = TarUtils.readLineOfNumberForPax1X(inputStream);

        assertEquals(12345, result[0]);
        assertEquals(6, result[1]); // 5 digits + 1 newline
    }

    @Test(expected = IOException.class)
    public void testReadLineOfNumberForPax1XUnexpectedEOF() throws Exception {
        String numberData = "12345";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(numberData.getBytes(StandardCharsets.UTF_8));
        TarUtils.readLineOfNumberForPax1X(inputStream);
    }

    @Test(expected = IOException.class)
    public void testParsePaxHeadersInvalidLength() throws Exception {
        String paxHeader = "invalid_length keyword=value\n";
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        Map<String, String> globalPaxHeaders = new HashMap<>();
        TarUtils.parsePaxHeaders(
                new ByteArrayInputStream(paxHeader.getBytes(StandardCharsets.UTF_8)),
                sparseHeaders, globalPaxHeaders);
    }

    @Test(expected = IOException.class)
    public void testParsePaxHeadersInvalidSparseHeaders() throws Exception {
        String paxHeader = "30 GNU.sparse.numbytes=67890\n";
        List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
        Map<String, String> globalPaxHeaders = new HashMap<>();
        TarUtils.parsePaxHeaders(
                new ByteArrayInputStream(paxHeader.getBytes(StandardCharsets.UTF_8)),
                sparseHeaders, globalPaxHeaders);
    }
}