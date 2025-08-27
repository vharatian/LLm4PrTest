package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test extends AbstractTempDirTest {

    @Test
    public void testFilterNullPointerException() {
        assertThrows(NullPointerException.class, () -> PathUtils.filter(null, Paths.get("test")));
    }

    @Test
    public void testFilterIllegalArgumentException() {
        assertThrowsExactly(IllegalArgumentException.class, () -> PathUtils.filter(path -> true, (Path) null));
    }

    @Test
    public void testVisitFileTreeNullPointerException() {
        assertThrows(NullPointerException.class, () -> PathUtils.visitFileTree(new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        }, (Path) null));
    }

    @Test
    public void testWriteStringNullPointerException() {
        assertThrows(NullPointerException.class, () -> PathUtils.writeString(null, "test", null));
        assertThrows(NullPointerException.class, () -> PathUtils.writeString(Paths.get("test"), null, null));
    }
}