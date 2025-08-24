package org.apache.commons.compress.archivers.zip;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.compress.AbstractTest.getFile;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.apache.commons.io.function.IORunnable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ZipFileLLM_Test extends AbstractTest {
    private ZipFile zf;

    @AfterEach
    public void tearDownClose() {
        ZipFile.closeQuietly(zf);
    }

    @Test
    public void testMaxNumberOfDisks() throws Exception {
        final File archive = getFile("split_zip_created_by_winrar.zip");
        assertThrows(IOException.class, () -> {
            ZipFile.builder().setFile(archive).setMaxNumberOfDisks(1).get();
        });
    }

    @Test
    public void testOpenZipChannel() throws Exception {
        final File archive = getFile("split_zip_created_by_winrar.zip");
        Path path = archive.toPath();
        SeekableByteChannel channel = ZipFile.builder().setFile(archive).setMaxNumberOfDisks(10).get().getChannel();
        assertNotNull(channel);
        channel.close();
    }

    @Test
    public void testConcurrentReadWithMaxDisks() throws Exception {
        byte[] data;
        try (InputStream fis = newInputStream("split_zip_created_by_winrar.zip")) {
            data = IOUtils.toByteArray(fis);
        }
        try (SeekableInMemoryByteChannel channel = new SeekableInMemoryByteChannel(data)) {
            zf = ZipFile.builder().setSeekableByteChannel(channel).setCharset(UTF_8).setMaxNumberOfDisks(10).get();
            final Map<String, byte[]> content = new HashMap<>();
            for (final ZipArchiveEntry entry : Collections.list(zf.getEntries())) {
                try (InputStream inputStream = zf.getInputStream(entry)) {
                    content.put(entry.getName(), IOUtils.toByteArray(inputStream));
                }
            }
            final AtomicInteger passedCount = new AtomicInteger();
            final IORunnable run = () -> {
                for (final ZipArchiveEntry entry : Collections.list(zf.getEntries())) {
                    assertAllReadMethods(content.get(entry.getName()), zf, entry);
                }
                passedCount.incrementAndGet();
            };
            final Thread t0 = new Thread(run.asRunnable());
            final Thread t1 = new Thread(run.asRunnable());
            t0.start();
            t1.start();
            t0.join();
            t1.join();
            assertEquals(2, passedCount.get());
        }
    }

    private void assertAllReadMethods(final byte[] expected, final ZipFile zipFile, final ZipArchiveEntry entry) throws IOException {
        try (InputStream stream = zf.getInputStream(entry)) {
            final byte[] full = IOUtils.toByteArray(stream);
            assertArrayEquals(expected, full);
        }
        try (InputStream stream = zf.getInputStream(entry)) {
            byte[] full;
            final byte[] bytes = new byte[0x40000];
            final int read = stream.read(bytes);
            if (read < 0) {
                full = ByteUtils.EMPTY_BYTE_ARRAY;
            } else {
                full = readStreamRest(bytes, read, stream);
            }
            assertArrayEquals(expected, full);
        }
        try (InputStream stream = zf.getInputStream(entry)) {
            byte[] full;
            final int single = stream.read();
            if (single < 0) {
                full = ByteUtils.EMPTY_BYTE_ARRAY;
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
            assertArrayEquals(expected, full);
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