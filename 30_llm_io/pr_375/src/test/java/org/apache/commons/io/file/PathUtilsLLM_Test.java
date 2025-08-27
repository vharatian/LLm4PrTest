package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test {

    @Test
    public void testGetLastModifiedFileTime_URL_URISyntaxException() {
        URL malformedUrl = new URL("http://example.com/invalid url");
        assertThrows(URISyntaxException.class, () -> {
            PathUtils.getLastModifiedFileTime(malformedUrl);
        });
    }

    @Test
    public void testGetLastModifiedFileTime_URL_Valid() throws IOException, URISyntaxException {
        URL validUrl = new URL("http://example.com");
        assertNotNull(PathUtils.getLastModifiedFileTime(validUrl));
    }
}