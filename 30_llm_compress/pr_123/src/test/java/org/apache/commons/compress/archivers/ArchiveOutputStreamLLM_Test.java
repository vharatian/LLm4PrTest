package org.apache.commons.compress.archivers;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.commons.compress.archivers.cpio.CpioArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ArchiveOutputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testCreateArchiveEntryWithPath() throws Exception {
        final OutputStream out1 = new ByteArrayOutputStream();
        final Path dummyPath = Paths.get(getFile("test1.xml").toURI());
        ArchiveOutputStream aos1;

        // Test for Zip
        aos1 = factory.createArchiveOutputStream("zip", out1);
        ArchiveEntry entry = aos1.createArchiveEntry(dummyPath, "dummy");
        assertNotNull(entry);
        assertTrue(entry instanceof ZipArchiveEntry);
        aos1.close();

        // Test for Jar
        aos1 = factory.createArchiveOutputStream("jar", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy");
        assertNotNull(entry);
        assertTrue(entry instanceof JarArchiveEntry);
        aos1.close();

        // Test for Ar
        aos1 = factory.createArchiveOutputStream("ar", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy");
        assertNotNull(entry);
        assertTrue(entry instanceof ArArchiveEntry);
        aos1.close();

        // Test for Cpio
        aos1 = factory.createArchiveOutputStream("cpio", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy");
        assertNotNull(entry);
        assertTrue(entry instanceof CpioArchiveEntry);
        aos1.close();

        // Test for Tar
        aos1 = factory.createArchiveOutputStream("tar", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy");
        assertNotNull(entry);
        assertTrue(entry instanceof TarArchiveEntry);
        aos1.close();
    }

    @Test
    public void testCreateArchiveEntryWithPathAndOptions() throws Exception {
        final OutputStream out1 = new ByteArrayOutputStream();
        final Path dummyPath = Paths.get(getFile("test1.xml").toURI());
        ArchiveOutputStream aos1;

        // Test for Zip
        aos1 = factory.createArchiveOutputStream("zip", out1);
        ArchiveEntry entry = aos1.createArchiveEntry(dummyPath, "dummy", LinkOption.NOFOLLOW_LINKS);
        assertNotNull(entry);
        assertTrue(entry instanceof ZipArchiveEntry);
        aos1.close();

        // Test for Jar
        aos1 = factory.createArchiveOutputStream("jar", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy", LinkOption.NOFOLLOW_LINKS);
        assertNotNull(entry);
        assertTrue(entry instanceof JarArchiveEntry);
        aos1.close();

        // Test for Ar
        aos1 = factory.createArchiveOutputStream("ar", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy", LinkOption.NOFOLLOW_LINKS);
        assertNotNull(entry);
        assertTrue(entry instanceof ArArchiveEntry);
        aos1.close();

        // Test for Cpio
        aos1 = factory.createArchiveOutputStream("cpio", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy", LinkOption.NOFOLLOW_LINKS);
        assertNotNull(entry);
        assertTrue(entry instanceof CpioArchiveEntry);
        aos1.close();

        // Test for Tar
        aos1 = factory.createArchiveOutputStream("tar", out1);
        entry = aos1.createArchiveEntry(dummyPath, "dummy", LinkOption.NOFOLLOW_LINKS);
        assertNotNull(entry);
        assertTrue(entry instanceof TarArchiveEntry);
        aos1.close();
    }
}