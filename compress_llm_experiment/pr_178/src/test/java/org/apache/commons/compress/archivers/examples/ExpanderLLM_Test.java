package org.apache.commons.compress.archivers.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public class ExpanderLLM_Test extends AbstractTestCase {

    private File archive;

    @Test
    public void sevenZFileVersionWithLambda() throws IOException, ArchiveException {
        setup7z();
        try (SevenZFile f = new SevenZFile(archive)) {
            new Expander().expand(f, resultDir);
        }
        verifyTargetDir();
    }

    private void setup7z() throws IOException, ArchiveException {
        archive = new File(dir, "test.7z");
        final File dummy = new File(dir, "x");
        try (var o = Files.newOutputStream(dummy.toPath())) {
            o.write(new byte[14]);
        }
        try (var aos = new SevenZOutputFile(archive)) {
            aos.putArchiveEntry(aos.createArchiveEntry(dir, "a"));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dir, "a/b"));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dir, "a/b/c"));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dummy, "a/b/d.txt"));
            aos.write("Hello, world 1".getBytes(StandardCharsets.UTF_8));
            aos.closeArchiveEntry();
            aos.putArchiveEntry(aos.createArchiveEntry(dummy, "a/b/c/e.txt"));
            aos.write("Hello, world 2".getBytes(StandardCharsets.UTF_8));
            aos.closeArchiveEntry();
            aos.finish();
        }
    }

    private void verifyTargetDir() throws IOException {
        Assert.assertTrue("a has not been created", new File(resultDir, "a").isDirectory());
        Assert.assertTrue("a/b has not been created", new File(resultDir, "a/b").isDirectory());
        Assert.assertTrue("a/b/c has not been created", new File(resultDir, "a/b/c").isDirectory());
        assertHelloWorld("a/b/d.txt", "1");
        assertHelloWorld("a/b/c/e.txt", "2");
    }

    private void assertHelloWorld(final String fileName, final String suffix) throws IOException {
        Assert.assertTrue(fileName + " does not exist", new File(resultDir, fileName).isFile());
        final byte[] expected = ("Hello, world " + suffix).getBytes(StandardCharsets.UTF_8);
        try (var is = Files.newInputStream(new File(resultDir, fileName).toPath())) {
            final byte[] actual = IOUtils.toByteArray(is);
            Assert.assertArrayEquals(expected, actual);
        }
    }
}