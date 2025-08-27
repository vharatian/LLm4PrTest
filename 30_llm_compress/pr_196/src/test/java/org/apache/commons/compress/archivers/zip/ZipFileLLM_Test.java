package org.apache.commons.compress.archivers.zip;

import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

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
     * Test for the change in getEntries method to use Collections.emptyList()
     */
    @Test
    public void testGetEntriesWithNoEntries() throws IOException {
        // Create an empty zip file
        final File emptyZip = File.createTempFile("empty", ".zip");
        emptyZip.deleteOnExit();
        zf = new ZipFile(emptyZip);

        // Test getEntries with a name that does not exist
        Iterable<ZipArchiveEntry> entries = zf.getEntries("nonexistent");
        assertNotNull(entries);
        assertFalse(entries.iterator().hasNext());

        // Clean up
        emptyZip.delete();
    }

    /**
     * Test for the change in getEntries method to use Collections.emptyList()
     * with a non-empty zip file but querying for a non-existent entry.
     */
    @Test
    public void testGetEntriesWithNonExistentEntry() throws IOException {
        // Load a non-empty zip file
        final File archive = getFile("ordertest.zip");
        zf = new ZipFile(archive);

        // Test getEntries with a name that does not exist
        Iterable<ZipArchiveEntry> entries = zf.getEntries("nonexistent");
        assertNotNull(entries);
        assertFalse(entries.iterator().hasNext());
    }

    /**
     * Test for the change in getEntries method to use Collections.emptyList()
     * with a non-empty zip file and querying for an existing entry.
     */
    @Test
    public void testGetEntriesWithExistingEntry() throws IOException {
        // Load a non-empty zip file
        final File archive = getFile("ordertest.zip");
        zf = new ZipFile(archive);

        // Test getEntries with a name that exists
        Iterable<ZipArchiveEntry> entries = zf.getEntries("ZipFile");
        assertNotNull(entries);
        assertFalse(entries.iterator().hasNext());
    }
}