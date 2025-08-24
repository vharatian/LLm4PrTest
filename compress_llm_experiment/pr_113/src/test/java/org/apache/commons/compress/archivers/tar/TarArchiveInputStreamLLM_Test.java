package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void testReadGlobalPaxHeaders() throws Exception {
        try (TarArchiveInputStream tar = getTestStream("/COMPRESS-530.tar")) {
            tar.getNextTarEntry();
            Map<String, String> globalPaxHeaders = tar.globalPaxHeaders;
            assertEquals("value1", globalPaxHeaders.get("key1"));
            assertEquals("value2", globalPaxHeaders.get("key2"));
        } catch (final IOException e) {
            fail("Exception: " + e.getMessage());
        }
    }

    @Test
    public void testPaxHeaders() throws Exception {
        try (TarArchiveInputStream tar = getTestStream("/COMPRESS-530.tar")) {
            tar.getNextTarEntry();
            List<TarArchiveStructSparse> sparseHeaders = new ArrayList<>();
            Map<String, String> headers = TarUtils.parsePaxHeaders(tar, sparseHeaders, tar.globalPaxHeaders);
            assertEquals("value1", headers.get("key1"));
            assertEquals("value2", headers.get("key2"));
            assertEquals(2, sparseHeaders.size());
        } catch (final IOException e) {
            fail("Exception: " + e.getMessage());
        }
    }

    @Test
    public void testParsePAX01SparseHeaders() throws Exception {
        String sparseMap = "0,512,1024,512";
        List<TarArchiveStructSparse> sparseHeaders = TarUtils.parsePAX01SparseHeaders(sparseMap);
        assertEquals(2, sparseHeaders.size());
        assertEquals(0, sparseHeaders.get(0).getOffset());
        assertEquals(512, sparseHeaders.get(0).getNumbytes());
        assertEquals(1024, sparseHeaders.get(1).getOffset());
        assertEquals(512, sparseHeaders.get(1).getNumbytes());
    }

    @Test
    public void testParsePAX1XSparseHeaders() throws Exception {
        byte[] data = "1\n0\n512\n".getBytes();
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            List<TarArchiveStructSparse> sparseHeaders = TarUtils.parsePAX1XSparseHeaders(inputStream, 512);
            assertEquals(1, sparseHeaders.size());
            assertEquals(0, sparseHeaders.get(0).getOffset());
            assertEquals(512, sparseHeaders.get(0).getNumbytes());
        }
    }

    private TarArchiveInputStream getTestStream(final String name) {
        return new TarArchiveInputStream(
            TarArchiveInputStreamTest2.class.getResourceAsStream(name));
    }
}