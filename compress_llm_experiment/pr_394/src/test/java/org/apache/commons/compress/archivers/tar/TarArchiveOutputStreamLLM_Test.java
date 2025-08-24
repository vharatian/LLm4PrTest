package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class TarArchiveOutputStreamLLM_Test {

    /**
     * Test to ensure that the removal of ExactMath import does not affect the functionality.
     * This test will focus on the methods that might have used ExactMath.
     */
    @Test
    public void testBigNumberHandlingWithoutExactMath() throws Exception {
        final TarArchiveEntry entry = new TarArchiveEntry("testBigNumber");
        entry.setSize(0100000000000L); // A large size to test big number handling

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        tos.setBigNumberMode(TarArchiveOutputStream.BIGNUMBER_POSIX);
        tos.putArchiveEntry(entry);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();

        final byte[] data = bos.toByteArray();
        try (TarArchiveInputStream tin = new TarArchiveInputStream(new ByteArrayInputStream(data))) {
            final TarArchiveEntry e = tin.getNextTarEntry();
            assertEquals(0100000000000L, e.getSize());
        }
    }

    /**
     * Test to ensure that the removal of ExactMath import does not affect the functionality.
     * This test will focus on the methods that might have used ExactMath.
     */
    @Test
    public void testPaxHeadersEncodingWithoutExactMath() throws Exception {
        final Map<String, String> paxHeaders = new HashMap<>();
        paxHeaders.put("size", "0100000000000");
        paxHeaders.put("uid", "1000");
        paxHeaders.put("gid", "1000");

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            tos.writePaxHeaders(new TarArchiveEntry("testPaxHeaders"), "testPaxHeaders", paxHeaders);
            final TarArchiveEntry entry = new TarArchiveEntry("testPaxHeaders");
            entry.setSize(10 * 1024);
            tos.putArchiveEntry(entry);
            tos.write(new byte[10 * 1024]);
            tos.closeArchiveEntry();
        }

        final byte[] data = bos.toByteArray();
        assertEquals("00000000145 ",
                new String(data, TarConstants.NAMELEN + TarConstants.MODELEN + TarConstants.UIDLEN + TarConstants.GIDLEN, 12, UTF_8));
        assertEquals("101 size=0100000000000\nuid=1000\ngid=1000\n", new String(data, 512, 101, UTF_8));
    }
}