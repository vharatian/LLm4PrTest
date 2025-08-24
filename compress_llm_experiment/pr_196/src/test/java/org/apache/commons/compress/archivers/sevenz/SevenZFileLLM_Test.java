package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testSevenZFileWithSeekableByteChannelAndOptions() throws IOException {
        // Test the constructor with SeekableByteChannel and SevenZFileOptions
        File file = new File("path/to/test/file.7z");
        try (SevenZFile sevenZFile = new SevenZFile(Files.newByteChannel(file.toPath()), SevenZFileOptions.DEFAULT)) {
            assertNull(sevenZFile.getDefaultName());
        }
    }

    @Test
    public void testSevenZFileWithFileAndOptions() throws IOException {
        // Test the constructor with File and SevenZFileOptions
        File file = new File("path/to/test/file.7z");
        try (SevenZFile sevenZFile = new SevenZFile(file, SevenZFileOptions.DEFAULT)) {
            assertEquals("file", sevenZFile.getDefaultName());
        }
    }
}