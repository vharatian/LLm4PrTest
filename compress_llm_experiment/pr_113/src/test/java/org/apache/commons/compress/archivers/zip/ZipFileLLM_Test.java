package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @Test
    public void testBoundedSeekableByteChannelInputStream() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive);
        final ZipArchiveEntry entry = zf.getEntry("inflated.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getInputStream(entry)) {
            byte[] content = IOUtils.toByteArray(is);
            assertEquals(70, content.length); // Assuming the length of the content is 70 bytes
        }
    }

    @Test
    public void testBoundedFileChannelInputStream() throws Exception {
        final File archive = getFile("mixed.zip");
        zf = new ZipFile(archive);
        final ZipArchiveEntry entry = zf.getEntry("stored.txt");
        assertNotNull(entry);
        try (InputStream is = zf.getInputStream(entry)) {
            byte[] content = IOUtils.toByteArray(is);
            assertEquals(70, content.length); // Assuming the length of the content is 70 bytes
        }
    }

    @Test
    public void testConcurrentReadWithBoundedInputStream() throws Exception {
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(getFile("mixed.zip"))) {
            data = IOUtils.toByteArray(fis);
        }
        zf = new ZipFile(new SeekableInMemoryByteChannel(data), ZipEncodingHelper.UTF8);
        final Map<String, byte[]> content = new HashMap<>();
        for (final ZipArchiveEntry entry : Collections.list(zf.getEntries())) {
            content.put(entry.getName(), IOUtils.toByteArray(zf.getInputStream(entry)));
        }
        final AtomicInteger passedCount = new AtomicInteger();
        final Runnable run = () -> {
            for (final ZipArchiveEntry entry : Collections.list(zf.getEntries())) {
                assertAllReadMethods(content.get(entry.getName()), zf, entry);
            }
            passedCount.incrementAndGet();
        };
        final Thread t0 = new Thread(run);
        final Thread t1 = new Thread(run);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        assertEquals(2, passedCount.get());
    }

    private void assertAllReadMethods(final byte[] expected, final ZipFile zipFile, final ZipArchiveEntry entry) {
        try (InputStream stream = zf.getInputStream(entry)) {
            final byte[] full = IOUtils.toByteArray(stream);
            assertEquals(expected.length, full.length);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
        try (InputStream stream = zf.getInputStream(entry)) {
            byte[] full;
            final byte[] bytes = new byte[0x40000];
            final int read = stream.read(bytes);
            if (read < 0) {
                full = new byte[0];
            } else {
                full = readStreamRest(bytes, read, stream);
            }
            assertEquals(expected.length, full.length);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
        try (InputStream stream = zf.getInputStream(entry)) {
            byte[] full;
            final int single = stream.read();
            if (single < 0) {
                full = new byte[0];
            } else {
                final byte[] big = new byte[0x40000];
                big[0] = (byte) single;
                final int read = stream.read(big, 1, big.length - 1);
                if (read < 0) {
                    full = new byte[] { (byte) single };
                } else {
                    full = readStreamRest(big, read + 1, stream);
                }
            }
            assertEquals(expected.length, full.length);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private byte[] readStreamRest(final byte[] beginning, final int length, final InputStream stream) throws IOException {
        final byte[] rest = IOUtils.toByteArray(stream);
        final byte[] full = new byte[length + rest.length];
        System.arraycopy(beginning, 0, full, 0, length);
        System.arraycopy(rest, 0, full, length, rest.length);
        return full;
    }
}