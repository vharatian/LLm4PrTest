package org.apache.commons.io;

import org.apache.commons.io.file.Counters;
import org.apache.commons.io.file.PathUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileUtilsLLM_Test {

    @Test
    public void testForceDeleteFile() throws IOException {
        File file = mock(File.class);
        Path filePath = mock(Path.class);
        Counters.PathCounters pathCounters = mock(Counters.PathCounters.class);
        Counters.Counter fileCounter = mock(Counters.Counter.class);
        Counters.Counter dirCounter = mock(Counters.Counter.class);

        when(file.toPath()).thenReturn(filePath);
        when(PathUtils.delete(filePath)).thenReturn(pathCounters);
        when(pathCounters.getFileCounter()).thenReturn(fileCounter);
        when(pathCounters.getDirectoryCounter()).thenReturn(dirCounter);
        when(fileCounter.get()).thenReturn(1L);
        when(dirCounter.get()).thenReturn(0L);

        FileUtils.forceDelete(file);

        verify(PathUtils).delete(filePath);
    }

    @Test
    public void testForceDeleteDirectory() throws IOException {
        File directory = mock(File.class);
        Path dirPath = mock(Path.class);
        Counters.PathCounters pathCounters = mock(Counters.PathCounters.class);
        Counters.Counter fileCounter = mock(Counters.Counter.class);
        Counters.Counter dirCounter = mock(Counters.Counter.class);

        when(directory.toPath()).thenReturn(dirPath);
        when(PathUtils.delete(dirPath)).thenReturn(pathCounters);
        when(pathCounters.getFileCounter()).thenReturn(fileCounter);
        when(pathCounters.getDirectoryCounter()).thenReturn(dirCounter);
        when(fileCounter.get()).thenReturn(0L);
        when(dirCounter.get()).thenReturn(1L);

        FileUtils.forceDelete(directory);

        verify(PathUtils).delete(dirPath);
    }

    @Test
    public void testForceDeleteNonExistentFile() {
        File file = mock(File.class);
        Path filePath = mock(Path.class);

        when(file.toPath()).thenReturn(filePath);
        when(PathUtils.delete(filePath)).thenThrow(new IOException("File not found"));

        FileNotFoundException thrown = assertThrows(FileNotFoundException.class, () -> {
            FileUtils.forceDelete(file);
        });

        assertEquals("File does not exist: " + file, thrown.getMessage());
    }

    @Test
    public void testForceDeleteIOException() {
        File file = mock(File.class);
        Path filePath = mock(Path.class);

        when(file.toPath()).thenReturn(filePath);
        when(PathUtils.delete(filePath)).thenThrow(new IOException("Unable to delete"));

        IOException thrown = assertThrows(IOException.class, () -> {
            FileUtils.forceDelete(file);
        });

        assertEquals("Unable to delete file: " + file, thrown.getMessage());
    }
}