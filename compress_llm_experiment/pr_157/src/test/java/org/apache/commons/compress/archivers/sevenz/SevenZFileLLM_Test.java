package org.apache.commons.compress.archivers.sevenz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SevenZFileLLM_Test {

    @Test
    public void testReadFilesInfoWithValidNames() throws IOException {
        // Prepare a mock ByteBuffer with valid file names
        ByteBuffer header = ByteBuffer.allocate(100).order(ByteOrder.LITTLE_ENDIAN);
        header.put((byte) NID.kName);
        header.putLong(18); // size of the names
        header.put("test1.txt\0test2.txt\0".getBytes(StandardCharsets.UTF_16LE));
        header.put((byte) 0); // end of properties
        header.flip();

        Archive archive = new Archive();
        SevenZFile sevenZFile = new SevenZFile(null);
        sevenZFile.readFilesInfo(header, archive);

        // Validate the parsed file names
        assertEquals(2, archive.files.length);
        assertEquals("test1.txt", archive.files[0].getName());
        assertEquals("test2.txt", archive.files[1].getName());
    }

    @Test
    public void testReadFilesInfoWithInvalidNames() {
        // Prepare a mock ByteBuffer with invalid file names
        ByteBuffer header = ByteBuffer.allocate(100).order(ByteOrder.LITTLE_ENDIAN);
        header.put((byte) NID.kName);
        header.putLong(17); // invalid size of the names
        header.put("test1.txt\0test2.txt".getBytes(StandardCharsets.UTF_16LE)); // missing null terminator
        header.put((byte) 0); // end of properties
        header.flip();

        Archive archive = new Archive();
        SevenZFile sevenZFile = new SevenZFile(null);

        try {
            sevenZFile.readFilesInfo(header, archive);
            fail("Expected IOException due to invalid file names");
        } catch (IOException e) {
            assertEquals("Error parsing file names", e.getMessage());
        }
    }
}