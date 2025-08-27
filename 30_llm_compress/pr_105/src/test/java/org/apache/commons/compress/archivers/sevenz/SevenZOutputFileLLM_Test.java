package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.apache.commons.compress.AbstractTestCase;

public class SevenZOutputFileLLM_Test extends AbstractTestCase {

    private File output;

    @Override
    public void tearDown() throws Exception {
        if (output != null && !output.delete()) {
            output.deleteOnExit();
        }
        super.tearDown();
    }

    @Test
    public void testFileNameEncoding() throws Exception {
        output = new File(dir, "filename-encoding.7z");
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("testfile");
            outArchive.putArchiveEntry(entry);
            outArchive.write("content".getBytes(StandardCharsets.UTF_8));
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(output)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("testfile", entry.getName());
            byte[] content = new byte[(int) entry.getSize()];
            assertEquals(content.length, archive.read(content));
            assertEquals("content", new String(content, StandardCharsets.UTF_8));
        }
    }

    @Test
    public void testFileNameEncodingUTF16LE() throws Exception {
        output = new File(dir, "filename-encoding-utf16le.7z");
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("testfile-utf16le");
            outArchive.putArchiveEntry(entry);
            outArchive.write("content".getBytes(StandardCharsets.UTF_8));
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }

        try (SevenZFile archive = new SevenZFile(output)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("testfile-utf16le", entry.getName());
            byte[] content = new byte[(int) entry.getSize()];
            assertEquals(content.length, archive.read(content));
            assertEquals("content", new String(content, StandardCharsets.UTF_8));
        }
    }
}