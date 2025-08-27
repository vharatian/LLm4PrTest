package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileUtilsLLM_Test {

    private File tempDirFile;
    private File testFile1;
    private long testFile1Size;

    @BeforeEach
    public void setUp() throws Exception {
        tempDirFile = Files.createTempDirectory("tempDir").toFile();
        testFile1 = new File(tempDirFile, "file1-test.txt");
        testFile1Size = 1234L;
        if (!testFile1.getParentFile().exists()) {
            throw new IOException("Cannot create file " + testFile1 + " as the parent directory does not exist");
        }
        try (var output = Files.newOutputStream(testFile1.toPath())) {
            output.write(new byte[(int) testFile1Size]);
        }
    }

    @Test
    public void testCopyFileWithPreserveFileDate() throws Exception {
        File destFile = new File(tempDirFile, "copy.txt");
        backDateFile10Minutes(testFile1);
        FileUtils.copyFile(testFile1, destFile, true);
        assertTrue(destFile.exists(), "Check Exist");
        assertEquals(testFile1Size, destFile.length(), "Check Full copy");
        assertEquals(getLastModifiedMillis(testFile1), getLastModifiedMillis(destFile), "Check last modified date preserved");
    }

    @Test
    public void testCopyFileWithoutPreserveFileDate() throws Exception {
        File destFile = new File(tempDirFile, "copy.txt");
        backDateFile10Minutes(testFile1);
        long nowMillis = System.currentTimeMillis() - 1000L;
        FileUtils.copyFile(testFile1, destFile, false);
        assertTrue(destFile.exists(), "Check Exist");
        assertEquals(testFile1Size, destFile.length(), "Check Full copy");
        long destLastModMillis = getLastModifiedMillis(destFile);
        long unexpectedMillis = getLastModifiedMillis(testFile1);
        if (!SystemUtils.IS_OS_WINDOWS) {
            long deltaMillis = destLastModMillis - unexpectedMillis;
            assertNotEquals(unexpectedMillis, destLastModMillis, "Check last modified date not same as input, delta " + deltaMillis);
            assertTrue(destLastModMillis > nowMillis, destLastModMillis + " > " + nowMillis + " (delta " + deltaMillis + ")");
        }
    }

    @Test
    public void testMoveFileWithPreserveFileDate() throws Exception {
        File destFile = new File(tempDirFile, "move.txt");
        backDateFile10Minutes(testFile1);
        long expected = getLastModifiedMillis(testFile1);
        FileUtils.moveFile(testFile1, destFile, StandardCopyOption.COPY_ATTRIBUTES);
        assertTrue(destFile.exists(), "Check Exist");
        assertNotEquals(testFile1.exists(), "Original deleted");
        long destLastMod = getLastModifiedMillis(destFile);
        long delta = destLastMod - expected;
        assertEquals(expected, destLastMod, "Check last modified date same as input, delta " + delta);
    }

    @Test
    public void testMoveFileWithoutPreserveFileDate() throws Exception {
        File destFile = new File(tempDirFile, "move.txt");
        backDateFile10Minutes(testFile1);
        long nowMillis = System.currentTimeMillis() - 1000L;
        long unexpectedMillis = getLastModifiedMillis(testFile1);
        FileUtils.moveFile(testFile1, destFile, PathUtils.EMPTY_COPY_OPTIONS);
        assertTrue(destFile.exists(), "Check Exist");
        assertNotEquals(testFile1.exists(), "Original deleted");
        if (!SystemUtils.IS_OS_WINDOWS) {
            long destLastModMillis = getLastModifiedMillis(destFile);
            long deltaMillis = destLastModMillis - unexpectedMillis;
            assertNotEquals(unexpectedMillis, destLastModMillis, "Check last modified date not same as input, delta " + deltaMillis);
            assertTrue(destLastModMillis > nowMillis, destLastModMillis + " > " + nowMillis + " (delta " + deltaMillis + ")");
        }
    }

    @Test
    public void testSetTimes() throws Exception {
        File destFile = new File(tempDirFile, "dest.txt");
        Files.createFile(destFile.toPath());
        BasicFileAttributes srcAttr = Files.readAttributes(testFile1.toPath(), BasicFileAttributes.class);
        FileUtils.copyFile(testFile1, destFile, true);
        BasicFileAttributes destAttr = Files.readAttributes(destFile.toPath(), BasicFileAttributes.class);
        assertEquals(srcAttr.lastModifiedTime(), destAttr.lastModifiedTime(), "Check last modified time");
        assertEquals(srcAttr.lastAccessTime(), destAttr.lastAccessTime(), "Check last access time");
        assertEquals(srcAttr.creationTime(), destAttr.creationTime(), "Check creation time");
    }

    private void backDateFile10Minutes(File testFile) throws IOException {
        long mins10 = 1000 * 60 * 10;
        long lastModified1 = getLastModifiedMillis(testFile);
        assertTrue(testFile.setLastModified(lastModified1 - mins10));
        assertNotEquals(getLastModifiedMillis(testFile), lastModified1, "Should have changed source date");
    }

    private long getLastModifiedMillis(File file) throws IOException {
        return Files.getLastModifiedTime(file.toPath()).toMillis();
    }
}