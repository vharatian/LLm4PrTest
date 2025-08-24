package org.apache.commons.compress.archivers.ar;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.AbstractTestCase;
import org.junit.Test;

public class ArArchiveOutputStreamLLM_Test extends AbstractTestCase {
    @Test
    public void testLongFileNamesCauseExceptionByDefault() {
        try (ArArchiveOutputStream os = new ArArchiveOutputStream(new ByteArrayOutputStream())) {
            final ArArchiveEntry ae = new ArArchiveEntry("this_is_a_long_name.txt", 0);
            os.putArchiveEntry(ae);
            fail("Expected an exception");
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().startsWith("File name too long"));
        }
    }

    @Test
    public void testLongFileNamesWorkUsingBSDDialect() throws Exception {
        final File[] df = createTempDirAndFile();
        try (FileOutputStream fos = new FileOutputStream(df[1]);
             ArArchiveOutputStream os = new ArArchiveOutputStream(fos)) {
            os.setLongFileMode(ArArchiveOutputStream.LONGFILE_BSD);
            final ArArchiveEntry ae = new ArArchiveEntry("this_is_a_long_name.txt", 14);
            os.putArchiveEntry(ae);
            os.write(new byte[] { 'H', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd', '!', '\n' });
            os.closeArchiveEntry();
            final List<String> expected = new ArrayList<>();
            expected.add("this_is_a_long_name.txt");
            checkArchiveContent(df[1], expected);
        } finally {
            rmdir(df[0]);
        }
    }

    // New test to ensure the refactored code handles name length correctly
    @Test
    public void testLongFileNamesLengthCheck() {
        try (ArArchiveOutputStream os = new ArArchiveOutputStream(new ByteArrayOutputStream())) {
            final ArArchiveEntry ae = new ArArchiveEntry("this_is_a_long_name_with_exactly_17_chars.txt", 0);
            os.putArchiveEntry(ae);
            fail("Expected an exception");
        } catch (final IOException ex) {
            assertTrue(ex.getMessage().startsWith("File name too long"));
        }
    }

    // New test to ensure the refactored code handles name length correctly in BSD mode
    @Test
    public void testLongFileNamesLengthCheckBSD() throws Exception {
        final File[] df = createTempDirAndFile();
        try (FileOutputStream fos = new FileOutputStream(df[1]);
             ArArchiveOutputStream os = new ArArchiveOutputStream(fos)) {
            os.setLongFileMode(ArArchiveOutputStream.LONGFILE_BSD);
            final ArArchiveEntry ae = new ArArchiveEntry("this_is_a_long_name_with_exactly_17_chars.txt", 14);
            os.putArchiveEntry(ae);
            os.write(new byte[] { 'H', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd', '!', '\n' });
            os.closeArchiveEntry();
            final List<String> expected = new ArrayList<>();
            expected.add("this_is_a_long_name_with_exactly_17_chars.txt");
            checkArchiveContent(df[1], expected);
        } finally {
            rmdir(df[0]);
        }
    }
}