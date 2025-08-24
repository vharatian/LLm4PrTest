package org.apache.commons.compress.compressors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

public class FileNameUtilLLM_Test {

    @Test
    public void testGetExtensionWithNull() {
        assertNull(FileNameUtil.getExtension(null));
    }

    @Test
    public void testGetExtensionWithNoExtension() {
        assertEquals("", FileNameUtil.getExtension("file"));
    }

    @Test
    public void testGetExtensionWithExtension() {
        assertEquals("txt", FileNameUtil.getExtension("file.txt"));
    }

    @Test
    public void testGetExtensionWithMultipleDots() {
        assertEquals("gz", FileNameUtil.getExtension("archive.tar.gz"));
    }

    @Test
    public void testGetExtensionWithPath() {
        assertEquals("txt", FileNameUtil.getExtension("/path/to/file.txt"));
    }

    @Test
    public void testGetExtensionWithPathAndNoExtension() {
        assertEquals("", FileNameUtil.getExtension("/path/to/file"));
    }

    @Test
    public void testGetBaseNameWithNull() {
        assertNull(FileNameUtil.getBaseName(null));
    }

    @Test
    public void testGetBaseNameWithNoExtension() {
        assertEquals("file", FileNameUtil.getBaseName("file"));
    }

    @Test
    public void testGetBaseNameWithExtension() {
        assertEquals("file", FileNameUtil.getBaseName("file.txt"));
    }

    @Test
    public void testGetBaseNameWithMultipleDots() {
        assertEquals("archive.tar", FileNameUtil.getBaseName("archive.tar.gz"));
    }

    @Test
    public void testGetBaseNameWithPath() {
        assertEquals("file", FileNameUtil.getBaseName("/path/to/file.txt"));
    }

    @Test
    public void testGetBaseNameWithPathAndNoExtension() {
        assertEquals("file", FileNameUtil.getBaseName("/path/to/file"));
    }

    @Test
    public void testGetBaseNameWithPathUsingFileSeparator() {
        assertEquals("file", FileNameUtil.getBaseName("C:\\path\\to\\file.txt"));
    }

    @Test
    public void testGetBaseNameWithPathAndNoExtensionUsingFileSeparator() {
        assertEquals("file", FileNameUtil.getBaseName("C:\\path\\to\\file"));
    }
}