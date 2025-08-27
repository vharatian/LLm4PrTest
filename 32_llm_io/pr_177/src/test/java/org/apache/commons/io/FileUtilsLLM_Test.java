package org.apache.commons.io;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileUtilsLLM_Test {

    @Test
    public void testByteCountToDisplaySize() {
        // Test case for size in EB
        assertEquals("1 EB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_EB)));
        assertEquals("2 EB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_EB * 2)));

        // Test case for size in PB
        assertEquals("1 PB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_PB)));
        assertEquals("2 PB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_PB * 2)));

        // Test case for size in TB
        assertEquals("1 TB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_TB)));
        assertEquals("2 TB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_TB * 2)));

        // Test case for size in GB
        assertEquals("1 GB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_GB)));
        assertEquals("2 GB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_GB * 2)));

        // Test case for size in MB
        assertEquals("1 MB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_MB)));
        assertEquals("2 MB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_MB * 2)));

        // Test case for size in KB
        assertEquals("1 KB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_KB)));
        assertEquals("2 KB", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(FileUtils.ONE_KB * 2)));

        // Test case for size in bytes
        assertEquals("1 bytes", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(1)));
        assertEquals("1023 bytes", FileUtils.byteCountToDisplaySize(BigInteger.valueOf(1023)));
    }
}