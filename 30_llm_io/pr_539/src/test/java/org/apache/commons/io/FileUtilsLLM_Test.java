package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.File;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    @Test
    public void testToFileDecoding() throws Exception {
        // Test for URL decoding to File path
        URL url = new URL("file", null, "a/b/c/file%20name.txt");
        File file = FileUtils.toFile(url);
        assertEquals("a/b/c/file name.txt", file.toString());

        // Test for URL decoding with UTF-8 characters
        url = new URL("file", null, "/home/%C3%A4%C3%B6%C3%BC%C3%9F");
        file = FileUtils.toFile(url);
        assertEquals("/home/äöüß", file.toString());

        // Test for null URL
        assertNull(FileUtils.toFile(null));
    }
}