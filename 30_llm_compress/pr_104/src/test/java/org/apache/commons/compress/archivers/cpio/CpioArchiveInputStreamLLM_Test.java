package org.apache.commons.compress.archivers.cpio;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import org.apache.commons.compress.AbstractTestCase;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class CpioArchiveInputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testUnknownMagicExceptionMessage() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("unknown_magic.cpio")))) {
            in.getNextEntry();
            fail("Expected IOException due to unknown magic");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Unknown magic"));
            assertTrue(e.getMessage().contains("Occurred at byte"));
        }
    }

    @Test
    public void testCrcErrorExceptionMessage() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("crc_error.cpio")))) {
            while (in.getNextEntry() != null) {
                IOUtils.toByteArray(in);
            }
            fail("Expected IOException due to CRC error");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("CRC Error"));
            assertTrue(e.getMessage().contains("Occurred at byte"));
        }
    }

    @Test
    public void testModeZeroExceptionMessageNewEntry() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("mode_zero_new_entry.cpio")))) {
            in.getNextEntry();
            fail("Expected IOException due to mode 0 in new entry");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Mode 0 only allowed in the trailer"));
            assertTrue(e.getMessage().contains("Occurred at byte"));
        }
    }

    @Test
    public void testModeZeroExceptionMessageOldAsciiEntry() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("mode_zero_old_ascii_entry.cpio")))) {
            in.getNextEntry();
            fail("Expected IOException due to mode 0 in old ASCII entry");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Mode 0 only allowed in the trailer"));
            assertTrue(e.getMessage().contains("Occurred at byte"));
        }
    }

    @Test
    public void testModeZeroExceptionMessageOldBinaryEntry() throws Exception {
        try (final CpioArchiveInputStream in = new CpioArchiveInputStream(new FileInputStream(getFile("mode_zero_old_binary_entry.cpio")))) {
            in.getNextEntry();
            fail("Expected IOException due to mode 0 in old binary entry");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Mode 0 only allowed in the trailer"));
            assertTrue(e.getMessage().contains("Occurred at byte"));
        }
    }
}