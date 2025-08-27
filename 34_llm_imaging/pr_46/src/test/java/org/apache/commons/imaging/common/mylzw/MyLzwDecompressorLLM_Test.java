package org.apache.commons.imaging.common.mylzw;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class MyLzwDecompressorLLM_Test {

    @Test
    public void testAddStringToTableWhenTableIsFull() throws IOException {
        MyLzwDecompressor decompressor = new MyLzwDecompressor(8, ByteOrder.LITTLE_ENDIAN);
        decompressor.setTiffLZWMode();
        
        // Simulate filling the table
        for (int i = 0; i < (1 << 12); i++) {
            decompressor.addStringToTable(new byte[]{(byte) i});
        }

        // Try to add another string to the table, which should be ignored
        try {
            decompressor.addStringToTable(new byte[]{(byte) 0});
        } catch (IOException e) {
            fail("IOException should not be thrown when the table is full");
        }
    }

    @Test
    public void testDecompressIgnoresExtraBytesWhenTableIsFull() throws IOException {
        byte[] compressedData = {0x00, 0x01, 0x02, 0x03}; // Example compressed data
        ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
        MyLzwDecompressor decompressor = new MyLzwDecompressor(8, ByteOrder.LITTLE_ENDIAN);
        decompressor.setTiffLZWMode();
        
        // Simulate filling the table
        for (int i = 0; i < (1 << 12); i++) {
            decompressor.addStringToTable(new byte[]{(byte) i});
        }

        // Decompress and verify no exception is thrown
        try {
            byte[] result = decompressor.decompress(inputStream, 4);
            assertNotNull(result);
        } catch (IOException e) {
            fail("IOException should not be thrown during decompression when the table is full");
        }
    }
}