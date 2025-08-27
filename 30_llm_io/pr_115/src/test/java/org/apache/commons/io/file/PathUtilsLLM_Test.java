package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test {

    @Test
    public void testDirectoryAndFileContentEqualsUnexpectedMismatch() throws Exception {
        Path path1 = Paths.get("src/test/resources/org/apache/commons/io/dirs-1");
        Path path2 = Paths.get("src/test/resources/org/apache/commons/io/dirs-2");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            PathUtils.directoryAndFileContentEquals(path1, path2);
        });

        assertEquals("Unexpected mismatch.", exception.getMessage());
    }
}