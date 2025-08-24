package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TarUtilsLLM_Test {

    @Test
    public void testFormatLongOctalBytesDocumentationCorrection() {
        // This test ensures that the documentation correction does not affect functionality
        final byte[] buffer = new byte[12];
        TarUtils.formatLongOctalBytes(123456789L, buffer, 0, buffer.length);
        assertEquals("11145401315 ", new String(buffer, UTF_8));
    }

    @Test
    public void testFormatOctalBytesDocumentationCorrection() {
        // This test ensures that the documentation correction does not affect functionality
        final byte[] buffer = new byte[12];
        TarUtils.formatOctalBytes(123456789L, buffer, 0, buffer.length);
        assertEquals("11145401315\0", new String(buffer, UTF_8));
    }

    @Test
    public void testParseOctalDocumentationCorrection() {
        // This test ensures that the documentation correction does not affect functionality
        final byte[] buffer = "11145401315 ".getBytes(UTF_8);
        long value = TarUtils.parseOctal(buffer, 0, buffer.length);
        assertEquals(123456789L, value);
    }

    @Test
    public void testParsePaxHeadersDocumentationCorrection() throws IOException {
        // This test ensures that the documentation correction does not affect functionality
        final String header = "11 foo=bar\n";
        final Map<String, String> headers = TarUtils.parsePaxHeaders(
            new ByteArrayInputStream(header.getBytes(UTF_8)),
            null, new HashMap<>()
        );
        assertEquals(1, headers.size());
        assertEquals("bar", headers.get("foo"));
    }

    @Test
    public void testParsePaxHeadersWithSparseHeadersDocumentationCorrection() throws IOException {
        // This test ensures that the documentation correction does not affect functionality
        final String header = "23 GNU.sparse.offset=0\n26 GNU.sparse.numbytes=10\n";
        final Map<String, String> headers = new HashMap<>();
        final Map<String, String> globalPaxHeaders = new HashMap<>();
        TarUtils.parsePaxHeaders(
            new ByteArrayInputStream(header.getBytes(UTF_8)),
            null, globalPaxHeaders, -1
        );
        assertEquals(0, headers.size());
    }
}