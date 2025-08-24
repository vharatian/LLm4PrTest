package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testDefaultValues() throws Exception {
        // Test to ensure default values of the modified fields are set correctly
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

        assertFalse(tos.isClosed());
        assertFalse(tos.isHaveUnclosedEntry());
        assertFalse(tos.isFinished());
        assertFalse(tos.isAddPaxHeadersForNonAsciiNames());

        tos.close();
    }

    @Test
    public void testSetClosed() throws Exception {
        // Test to ensure the closed field is set correctly
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

        tos.close();
        assertTrue(tos.isClosed());
    }

    @Test
    public void testSetHaveUnclosedEntry() throws Exception {
        // Test to ensure the haveUnclosedEntry field is set correctly
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

        final TarArchiveEntry entry = new TarArchiveEntry("test");
        entry.setSize(10);
        tos.putArchiveEntry(entry);
        assertTrue(tos.isHaveUnclosedEntry());

        tos.closeArchiveEntry();
        assertFalse(tos.isHaveUnclosedEntry());

        tos.close();
    }

    @Test
    public void testSetFinished() throws Exception {
        // Test to ensure the finished field is set correctly
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

        tos.finish();
        assertTrue(tos.isFinished());

        tos.close();
    }

    @Test
    public void testSetAddPaxHeadersForNonAsciiNames() throws Exception {
        // Test to ensure the addPaxHeadersForNonAsciiNames field is set correctly
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final TarArchiveOutputStream tos = new TarArchiveOutputStream(bos);

        tos.setAddPaxHeadersForNonAsciiNames(true);
        assertTrue(tos.isAddPaxHeadersForNonAsciiNames());

        tos.setAddPaxHeadersForNonAsciiNames(false);
        assertFalse(tos.isAddPaxHeadersForNonAsciiNames());

        tos.close();
    }
}