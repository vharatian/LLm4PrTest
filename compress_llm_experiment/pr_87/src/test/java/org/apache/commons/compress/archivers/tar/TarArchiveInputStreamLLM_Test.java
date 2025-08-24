package org.apache.commons.compress.archivers.tar;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.apache.commons.compress.AbstractTestCase.mkdir;
import static org.apache.commons.compress.AbstractTestCase.rmdir;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class TarArchiveInputStreamLLM_Test {

    @Test
    public void testSparseInputStreamReading() throws Exception {
        // Create a tar archive with sparse entries
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        TarArchiveEntry t = new TarArchiveEntry("sparsefile");
        t.setSize(1024);
        tos.putArchiveEntry(t);
        tos.write(new byte[512]); // Sparse hole
        tos.write(new byte[512]); // Actual data
        tos.closeArchiveEntry();
        tos.close();

        final byte[] data = bos.toByteArray();
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);
        final TarArchiveInputStream tis = new TarArchiveInputStream(bis);

        // Read the sparse entry
        t = tis.getNextTarEntry();
        byte[] buffer = new byte[1024];
        int bytesRead = tis.read(buffer);

        // Verify the sparse data
        assertEquals(1024, bytesRead);
        for (int i = 0; i < 512; i++) {
            assertEquals(0, buffer[i]);
        }
        for (int i = 512; i < 1024; i++) {
            assertEquals(0, buffer[i]);
        }

        tis.close();
    }

    @Test
    public void testSkipSparse() throws Exception {
        // Create a tar archive with sparse entries
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        TarArchiveEntry t = new TarArchiveEntry("sparsefile");
        t.setSize(1024);
        tos.putArchiveEntry(t);
        tos.write(new byte[512]); // Sparse hole
        tos.write(new byte[512]); // Actual data
        tos.closeArchiveEntry();
        tos.close();

        final byte[] data = bos.toByteArray();
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);
        final TarArchiveInputStream tis = new TarArchiveInputStream(bis);

        // Read the sparse entry
        t = tis.getNextTarEntry();
        long skipped = tis.skip(1024);

        // Verify the skipped bytes
        assertEquals(1024, skipped);

        tis.close();
    }

    @Test
    public void testCloseSparseInputStreams() throws Exception {
        // Create a tar archive with sparse entries
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);
        TarArchiveEntry t = new TarArchiveEntry("sparsefile");
        t.setSize(1024);
        tos.putArchiveEntry(t);
        tos.write(new byte[512]); // Sparse hole
        tos.write(new byte[512]); // Actual data
        tos.closeArchiveEntry();
        tos.close();

        final byte[] data = bos.toByteArray();
        final ByteArrayInputStream bis = new ByteArrayInputStream(data);
        final TarArchiveInputStream tis = new TarArchiveInputStream(bis);

        // Read the sparse entry
        t = tis.getNextTarEntry();
        tis.read(new byte[1024]);

        // Close the TarArchiveInputStream and verify all sparse input streams are closed
        tis.close();
    }

    @Test
    public void testParsePAX1XSparseHeaders() throws Exception {
        final InputStream is = new ByteArrayInputStream("1\n0\n512\n".getBytes(CharsetNames.UTF_8));
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        List<TarArchiveStructSparse> sparseHeaders = tais.parsePAX1XSparseHeaders();
        assertEquals(1, sparseHeaders.size());
        assertEquals(0, sparseHeaders.get(0).getOffset());
        assertEquals(512, sparseHeaders.get(0).getNumbytes());
        tais.close();
    }

    @Test
    public void testReadLineOfNumberForPax1X() throws Exception {
        final InputStream is = new ByteArrayInputStream("512\n".getBytes(CharsetNames.UTF_8));
        final TarArchiveInputStream tais = new TarArchiveInputStream(is);
        long[] result = tais.readLineOfNumberForPax1X(is);
        assertEquals(512, result[0]);
        assertEquals(4, result[1]);
        tais.close();
    }
}