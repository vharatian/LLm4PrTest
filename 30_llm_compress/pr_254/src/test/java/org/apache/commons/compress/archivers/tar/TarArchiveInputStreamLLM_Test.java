package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.utils.CharsetNames;
import org.junit.jupiter.api.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void testGlobalPaxHeadersPassedToTarArchiveEntry() throws IOException {
        // Create a dummy tar entry with global PAX headers
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final String encoding = CharsetNames.UTF_8;
        final String name = "testfile.txt";
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos, encoding);
        tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
        TarArchiveEntry t = new TarArchiveEntry(name);
        t.setSize(1);
        tos.putArchiveEntry(t);
        tos.write(30);
        tos.closeArchiveEntry();
        tos.close();

        final byte[] data = bos.toByteArray();
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);

        // Create global PAX headers
        Map<String, String> globalPaxHeaders = new HashMap<>();
        globalPaxHeaders.put("key1", "value1");
        globalPaxHeaders.put("key2", "value2");

        // Read the tar entry and verify the global PAX headers are passed correctly
        try (TarArchiveInputStream tis = new TarArchiveInputStream(bis, encoding)) {
            tis.globalPaxHeaders = globalPaxHeaders; // Set global PAX headers
            t = tis.getNextTarEntry();
            assertEquals("value1", t.getExtraPaxHeader("key1"));
            assertEquals("value2", t.getExtraPaxHeader("key2"));
        }
    }

    @Test
    public void testErrorOnInvalidHeaderWithGlobalPaxHeaders() {
        final byte[] invalidHeader = new byte[512]; // Create an invalid header
        final ByteArrayInputStream bis = new ByteArrayInputStream(invalidHeader);

        // Create global PAX headers
        Map<String, String> globalPaxHeaders = new HashMap<>();
        globalPaxHeaders.put("key1", "value1");

        try (TarArchiveInputStream tis = new TarArchiveInputStream(bis)) {
            tis.globalPaxHeaders = globalPaxHeaders; // Set global PAX headers
            tis.getNextTarEntry();
            fail("Expected IOException to be thrown due to invalid header");
        } catch (IOException e) {
            assertEquals("Error detected parsing the header", e.getMessage());
        }
    }
}