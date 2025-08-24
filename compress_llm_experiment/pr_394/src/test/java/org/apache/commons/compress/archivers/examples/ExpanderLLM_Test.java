package org.apache.commons.compress.archivers.examples;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class ExpanderLLM_Test extends AbstractTestCase {
    private File archive;

    private void setupZip() throws IOException, ArchiveException {
        archive = new File(dir, "test.zip");
        final File dummy = new File(dir, "x");
        try (OutputStream o = Files.newOutputStream(dummy.toPath())) {
            o.write(new byte[14]);
        }
        try (ArchiveOutputStream aos = ArchiveStreamFactory.DEFAULT
                .createArchiveOutputStream("zip", Files.newOutputStream(archive.toPath()))) {
            aos.putArchiveEntry(aos.createArchiveEntry(dir, "a"));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dir, "a/b"));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dir, "a/b/c"));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dummy, "a/b/d.txt"));
            aos.write("Hello, world 1".getBytes(UTF_8));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dummy, "a/b/c/e.txt"));
            aos.write("Hello, world 2".getBytes(UTF_8));
            aos.closeArchiveEntry();
            aos.finish();
        }
    }

    private void verifyTargetDir() throws IOException {
        assertTrue(new File(resultDir, "a").isDirectory(), "a has not been created");
        assertTrue(new File(resultDir, "a/b").isDirectory(), "a/b has not been created");
        assertTrue(new File(resultDir, "a/b/c").isDirectory(), "a/b/c has not been created");
        assertHelloWorld("a/b/d.txt", "1");
        assertHelloWorld("a/b/c/e.txt", "2");
    }

    private void assertHelloWorld(final String fileName, final String suffix) throws IOException {
        assertTrue(new File(resultDir, fileName).isFile(), fileName + " does not exist");
        final byte[] expected = ("Hello, world " + suffix).getBytes(UTF_8);
        try (InputStream is = Files.newInputStream(new File(resultDir, fileName).toPath())) {
            final byte[] actual = IOUtils.toByteArray(is);
            assertArrayEquals(expected, actual);
        }
    }

    @Test
    public void zipFileVersionWithAutoDetection() throws IOException, ArchiveException {
        setupZip();
        new Expander().expand(archive.toPath(), resultDir.toPath());
        verifyTargetDir();
    }

    @Test
    public void zipInputStreamVersionWithAutoDetection() throws IOException, ArchiveException {
        setupZip();
        try (InputStream i = new BufferedInputStream(Files.newInputStream(archive.toPath()))) {
            new Expander().expand(i, resultDir);
        }
        verifyTargetDir();
    }
}