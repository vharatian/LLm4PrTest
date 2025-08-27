package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void readSimplePaxHeaderWithStandardCharsets() throws Exception {
        final InputStream is = new ByteArrayInputStream(new byte[1]);
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        final Map<String, String> headers = tais
            .parsePaxHeaders(new ByteArrayInputStream("30 atime=1321711775.972059463\n"
            .getBytes(StandardCharsets.UTF_8)), null);
        assertEquals(1, headers.size());
        assertEquals("1321711775.972059463", headers.get("atime"));
        tais.close();
    }

    @Test
    public void secondEntryWinsWhenPaxHeaderContainsDuplicateKeyWithStandardCharsets() throws Exception {
        final InputStream is = new ByteArrayInputStream(new byte[1]);
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        final Map<String, String> headers = tais
            .parsePaxHeaders(new ByteArrayInputStream("11 foo=bar\n11 foo=baz\n"
            .getBytes(StandardCharsets.UTF_8)), null);
        assertEquals(1, headers.size());
        assertEquals("baz", headers.get("foo"));
        tais.close();
    }

    @Test
    public void paxHeaderEntryWithEmptyValueRemovesKeyWithStandardCharsets() throws Exception {
        final InputStream is = new ByteArrayInputStream(new byte[1]);
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        final Map<String, String> headers = tais
            .parsePaxHeaders(new ByteArrayInputStream("11 foo=bar\n7 foo=\n"
            .getBytes(StandardCharsets.UTF_8)), null);
        assertEquals(0, headers.size());
        tais.close();
    }

    @Test
    public void readPaxHeaderWithEmbeddedNewlineWithStandardCharsets() throws Exception {
        final InputStream is = new ByteArrayInputStream(new byte[1]);
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        final Map<String, String> headers = tais
            .parsePaxHeaders(new ByteArrayInputStream("28 comment=line1\nline2\nand3\n"
            .getBytes(StandardCharsets.UTF_8)), null);
        assertEquals(1, headers.size());
        assertEquals("line1\nline2\nand3", headers.get("comment"));
        tais.close();
    }

    @Test
    public void readNonAsciiPaxHeaderWithStandardCharsets() throws Exception {
        final String ae = "\u00e4";
        final String line = "11 path="+ ae + "\n";
        assertEquals(11, line.getBytes(StandardCharsets.UTF_8).length);
        final InputStream is = new ByteArrayInputStream(new byte[1]);
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        final Map<String, String> headers = tais
            .parsePaxHeaders(new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8)), null);
        assertEquals(1, headers.size());
        assertEquals(ae, headers.get("path"));
        tais.close();
    }
}