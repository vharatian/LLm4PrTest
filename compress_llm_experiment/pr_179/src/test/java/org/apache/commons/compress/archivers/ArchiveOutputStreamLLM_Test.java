package org.apache.commons.compress.archivers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    public void testBytesWrittenInitialization() throws Exception {
        final OutputStream out1 = new ByteArrayOutputStream();
        ArchiveOutputStream aos1 = factory.createArchiveOutputStream("zip", out1);
        assertEquals("bytesWritten should be initialized to 0", 0, aos1.getBytesWritten());
    }

    @Test
    public void testBytesWrittenAfterWrite() throws Exception {
        final OutputStream out1 = new ByteArrayOutputStream();
        ArchiveOutputStream aos1 = factory.createArchiveOutputStream("zip", out1);
        aos1.write(1);
        assertEquals("bytesWritten should be 1 after writing one byte", 1, aos1.getBytesWritten());
    }

    @Test
    public void testBytesWrittenAfterMultipleWrites() throws Exception {
        final OutputStream out1 = new ByteArrayOutputStream();
        ArchiveOutputStream aos1 = factory.createArchiveOutputStream("zip", out1);
        aos1.write(new byte[]{1, 2, 3, 4, 5});
        assertEquals("bytesWritten should be 5 after writing five bytes", 5, aos1.getBytesWritten());
    }
}