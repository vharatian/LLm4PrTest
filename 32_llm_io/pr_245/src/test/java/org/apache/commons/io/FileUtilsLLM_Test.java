package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileUtilsLLM_Test {

    @Test
    public void testDeleteDirectoryThrowsIllegalArgumentExceptionWhenNotADirectory() {
        File notADirectory = new File("notADirectory.txt");
        assertThrows(IllegalArgumentException.class, () -> {
            FileUtils.deleteDirectory(notADirectory);
        });
    }

    @Test
    public void testDeleteDirectoryThrowsNullPointerExceptionWhenDirectoryIsNull() {
        assertThrows(NullPointerException.class, () -> {
            FileUtils.deleteDirectory(null);
        });
    }
}