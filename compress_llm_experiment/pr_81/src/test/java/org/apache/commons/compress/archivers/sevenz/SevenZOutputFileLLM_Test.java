package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel;
import org.tukaani.xz.LZMA2Options;

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
    public void testAdditionalCountingStreamsArraySize() throws Exception {
        output = new File(dir, "additionalCountingStreamsArraySize.7z");
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("testfile");
            outArchive.putArchiveEntry(entry);
            outArchive.write(new byte[0]);
            outArchive.closeArchiveEntry();
            outArchive.finish();
        }
        try (SevenZFile archive = new SevenZFile(output)) {
            SevenZArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("testfile", entry.getName());
            assertEquals(0, entry.getSize());
        }
    }
}