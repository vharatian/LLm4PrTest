package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testDoCopyDirectoryWithPreserveDirDate() throws IOException {
        File srcDir = new File("src/test/resources/sourceDir");
        File destDir = new File("src/test/resources/destDir");
        FileFilter fileFilter = pathname -> true;
        boolean preserveDirDate = true;
        CopyOption[] copyOptions = {StandardCopyOption.COPY_ATTRIBUTES};

        FileUtils.copyDirectory(srcDir, destDir, fileFilter, preserveDirDate, copyOptions);

        assertTrue(destDir.exists());
        assertTrue(destDir.isDirectory());
        assertEquals(srcDir.lastModified(), destDir.lastModified());
    }

    @Test
    public void testDoCopyDirectoryWithoutPreserveDirDate() throws IOException {
        File srcDir = new File("src/test/resources/sourceDir");
        File destDir = new File("src/test/resources/destDir");
        FileFilter fileFilter = pathname -> true;
        boolean preserveDirDate = false;
        CopyOption[] copyOptions = {StandardCopyOption.COPY_ATTRIBUTES};

        FileUtils.copyDirectory(srcDir, destDir, fileFilter, preserveDirDate, copyOptions);

        assertTrue(destDir.exists());
        assertTrue(destDir.isDirectory());
        assertNotEquals(srcDir.lastModified(), destDir.lastModified());
    }

    @Test
    public void testListFilesWithFinalFileFilter() throws IOException {
        File directory = new File("src/test/resources");
        FileFilter fileFilter = pathname -> pathname.getName().endsWith(".txt");

        File[] files = FileUtils.listFiles(directory, fileFilter);

        assertNotNull(files);
        for (File file : files) {
            assertTrue(file.getName().endsWith(".txt"));
        }
    }

    @Test
    public void testRequireAbsentWithFinalName() {
        File file = new File("src/test/resources/existingFile.txt");
        String name = "existingFile";

        assertThrows(FileExistsException.class, () -> FileUtils.requireAbsent(file, name));
    }

    @Test
    public void testRequireCanWriteWithFinalName() {
        File file = new File("src/test/resources/writableFile.txt");
        String name = "writableFile";

        assertDoesNotThrow(() -> FileUtils.requireCanWrite(file, name));
    }

    @Test
    public void testRequireDirectoryWithFinalName() {
        File directory = new File("src/test/resources/existingDirectory");
        String name = "existingDirectory";

        assertDoesNotThrow(() -> FileUtils.requireDirectory(directory, name));
    }

    @Test
    public void testRequireDirectoryExistsWithFinalName() {
        File directory = new File("src/test/resources/existingDirectory");
        String name = "existingDirectory";

        assertDoesNotThrow(() -> FileUtils.requireDirectoryExists(directory, name));
    }

    @Test
    public void testRequireDirectoryIfExistsWithFinalName() {
        File directory = new File("src/test/resources/existingDirectory");
        String name = "existingDirectory";

        assertDoesNotThrow(() -> FileUtils.requireDirectoryIfExists(directory, name));
    }

    @Test
    public void testRequireExistsWithFinalFileParamName() {
        File file = new File("src/test/resources/existingFile.txt");
        String fileParamName = "existingFile";

        assertDoesNotThrow(() -> FileUtils.requireExists(file, fileParamName));
    }

    @Test
    public void testRequireExistsCheckedWithFinalFileParamName() throws IOException {
        File file = new File("src/test/resources/existingFile.txt");
        String fileParamName = "existingFile";

        assertDoesNotThrow(() -> FileUtils.requireExistsChecked(file, fileParamName));
    }

    @Test
    public void testRequireFileWithFinalName() {
        File file = new File("src/test/resources/existingFile.txt");
        String name = "existingFile";

        assertDoesNotThrow(() -> FileUtils.requireFile(file, name));
    }

    @Test
    public void testRequireFileIfExistsWithFinalName() {
        File file = new File("src/test/resources/existingFile.txt");
        String name = "existingFile";

        assertDoesNotThrow(() -> FileUtils.requireFileIfExists(file, name));
    }
}