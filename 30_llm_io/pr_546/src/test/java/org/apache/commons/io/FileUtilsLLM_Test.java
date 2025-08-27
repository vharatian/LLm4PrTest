package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testToURLs_NullFiles() {
        // Test toURLs with null files array
        assertThrows(NullPointerException.class, () -> FileUtils.toURLs((File[]) null));
    }

    @Test
    public void testToURLs_NullFileElement() throws IOException {
        // Test toURLs with an array containing a null element
        File[] files = { new File("file1.txt"), null };
        URL[] urls = FileUtils.toURLs(files);
        assertNotNull(urls);
        assertNotNull(urls[0]);
        assertNotNull(urls[1]);
    }
}