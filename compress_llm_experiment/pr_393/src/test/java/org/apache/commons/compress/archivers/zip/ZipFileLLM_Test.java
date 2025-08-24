package org.apache.commons.compress.archivers.zip;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipException;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;

public class ZipFileLLM_Test {

    @Test
    public void testCloseQuietlyWithNull() {
        // Ensure that closeQuietly does not throw an exception when given a null input
        assertDoesNotThrow(() -> ZipFile.closeQuietly(null));
    }

    @Test
    public void testCloseQuietlyWithValidZipFile() throws IOException {
        // Create a temporary zip file
        Path tempZip = Files.createTempFile("testCloseQuietly", ".zip");
        try (ZipFile zipFile = new ZipFile(tempZip.toFile())) {
            // Ensure that closeQuietly does not throw an exception when closing a valid ZipFile
            assertDoesNotThrow(() -> ZipFile.closeQuietly(zipFile));
        } finally {
            Files.deleteIfExists(tempZip);
        }
    }

    @Test
    public void testPositionAtEndOfCentralDirectoryRecordThrowsException() throws IOException {
        // Create a temporary file that is not a valid zip file
        Path tempFile = Files.createTempFile("testInvalidZip", ".zip");
        try (ZipFile zipFile = new ZipFile(tempFile.toFile())) {
            // Ensure that positionAtEndOfCentralDirectoryRecord throws a ZipException for an invalid zip file
            assertThrows(ZipException.class, () -> {
                zipFile.getEntries();
            });
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testStartsWithLocalFileHeader() throws IOException {
        // Create a temporary zip file
        Path tempZip = Files.createTempFile("testStartsWithLocalFileHeader", ".zip");
        try (ZipFile zipFile = new ZipFile(tempZip.toFile())) {
            // Ensure that startsWithLocalFileHeader returns false for an empty zip file
            assertEquals(false, zipFile.startsWithLocalFileHeader());
        } finally {
            Files.deleteIfExists(tempZip);
        }
    }
}