package org.apache.commons.compress.archivers.dump;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class DumpArchiveInputStreamLLM_Test {

    @Test
    public void testFinalFieldsInitialization() throws Exception {
        try (FileInputStream is = new FileInputStream(getFile("archive_with_trailer.dump"))) {
            DumpArchiveInputStream dump = new DumpArchiveInputStream(is);
            assertNotNull("Summary should be initialized", dump.getSummary());
            assertNotNull("Raw should be initialized", dump.raw);
            assertNotNull("Queue should be initialized", dump.queue);
            dump.close();
        }
    }

    @Test
    public void testFinalFieldsImmutability() throws Exception {
        try (FileInputStream is = new FileInputStream(getFile("archive_with_trailer.dump"))) {
            DumpArchiveInputStream dump = new DumpArchiveInputStream(is);
            try {
                dump.summary = null;
                fail("Expected IllegalAccessError due to final field modification");
            } catch (IllegalAccessError e) {
                assertTrue(true);
            }
            try {
                dump.raw = null;
                fail("Expected IllegalAccessError due to final field modification");
            } catch (IllegalAccessError e) {
                assertTrue(true);
            }
            try {
                dump.queue = null;
                fail("Expected IllegalAccessError due to final field modification");
            } catch (IllegalAccessError e) {
                assertTrue(true);
            }
            dump.close();
        }
    }

    private InputStream getFile(String name) {
        return DumpArchiveInputStreamTest2.class.getResourceAsStream("/" + name);
    }
}