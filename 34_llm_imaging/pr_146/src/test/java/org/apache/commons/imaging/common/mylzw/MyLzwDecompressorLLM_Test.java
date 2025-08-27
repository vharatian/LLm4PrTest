package org.apache.commons.imaging.common.mylzw;

import org.apache.commons.imaging.ImageReadException;
import org.junit.jupiter.api.Test;
import java.nio.ByteOrder;
import static org.junit.jupiter.api.Assertions.*;

public class MyLzwDecompressorLLM_Test {

    @Test
    public void testConstructorWithInvalidInitialCodeSize() {
        Exception exception = assertThrows(ImageReadException.class, () -> {
            new MyLzwDecompressor(13, ByteOrder.BIG_ENDIAN);
        });
        String expectedMessage = "Invalid Lzw table length";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testConstructorWithValidInitialCodeSize() {
        assertDoesNotThrow(() -> {
            new MyLzwDecompressor(8, ByteOrder.BIG_ENDIAN);
        });
    }

    @Test
    public void testInitializeTableWithInvalidEntriesCount() {
        Exception exception = assertThrows(ImageReadException.class, () -> {
            MyLzwDecompressor decompressor = new MyLzwDecompressor(12, ByteOrder.BIG_ENDIAN);
        });
        String expectedMessage = "Invalid Lzw table length";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testInitializeTableWithValidEntriesCount() {
        assertDoesNotThrow(() -> {
            MyLzwDecompressor decompressor = new MyLzwDecompressor(8, ByteOrder.BIG_ENDIAN);
        });
    }
}