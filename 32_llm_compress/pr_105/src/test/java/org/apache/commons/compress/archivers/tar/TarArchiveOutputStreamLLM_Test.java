package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.utils.CharsetNames;
import org.junit.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testEncodeExtendedPaxHeadersContents() throws Exception {
        // Create a map with sample headers
        final Map<String, String> headers = new HashMap<>();
        headers.put("key1", "value1");
        headers.put("key2", "value2");

        // Create a TarArchiveOutputStream instance
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

        // Use reflection to access the private method encodeExtendedPaxHeadersContents
        final java.lang.reflect.Method method = TarArchiveOutputStream.class.getDeclaredMethod("encodeExtendedPaxHeadersContents", Map.class);
        method.setAccessible(true);

        // Invoke the method and get the result
        final byte[] result = (byte[]) method.invoke(tos, headers);

        // Verify the result
        final String expected = "11 key1=value1\n11 key2=value2\n";
        assertEquals(expected, new String(result, StandardCharsets.UTF_8));
    }

    @Test
    public void testWritePaxHeadersWithStandardCharsets() throws Exception {
        final Map<String, String> headers = new HashMap<>();
        headers.put("key", "value");

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos, "ASCII");

        final TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setSize(10 * 1024);
        tos.putArchiveEntry(entry);
        tos.writePaxHeaders(entry, "test", headers);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();

        final byte[] data = bos.toByteArray();
        final TarArchiveInputStream tin = new TarArchiveInputStream(new ByteArrayInputStream(data));
        final TarArchiveEntry e = tin.getNextTarEntry();
        assertNotNull(e);
        assertEquals("test", e.getName());

        final Reader reader = new InputStreamReader(tin, StandardCharsets.UTF_8);
        final char[] buffer = new char[11];
        reader.read(buffer);
        assertEquals("key=value\n", new String(buffer));
        assertNull(tin.getNextTarEntry());
        tin.close();
    }
}