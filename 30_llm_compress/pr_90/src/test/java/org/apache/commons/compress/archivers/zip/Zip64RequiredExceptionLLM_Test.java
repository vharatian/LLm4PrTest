package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Zip64RequiredExceptionLLM_Test {

    @Test
    public void testGetEntryTooBigMessage() {
        ZipArchiveEntry entry = new ZipArchiveEntry("testEntry");
        String expectedMessage = "testEntry's size exceeds the limit of 4GByte.";
        assertEquals(expectedMessage, Zip64RequiredException.getEntryTooBigMessage(entry));
    }

    @Test
    public void testNumberOfThisDiskTooBigMessage() {
        String expectedMessage = "Number of the disk of End Of Central Directory exceeds the limmit of 65535.";
        assertEquals(expectedMessage, Zip64RequiredException.NUMBER_OF_THIS_DISK_TOO_BIG_MESSAGE);
    }

    @Test
    public void testNumberOfTheDiskOfCentralDirectoryTooBigMessage() {
        String expectedMessage = "Number of the disk with the start of Central Directory exceeds the limmit of 65535.";
        assertEquals(expectedMessage, Zip64RequiredException.NUMBER_OF_THE_DISK_OF_CENTRAL_DIRECTORY_TOO_BIG_MESSAGE);
    }

    @Test
    public void testTooManyEntriesOnThisDiskMessage() {
        String expectedMessage = "Number of entries on this disk exceeds the limmit of 65535.";
        assertEquals(expectedMessage, Zip64RequiredException.TOO_MANY_ENTRIES_ON_THIS_DISK_MESSAGE);
    }

    @Test
    public void testSizeOfCentralDirectoryTooBigMessage() {
        String expectedMessage = "The size of the entire central directory exceeds the limit of 4GByte.";
        assertEquals(expectedMessage, Zip64RequiredException.SIZE_OF_CENTRAL_DIRECTORY_TOO_BIG_MESSAGE);
    }

    @Test
    public void testArchiveTooBigMessage() {
        String expectedMessage = "Archive's size exceeds the limit of 4GByte.";
        assertEquals(expectedMessage, Zip64RequiredException.ARCHIVE_TOO_BIG_MESSAGE);
    }

    @Test
    public void testTooManyEntriesMessage() {
        String expectedMessage = "Archive contains more than 65535 entries.";
        assertEquals(expectedMessage, Zip64RequiredException.TOO_MANY_ENTRIES_MESSAGE);
    }

    @Test
    public void testConstructor() {
        String reason = "Test reason";
        Zip64RequiredException exception = new Zip64RequiredException(reason);
        assertEquals(reason, exception.getMessage());
    }
}