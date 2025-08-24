package org.apache.commons.compress.archivers.ar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.AbstractTest;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ArArchiveInputStreamLLM_Test extends AbstractTest {

    @Test
    public void testAsIntWithIOException() throws Exception {
        try (InputStream in = newInputStream("org/apache/commons/compress/ar/number_parsing/bad_int_value.ar");
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextEntry());
        }
    }

    @Test
    public void testAsLongWithIOException() throws Exception {
        try (InputStream in = newInputStream("org/apache/commons/compress/ar/number_parsing/bad_long_value.ar");
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextEntry());
        }
    }

    @Test
    public void testGetBSDLongNameWithIOException() throws Exception {
        try (InputStream in = newInputStream("org/apache/commons/compress/ar/number_parsing/bad_bsd_longname.ar");
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextEntry());
        }
    }

    @Test
    public void testGetExtendedNameWithIOException() throws Exception {
        try (InputStream in = newInputStream("org/apache/commons/compress/ar/number_parsing/bad_gnu_longname_offset.ar");
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextEntry());
        }
    }

    @Test
    public void testReadGNUStringTableWithIOException() throws Exception {
        try (InputStream in = newInputStream("org/apache/commons/compress/ar/number_parsing/bad_gnu_string_table.ar");
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            assertThrows(IOException.class, () -> archive.getNextEntry());
        }
    }

    @Test
    public void testParsingUtilsIntegration() throws Exception {
        try (InputStream in = newInputStream("org/apache/commons/compress/ar/number_parsing/valid_ar_with_parsing_utils.ar");
             ArArchiveInputStream archive = new ArArchiveInputStream(in)) {
            ArchiveEntry entry = archive.getNextEntry();
            assertNotNull(entry);
            assertEquals("testfile.txt", entry.getName());
            assertEquals(100, entry.getSize());
        }
    }
}