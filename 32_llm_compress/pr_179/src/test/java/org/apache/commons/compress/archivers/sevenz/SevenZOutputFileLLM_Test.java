package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.utils.ByteUtils;
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
    public void testDefaultInitialization() throws Exception {
        output = new File(dir, "default-init.7z");
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            // Verify default values of the modified fields
            assertEquals(0, outArchive.numNonEmptyStreams);
            assertEquals(0, outArchive.fileBytesWritten);
            assertFalse(outArchive.finished);
        }
    }

    @Test
    public void testNonDefaultInitialization() throws Exception {
        output = new File(dir, "nondefault-init.7z");
        try (SevenZOutputFile outArchive = new SevenZOutputFile(output)) {
            // Add a file to change the state
            SevenZArchiveEntry entry = new SevenZArchiveEntry();
            entry.setName("testfile");
            outArchive.putArchiveEntry(entry);
            outArchive.write(new byte[] { 'A' });
            outArchive.closeArchiveEntry();
            outArchive.finish();

            // Verify changed values of the modified fields
            assertEquals(1, outArchive.numNonEmptyStreams);
            assertEquals(1, outArchive.fileBytesWritten);
            assertTrue(outArchive.finished);
        }
    }
}