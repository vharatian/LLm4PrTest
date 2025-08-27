package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IOUtilsLLM_Test {

    @Test
    public void testCopyURLToFile() throws IOException {
        URL url = new URL("http://www.example.com");
        File file = Files.createTempFile("testCopyURLToFile", ".tmp").toFile();
        file.deleteOnExit();

        long copiedBytes = IOUtils.copy(url, file);
        assertEquals(file.length(), copiedBytes);
    }

    @Test
    public void testCopyURLToFileWithNullFile() {
        URL url = new URL("http://www.example.com");
        assertThrows(NullPointerException.class, () -> {
            IOUtils.copy(url, null);
        });
    }

    @Test
    public void testCopyURLToFileWithInvalidURL() {
        URL url = new URL("http://invalid.url");
        File file = Files.createTempFile("testCopyURLToFile", ".tmp").toFile();
        file.deleteOnExit();

        assertThrows(IOException.class, () -> {
            IOUtils.copy(url, file);
        });
    }
}