package org.apache.commons.io.file.spi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;

import org.junit.jupiter.api.Test;

public class FileSystemProvidersLLM_Test {

    private static final String FILE_PATH = "file:///";

    @Test
    public void testGetFileSystemProvider_all() throws URISyntaxException {
        for (final FileSystemProvider fileSystemProvider : FileSystemProvider.installedProviders()) {
            final String scheme = fileSystemProvider.getScheme();
            final URI uri = new URI(scheme, "ssp", "fragment");
            assertEquals(scheme, FileSystemProviders.installed().getFileSystemProvider(uri).getScheme());
        }
    }

    @Test
    public void testGetFileSystemProvider_filePath() {
        assertNotNull(FileSystemProviders.getFileSystemProvider(Paths.get(URI.create(FILE_PATH))));
    }

    @Test
    public void testGetFileSystemProvider_fileScheme() {
        assertNotNull(FileSystemProviders.installed().getFileSystemProvider("file"));
    }

    @Test
    public void testGetFileSystemProvider_fileURI() {
        assertNotNull(FileSystemProviders.installed().getFileSystemProvider(URI.create(FILE_PATH)));
    }

    @Test
    public void testGetFileSystemProvider_fileURL() throws MalformedURLException {
        assertNotNull(FileSystemProviders.installed().getFileSystemProvider(new URL(FILE_PATH)));
    }
}