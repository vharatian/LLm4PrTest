package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testRemoveCharsetNamesImport() throws Exception {
        // This test ensures that the removal of the CharsetNames import does not affect functionality
        final File f = File.createTempFile("commons-compress-tarcount", ".tar");
        f.deleteOnExit();
        final FileOutputStream fos = new FileOutputStream(f);
        final ArchiveOutputStream tarOut = new ArchiveStreamFactory()
                .createArchiveOutputStream(ArchiveStreamFactory.TAR, fos);
        final File file1 = new File("test1.xml");
        final TarArchiveEntry sEntry = new TarArchiveEntry(file1, file1.getName());
        tarOut.putArchiveEntry(sEntry);
        final FileInputStream in = new FileInputStream(file1);
        final byte[] buf = new byte[8192];
        int read = 0;
        while ((read = in.read(buf)) > 0) {
            tarOut.write(buf, 0, read);
        }
        in.close();
        tarOut.closeArchiveEntry();
        tarOut.close();
        assertEquals(f.length(), tarOut.getBytesWritten());
    }

    @Test
    public void testWritePaxHeadersWithoutCharsetNames() throws Exception {
        // This test ensures that the writePaxHeaders method works correctly without the CharsetNames import
        final Map<String, String> m = new HashMap<>();
        m.put("a", "b");
        final byte[] data = writePaxHeader(m);
        assertEquals("00000000006 ",
                new String(data, TarConstants.NAMELEN + TarConstants.MODELEN + TarConstants.UIDLEN + TarConstants.GIDLEN, 12,
                        StandardCharsets.UTF_8));
        assertEquals("6 a=b\n", new String(data, 512, 6, StandardCharsets.UTF_8));
    }

    private byte[] writePaxHeader(final Map<String, String> m) throws Exception {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos, "ASCII");
        tos.writePaxHeaders(new TarArchiveEntry("x"), "foo", m);
        final TarArchiveEntry t = new TarArchiveEntry("foo");
        t.setSize(10 * 1024);
        tos.putArchiveEntry(t);
        tos.write(new byte[10 * 1024]);
        tos.closeArchiveEntry();
        tos.close();
        return bos.toByteArray();
    }
}