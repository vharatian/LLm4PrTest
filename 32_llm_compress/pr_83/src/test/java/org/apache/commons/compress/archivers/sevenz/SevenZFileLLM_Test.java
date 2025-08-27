package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.Test;

public class SevenZFileLLM_Test extends AbstractTestCase {

    @Test
    public void testGetInputStream() throws Exception {
        SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/Copy.7z"));
        SevenZArchiveEntry entry = archive.getNextEntry();
        assertNotNull(entry);
        byte[] content = readFully(archive);

        // Use getInputStream to read the same entry
        InputStream entryStream = archive.getInputStream(entry);
        byte[] contentFromStream = IOUtils.toByteArray(entryStream);
        assertArrayEquals(content, contentFromStream);

        archive.close();
    }

    @Test
    public void testRandomAccessSkippingEntries() throws Exception {
        SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/Copy.7z"));
        SevenZArchiveEntry entry1 = archive.getNextEntry();
        assertNotNull(entry1);
        SevenZArchiveEntry entry2 = archive.getNextEntry();
        assertNotNull(entry2);

        // Random access to the second entry
        InputStream entryStream = archive.getInputStream(entry2);
        byte[] contentFromStream = IOUtils.toByteArray(entryStream);
        assertNotNull(contentFromStream);

        archive.close();
    }

    @Test
    public void testGetInputStreamWithRandomAccess() throws Exception {
        SevenZFile archive = new SevenZFile(getFile("COMPRESS-320/Copy.7z"));
        SevenZArchiveEntry entry1 = archive.getNextEntry();
        assertNotNull(entry1);
        SevenZArchiveEntry entry2 = archive.getNextEntry();
        assertNotNull(entry2);

        // Random access to the first entry again
        InputStream entryStream = archive.getInputStream(entry1);
        byte[] contentFromStream = IOUtils.toByteArray(entryStream);
        assertNotNull(contentFromStream);

        archive.close();
    }

    private byte[] readFully(final SevenZFile archive) throws IOException {
        final byte[] buf = new byte[1024];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int len = 0; (len = archive.read(buf)) > 0;) {
            baos.write(buf, 0, len);
        }
        return baos.toByteArray();
    }
}