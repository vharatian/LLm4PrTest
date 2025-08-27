package org.apache.commons.compress.archivers.ar;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.AbstractTestCase;
import org.junit.Test;

public class ArArchiveOutputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testWriteArchiveHeader() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ArArchiveOutputStream os = new ArArchiveOutputStream(baos)) {
            os.putArchiveEntry(new ArArchiveEntry("testfile.txt", 0));
            os.closeArchiveEntry();
            byte[] header = baos.toByteArray();
            assertTrue(header.length > 0);
        }
    }

    @Test
    public void testWriteEntryHeader() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ArArchiveOutputStream os = new ArArchiveOutputStream(baos)) {
            ArArchiveEntry entry = new ArArchiveEntry("testfile.txt", 0);
            os.putArchiveEntry(entry);
            os.closeArchiveEntry();
            byte[] header = baos.toByteArray();
            assertTrue(header.length > 0);
        }
    }

    @Test
    public void testEntryOffsetInitialization() throws IOException {
        try (ArArchiveOutputStream os = new ArArchiveOutputStream(new ByteArrayOutputStream())) {
            assertTrue(os.entryOffset == 0);
        }
    }

    @Test
    public void testHaveUnclosedEntryInitialization() throws IOException {
        try (ArArchiveOutputStream os = new ArArchiveOutputStream(new ByteArrayOutputStream())) {
            assertTrue(!os.haveUnclosedEntry);
        }
    }

    @Test
    public void testFinishedInitialization() throws IOException {
        try (ArArchiveOutputStream os = new ArArchiveOutputStream(new ByteArrayOutputStream())) {
            assertTrue(!os.finished);
        }
    }
}