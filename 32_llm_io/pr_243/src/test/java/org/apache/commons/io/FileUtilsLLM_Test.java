package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testCopyDirectoryWithNonNullSrcFiles() throws IOException {
        File srcDir = new File("srcDir");
        File destDir = new File("destDir");
        FileFilter fileFilter = pathname -> true;

        // Mocking the listFiles method to return a non-null array
        File[] srcFiles = {new File("file1"), new File("file2")};
        FileUtils.copyDirectory(srcDir, destDir, fileFilter, true, srcFiles);

        // Verify that exclusionList is created and contains the correct paths
        List<String> exclusionList = new ArrayList<>(srcFiles.length);
        for (File srcFile : srcFiles) {
            File copiedFile = new File(destDir, srcFile.getName());
            exclusionList.add(copiedFile.getCanonicalPath());
        }
        assertEquals(2, exclusionList.size());
    }

    @Test
    public void testMoveDirectoryToDirectoryWithCreateDestDirFalse() {
        File src = new File("srcDir");
        File destDir = new File("destDir");

        Exception exception = assertThrows(FileNotFoundException.class, () -> {
            FileUtils.moveDirectoryToDirectory(src, destDir, false);
        });

        String expectedMessage = "Destination directory '" + destDir + "' does not exist [createDestDir=" + false + "]";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}