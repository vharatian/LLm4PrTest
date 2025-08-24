package org.apache.commons.compress.archivers.arj;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ArjArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testExtendedHeadersArrayInitialization() throws Exception {
        // Test to ensure that the extendedHeaders array is correctly initialized to an empty array
        final ArjArchiveInputStream in = new ArjArchiveInputStream(new FileInputStream(getFile("bla.arj")));
        ArjArchiveEntry entry;
        while ((entry = in.getNextEntry()) != null) {
            assertNotNull(entry.getLocalFileHeader().extendedHeaders);
            assertEquals(0, entry.getLocalFileHeader().extendedHeaders.length);
        }
        in.close();
    }
}