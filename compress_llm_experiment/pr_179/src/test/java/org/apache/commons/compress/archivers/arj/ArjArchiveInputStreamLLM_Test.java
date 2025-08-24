package org.apache.commons.compress.archivers.arj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ArjArchiveInputStreamLLM_Test extends AbstractTestCase {

    /**
     * Test to ensure that the initialization of currentLocalFileHeader and currentInputStream
     * without explicit null assignment works as expected.
     */
    @Test
    public void testInitializationWithoutExplicitNullAssignment() throws Exception {
        try (final ArjArchiveInputStream in = new ArjArchiveInputStream(new FileInputStream(getFile("bla.arj")))) {
            // Check initial state of currentLocalFileHeader and currentInputStream
            assertEquals(null, in.currentLocalFileHeader);
            assertEquals(null, in.currentInputStream);

            // Read the first entry
            final ArjArchiveEntry entry = in.getNextEntry();
            assertEquals("test1.xml", entry.getName());

            // Check state after reading the first entry
            assertEquals(entry.getLocalFileHeader(), in.currentLocalFileHeader);
            assertEquals(true, in.currentInputStream != null);
        }
    }
}