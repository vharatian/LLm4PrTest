package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.apache.commons.compress.utils.CharsetNames;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TarFileLLM_Test {

    @Test
    public void testGlobalPaxHeadersAppliedToEntry() throws IOException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final String encoding = CharsetNames.UTF_16;
        final String name = "testfile.txt";
        final String paxHeaderKey = "key";
        final String paxHeaderValue = "value";
        
        try (final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos, encoding)) {
            tos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
            TarArchiveEntry t = new TarArchiveEntry(name);
            t.setSize(1);
            tos.putArchiveEntry(t);
            tos.write(30);
            tos.closeArchiveEntry();
            
            // Add a global PAX header
            TarArchiveEntry globalPaxHeader = new TarArchiveEntry("././@PaxHeader");
            globalPaxHeader.setSize(paxHeaderKey.length() + paxHeaderValue.length() + 3);
            tos.putArchiveEntry(globalPaxHeader);
            tos.write((paxHeaderKey + "=" + paxHeaderValue + "\n").getBytes(encoding));
            tos.closeArchiveEntry();
        }
        
        final byte[] data = bos.toByteArray();
        try (final TarFile tarFile = new TarFile(data, encoding)) {
            List<TarArchiveEntry> entries = tarFile.getEntries();
            assertEquals(2, entries.size());
            assertEquals(name, entries.get(1).getName());
            assertTrue(entries.get(1).getPaxHeaders().containsKey(paxHeaderKey));
            assertEquals(paxHeaderValue, entries.get(1).getPaxHeaders().get(paxHeaderKey));
        }
    }
}