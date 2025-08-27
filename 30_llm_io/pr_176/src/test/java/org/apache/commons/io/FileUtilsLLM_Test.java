package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class FileUtilsLLM_Test {

    @Test
    public void testByteCountToDisplaySize() {
        assertEquals("1 EB", FileUtils.byteCountToDisplaySize(FileUtils.ONE_EB_BI));
        assertEquals("1 PB", FileUtils.byteCountToDisplaySize(FileUtils.ONE_PB_BI));
        assertEquals("1 TB", FileUtils.byteCountToDisplaySize(FileUtils.ONE_TB_BI));
        assertEquals("1 GB", FileUtils.byteCountToDisplaySize(FileUtils.ONE_GB_BI));
        assertEquals("1 MB", FileUtils.byteCountToDisplaySize(FileUtils.ONE_MB_BI));
        assertEquals("1 KB", FileUtils.byteCountToDisplaySize(FileUtils.ONE_KB_BI));
        assertEquals("1 bytes", FileUtils.byteCountToDisplaySize(BigInteger.ONE));
    }

    @Test
    public void testStreamFiles() throws IOException {
        File directory = new File("src/test/resources");
        Stream<File> fileStream = FileUtils.streamFiles(directory, true, "txt");
        assertNotNull(fileStream);
        assertTrue(fileStream.anyMatch(file -> file.getName().endsWith(".txt")));
    }
}