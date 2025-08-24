package org.apache.commons.compress.harmony.pack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackingUtilsLLM_Test {

    private PackingUtils packingUtils;

    @BeforeEach
    public void setUp() {
        packingUtils = new PackingUtils();
    }

    @Test
    public void testReorderPackingFiles() throws IOException {
        List<PackingUtils.PackingFile> packingFileList = new ArrayList<>();
        packingFileList.add(new PackingUtils.PackingFile("META-INF/MANIFEST.MF", new byte[0], 0));
        packingFileList.add(new PackingUtils.PackingFile("B.class", new byte[0], 0));
        packingFileList.add(new PackingUtils.PackingFile("A.class", new byte[0], 0));

        PackingUtils.reorderPackingFiles(packingFileList);

        assertEquals("META-INF/MANIFEST.MF", packingFileList.get(0).getName());
        assertEquals("A.class", packingFileList.get(1).getName());
        assertEquals("B.class", packingFileList.get(2).getName());
    }

    @Test
    public void testReorderPackingFilesWithDirectories() throws IOException {
        List<PackingUtils.PackingFile> packingFileList = new ArrayList<>();
        packingFileList.add(new PackingUtils.PackingFile("META-INF/MANIFEST.MF", new byte[0], 0));
        packingFileList.add(new PackingUtils.PackingFile("dir/", new byte[0], 0, true));
        packingFileList.add(new PackingUtils.PackingFile("A.class", new byte[0], 0));

        PackingUtils.reorderPackingFiles(packingFileList);

        assertEquals(2, packingFileList.size());
        assertEquals("META-INF/MANIFEST.MF", packingFileList.get(0).getName());
        assertEquals("A.class", packingFileList.get(1).getName());
    }

    @Test
    public void testReorderPackingFilesWithInvalidEntries() {
        List<Object> packingFileList = new ArrayList<>();
        packingFileList.add("InvalidEntry");

        assertThrows(IllegalArgumentException.class, () -> {
            PackingUtils.reorderPackingFiles(packingFileList);
        });
    }
}