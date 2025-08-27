package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testDeleteExistingFile() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("tempFile", ".txt");
        assertTrue(tempFile.exists(), "Temporary file should exist before deletion");

        // Delete the file using FileUtils.delete
        FileUtils.delete(tempFile);

        // Verify the file has been deleted
        assertFalse(tempFile.exists(), "Temporary file should not exist after deletion");
    }

    @Test
    public void testDeleteNonExistingFile() {
        // Create a reference to a non-existing file
        File nonExistingFile = new File("nonExistingFile.txt");

        // Attempt to delete the non-existing file and expect an IOException
        assertThrows(NoSuchFileException.class, () -> {
            FileUtils.delete(nonExistingFile);
        });
    }

    @Test
    public void testDeleteNullFile() {
        // Attempt to delete a null file and expect a NullPointerException
        assertThrows(NullPointerException.class, () -> {
            FileUtils.delete(null);
        });
    }
}