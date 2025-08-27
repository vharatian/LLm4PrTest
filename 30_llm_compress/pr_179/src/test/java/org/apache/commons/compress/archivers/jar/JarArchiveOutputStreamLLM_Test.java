package org.apache.commons.compress.archivers.jar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.Test;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class JarArchiveOutputStreamLLM_Test {

    @Test
    public void testJarMarkerInitialization() throws IOException {
        final File testArchive = File.createTempFile("jar-aostest-init", ".jar");
        testArchive.deleteOnExit();
        JarArchiveOutputStream out = null;
        ZipFile zf = null;
        try {
            out = new JarArchiveOutputStream(new FileOutputStream(testArchive));
            out.putArchiveEntry(new ZipArchiveEntry("foo/"));
            out.closeArchiveEntry();
            out.putArchiveEntry(new ZipArchiveEntry("bar/"));
            out.closeArchiveEntry();
            out.finish();
            out.close();
            out = null;
            zf = new ZipFile(testArchive);
            ZipArchiveEntry ze = zf.getEntry("foo/");
            assertNotNull(ze);
            ZipExtraField[] fes = ze.getExtraFields();
            assertEquals(1, fes.length);
            assertTrue(fes[0] instanceof JarMarker);
            ze = zf.getEntry("bar/");
            assertNotNull(ze);
            fes = ze.getExtraFields();
            assertEquals(0, fes.length);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (final IOException e) { }
            }
            ZipFile.closeQuietly(zf);
            AbstractTestCase.tryHardToDelete(testArchive);
        }
    }
}