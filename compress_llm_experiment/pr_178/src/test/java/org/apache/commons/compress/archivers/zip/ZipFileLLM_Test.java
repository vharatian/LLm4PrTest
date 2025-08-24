package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;

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

    /**
     * Test to ensure that the computeIfAbsent method works correctly in fillNameMap.
     */
    @Test
    public void testFillNameMapComputeIfAbsent() throws Exception {
        // Prepare a zip file with known entries
        final File archive = getFile("ordertest.zip");
        zf = new ZipFile(archive);

        // Ensure the nameMap is populated correctly
        final Enumeration<ZipArchiveEntry> entries = zf.getEntries();
        while (entries.hasMoreElements()) {
            final ZipArchiveEntry entry = entries.nextElement();
            final LinkedList<ZipArchiveEntry> entriesOfThatName = zf.nameMap.get(entry.getName());
            assertNotNull(entriesOfThatName);
            assertFalse(entriesOfThatName.isEmpty());
            assertEquals(entry, entriesOfThatName.getFirst());
        }
    }

    /**
     * Test to ensure that the computeIfAbsent method works correctly in fillNameMap with in-memory zip.
     */
    @Test
    public void testFillNameMapComputeIfAbsentInMemory() throws Exception {
        // Prepare a zip file with known entries
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(getFile("ordertest.zip"))) {
            data = IOUtils.toByteArray(fis);
        }
        zf = new ZipFile(new SeekableInMemoryByteChannel(data), ZipEncodingHelper.UTF8);

        // Ensure the nameMap is populated correctly
        final Enumeration<ZipArchiveEntry> entries = zf.getEntries();
        while (entries.hasMoreElements()) {
            final ZipArchiveEntry entry = entries.nextElement();
            final LinkedList<ZipArchiveEntry> entriesOfThatName = zf.nameMap.get(entry.getName());
            assertNotNull(entriesOfThatName);
            assertFalse(entriesOfThatName.isEmpty());
            assertEquals(entry, entriesOfThatName.getFirst());
        }
    }
}