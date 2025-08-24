package org.apache.commons.compress.archivers.ar;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.*;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class ArArchiveInputStreamLLM_Test extends AbstractTestCase {
    @Test
    public void testIOExceptionMessages() throws Exception {
        // Test for "Failed to read header. Occurred at byte: " message
        try (ByteArrayInputStream bais = new ByteArrayInputStream(new byte[0]);
             ArArchiveInputStream s = new ArArchiveInputStream(bais)) {
            s.getNextEntry();
            fail("Expected IOException");
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("Failed to read header. Occurred at byte: "));
        }

        // Test for "Failed to read entry trailer. Occurred at byte: " message
        byte[] header = ArchiveUtils.toAsciiBytes(ArArchiveEntry.HEADER);
        byte[] truncatedEntry = new byte[header.length + 1];
        System.arraycopy(header, 0, truncatedEntry, 0, header.length);
        truncatedEntry[header.length] = '0'; // Simulate a truncated entry

        try (ByteArrayInputStream bais = new ByteArrayInputStream(truncatedEntry);
             ArArchiveInputStream s = new ArArchiveInputStream(bais)) {
            s.getNextEntry();
            fail("Expected IOException");
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("Failed to read entry trailer. Occurred at byte: "));
        }

        // Test for "Invalid entry trailer. not read the content? Occurred at byte: " message
        byte[] invalidTrailer = new byte[header.length + ArArchiveEntry.TRAILER.length()];
        System.arraycopy(header, 0, invalidTrailer, 0, header.length);
        System.arraycopy(new byte[ArArchiveEntry.TRAILER.length()], 0, invalidTrailer, header.length, ArArchiveEntry.TRAILER.length());

        try (ByteArrayInputStream bais = new ByteArrayInputStream(invalidTrailer);
             ArArchiveInputStream s = new ArArchiveInputStream(bais)) {
            s.getNextEntry();
            fail("Expected IOException");
        } catch (IOException e) {
            assertThat(e.getMessage(), containsString("Invalid entry trailer. not read the content? Occurred at byte: "));
        }
    }
}