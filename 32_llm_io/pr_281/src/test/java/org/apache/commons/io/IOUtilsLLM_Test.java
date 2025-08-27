package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.input.QueueInputStream;
import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    @Test
    public void testCopy_ByteArrayOutputStreamToQueueInputStream() throws IOException {
        // Create a ByteArrayOutputStream and write some data to it
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write("hello world".getBytes(StandardCharsets.UTF_8));

        // Use the new copy method to create a QueueInputStream
        QueueInputStream inputStream = IOUtils.copy(outputStream);

        // Read the data from the QueueInputStream and verify it matches the original data
        byte[] expectedData = "hello world".getBytes(StandardCharsets.UTF_8);
        byte[] actualData = new byte[expectedData.length];
        int bytesRead = inputStream.read(actualData);

        assertEquals(expectedData.length, bytesRead, "Number of bytes read should match the expected data length");
        assertArrayEquals(expectedData, actualData, "Data read from QueueInputStream should match the original data");
    }

    @Test
    public void testCopy_ByteArrayOutputStreamToQueueInputStream_NullOutputStream() {
        // Verify that passing a null ByteArrayOutputStream throws a NullPointerException
        assertThrows(NullPointerException.class, () -> IOUtils.copy((ByteArrayOutputStream) null));
    }

    @Test
    public void testCopy_ByteArrayOutputStreamToQueueInputStream_EmptyOutputStream() throws IOException {
        // Create an empty ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Use the new copy method to create a QueueInputStream
        QueueInputStream inputStream = IOUtils.copy(outputStream);

        // Verify that the QueueInputStream is empty
        assertEquals(-1, inputStream.read(), "QueueInputStream should be empty");
    }

    @Test
    public void testCopy_ByteArrayOutputStreamToQueueInputStream_LargeData() throws IOException {
        // Create a ByteArrayOutputStream and write a large amount of data to it
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] largeData = new byte[1024 * 1024]; // 1 MB of data
        outputStream.write(largeData);

        // Use the new copy method to create a QueueInputStream
        QueueInputStream inputStream = IOUtils.copy(outputStream);

        // Read the data from the QueueInputStream and verify it matches the original data
        byte[] actualData = new byte[largeData.length];
        int bytesRead = inputStream.read(actualData);

        assertEquals(largeData.length, bytesRead, "Number of bytes read should match the large data length");
        assertArrayEquals(largeData, actualData, "Data read from QueueInputStream should match the large data");
    }

    @Test
    public void testCopy_ByteArrayOutputStreamToQueueInputStream_ExceptionHandling() {
        // Create a ByteArrayOutputStream that throws an IOException when written to
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream() {
            @Override
            public void writeTo(OutputStream out) throws IOException {
                throw new IOException("Test exception");
            }
        };

        // Verify that the copy method throws an IOException
        assertThrows(IOException.class, () -> IOUtils.copy(outputStream));
    }
}