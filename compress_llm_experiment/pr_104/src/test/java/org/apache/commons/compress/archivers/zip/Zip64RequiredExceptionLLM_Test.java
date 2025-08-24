package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Zip64RequiredExceptionLLM_Test {

    @Test
    public void testNumberOfThisDiskTooBigMessage() {
        assertEquals("Number of the disk of End Of Central Directory exceeds the limit of 65535.",
                Zip64RequiredException.NUMBER_OF_THIS_DISK_TOO_BIG_MESSAGE);
    }

    @Test
    public void testNumberOfTheDiskOfCentralDirectoryTooBigMessage() {
        assertEquals("Number of the disk with the start of Central Directory exceeds the limit of 65535.",
                Zip64RequiredException.NUMBER_OF_THE_DISK_OF_CENTRAL_DIRECTORY_TOO_BIG_MESSAGE);
    }

    @Test
    public void testTooManyEntriesOnThisDiskMessage() {
        assertEquals("Number of entries on this disk exceeds the limit of 65535.",
                Zip64RequiredException.TOO_MANY_ENTRIES_ON_THIS_DISK_MESSAGE);
    }
}