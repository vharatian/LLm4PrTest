package org.apache.commons.compress.archivers.cpio;

import org.junit.jupiter.api.Test;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CpioArchiveEntryLLM_Test {

    @Test
    public void testSetTimeWithFileTime() {
        // Arrange
        CpioArchiveEntry entry = new CpioArchiveEntry(CpioConstants.FORMAT_NEW);
        FileTime fileTime = FileTime.from(Instant.ofEpochSecond(1625097600L)); // Example Unix time

        // Act
        entry.setTime(fileTime);

        // Assert
        assertEquals(1625097600L, entry.getTime());
    }
}