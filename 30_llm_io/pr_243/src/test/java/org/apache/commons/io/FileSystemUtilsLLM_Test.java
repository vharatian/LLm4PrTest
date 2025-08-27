package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.Duration;

public class FileSystemUtilsLLM_Test {

    @Test
    public void testFreeSpaceOSWithUnsupportedOS() {
        FileSystemUtils fsUtils = new FileSystemUtils();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fsUtils.freeSpaceOS("/some/path", 0, false, Duration.ofMillis(-1));
        });
        assertEquals("Unsupported operating system", exception.getMessage());
    }

    @Test
    public void testFreeSpaceOSWithInitProblem() {
        FileSystemUtils fsUtils = new FileSystemUtils();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fsUtils.freeSpaceOS("/some/path", -1, false, Duration.ofMillis(-1));
        });
        assertEquals("Exception caught when determining operating system", exception.getMessage());
    }
}