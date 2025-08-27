package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.junit.After;
import org.junit.Test;

public class ZipFileLLM_Test {
    private ZipFile zf = null;

    @After
    public void tearDown() {
        ZipFile.closeQuietly(zf);
    }

    /**
     * Test to ensure the changes in the diff file are covered.
     * Specifically, the changes to the read method in BoundedInputStream.
     */
    @Test
    public void testBoundedInputStreamRead() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);

        try (InputStream is = zf.getInputStream(entry)) {
            byte[] buffer = new byte[10];
            int bytesRead = is.read(buffer, 0, buffer.length);
            assertEquals(10, bytesRead);

            bytesRead = is.read(buffer, 0, buffer.length);
            assertEquals(10, bytesRead);

            bytesRead = is.read(buffer, 0, buffer.length);
            assertEquals(10, bytesRead);
        }
    }

    /**
     * Test to ensure the changes in the diff file are covered.
     * Specifically, the changes to the read method in BoundedFileChannelInputStream.
     */
    @Test
    public void testBoundedFileChannelInputStreamRead() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive);
        final ZipArchiveEntry entry = zf.getEntry("stored.txt");
        assertNotNull(entry);

        try (InputStream is = zf.getInputStream(entry)) {
            byte[] buffer = new byte[10];
            int bytesRead = is.read(buffer, 0, buffer.length);
            assertEquals(10, bytesRead);

            bytesRead = is.read(buffer, 0, buffer.length);
            assertEquals(10, bytesRead);

            bytesRead = is.read(buffer, 0, buffer.length);
            assertEquals(10, bytesRead);
        }
    }
}