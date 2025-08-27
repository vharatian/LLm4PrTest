package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.utils.IOUtils;
import org.junit.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testReadFilesInfoWithNewMapImplementation() throws IOException {
        // Create a test 7z file with known entries
        File testFile = new File("src/test/resources/test.7z");
        Map<String, SevenZArchiveEntry> expectedEntries = new HashMap<>();
        expectedEntries.put("file1.txt", createTestEntry("file1.txt", 100, true));
        expectedEntries.put("file2.txt", createTestEntry("file2.txt", 200, true));
        expectedEntries.put("file3.txt", createTestEntry("file3.txt", 0, false));

        try (SevenZFile sevenZFile = new SevenZFile(testFile)) {
            Iterable<SevenZArchiveEntry> entries = sevenZFile.getEntries();
            for (SevenZArchiveEntry entry : entries) {
                assertTrue("Entry should be present in the expected entries", expectedEntries.containsKey(entry.getName()));
                SevenZArchiveEntry expectedEntry = expectedEntries.get(entry.getName());
                assertEquals("Entry size should match", expectedEntry.getSize(), entry.getSize());
                assertEquals("Entry hasStream should match", expectedEntry.hasStream(), entry.hasStream());
            }
        }
    }

    private SevenZArchiveEntry createTestEntry(String name, long size, boolean hasStream) {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setName(name);
        entry.setSize(size);
        entry.setHasStream(hasStream);
        return entry;
    }

    @Test
    public void testCheckEntryIsInitialized() throws IOException {
        // Create a test 7z file with known entries
        File testFile = new File("src/test/resources/test.7z");

        try (SevenZFile sevenZFile = new SevenZFile(testFile)) {
            // Access private method using reflection
            java.lang.reflect.Method method = SevenZFile.class.getDeclaredMethod("checkEntryIsInitialized", Map.class, int.class);
            method.setAccessible(true);

            Map<Integer, SevenZArchiveEntry> fileMap = new HashMap<>();
            method.invoke(sevenZFile, fileMap, 0);

            assertNotNull("Entry should be initialized", fileMap.get(0));
        } catch (ReflectiveOperationException e) {
            fail("Reflection failed: " + e.getMessage());
        }
    }

    @Test
    public void testReadFilesInfoWithEmptyStream() throws IOException {
        // Create a test 7z file with known entries
        File testFile = new File("src/test/resources/test-empty-stream.7z");

        try (SevenZFile sevenZFile = new SevenZFile(testFile)) {
            Iterable<SevenZArchiveEntry> entries = sevenZFile.getEntries();
            for (SevenZArchiveEntry entry : entries) {
                if (entry.getName().equals("empty.txt")) {
                    assertFalse("Entry should not have a stream", entry.hasStream());
                }
            }
        }
    }

    @Test
    public void testReadFilesInfoWithAttributes() throws IOException {
        // Create a test 7z file with known entries
        File testFile = new File("src/test/resources/test-attributes.7z");

        try (SevenZFile sevenZFile = new SevenZFile(testFile)) {
            Iterable<SevenZArchiveEntry> entries = sevenZFile.getEntries();
            for (SevenZArchiveEntry entry : entries) {
                if (entry.getName().equals("file-with-attributes.txt")) {
                    assertTrue("Entry should have Windows attributes", entry.getHasWindowsAttributes());
                    assertEquals("Windows attributes should match", 0x20, entry.getWindowsAttributes());
                }
            }
        }
    }

    @Test
    public void testReadFilesInfoWithTimes() throws IOException {
        // Create a test 7z file with known entries
        File testFile = new File("src/test/resources/test-times.7z");

        try (SevenZFile sevenZFile = new SevenZFile(testFile)) {
            Iterable<SevenZArchiveEntry> entries = sevenZFile.getEntries();
            for (SevenZArchiveEntry entry : entries) {
                if (entry.getName().equals("file-with-times.txt")) {
                    assertTrue("Entry should have creation date", entry.getHasCreationDate());
                    assertTrue("Entry should have access date", entry.getHasAccessDate());
                    assertTrue("Entry should have last modified date", entry.getHasLastModifiedDate());
                }
            }
        }
    }
}