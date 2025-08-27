package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test extends AbstractTempDirTest {

    @Test
    public void testVisitFileTreeThrowsNoSuchFileException() {
        Path nonExistentPath = Paths.get("/non/existent/path");
        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        };
        assertThrows(NoSuchFileException.class, () -> PathUtils.visitFileTree(visitor, nonExistentPath));
    }
}