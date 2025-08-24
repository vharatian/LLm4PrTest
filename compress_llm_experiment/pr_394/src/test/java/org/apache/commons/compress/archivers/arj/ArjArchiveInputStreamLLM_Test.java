package org.apache.commons.compress.archivers.arj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.InputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class ArjArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testReadHeaderWithModifiedFirstVariable() throws Exception {
        try (InputStream in = newInputStream("bla.arj");
             ArjArchiveInputStream archive = new ArjArchiveInputStream(in)) {
            // Trigger the readHeader method indirectly by calling getNextEntry
            final ArchiveEntry e = archive.getNextEntry();
            // Ensure that the entry is read correctly
            assertEquals("test1.xml", e.getName());
        }
    }
}