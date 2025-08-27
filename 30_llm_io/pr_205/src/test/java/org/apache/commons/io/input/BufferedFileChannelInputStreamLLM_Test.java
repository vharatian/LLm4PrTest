package org.apache.commons.io.input;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BufferedFileChannelInputStreamLLM_Test extends AbstractInputStreamTest {

    @SuppressWarnings("resource")
    @Override
    @BeforeEach
    public void setUp() throws IOException {
        super.setUp();
        inputStreams = new InputStream[] {
            new BufferedFileChannelInputStream(inputFile), 
            new BufferedFileChannelInputStream(inputFile, 123)
        };
    }

    @Test
    public void testIsDirectBuffer() throws IOException {
        BufferedFileChannelInputStream stream = new BufferedFileChannelInputStream(inputFile);
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        assertTrue(BufferedFileChannelInputStream.isDirectBuffer(buffer));
        ByteBuffer nonDirectBuffer = ByteBuffer.allocate(10);
        assertFalse(BufferedFileChannelInputStream.isDirectBuffer(nonDirectBuffer));
        stream.close();
    }

    @Test
    public void testCleanDirectBuffer() throws IOException {
        BufferedFileChannelInputStream stream = new BufferedFileChannelInputStream(inputFile);
        ByteBuffer buffer = ByteBuffer.allocateDirect(10);
        assertDoesNotThrow(() -> {
            stream.clean(buffer);
        });
        stream.close();
    }

    @Test
    public void testCleanNonDirectBuffer() throws IOException {
        BufferedFileChannelInputStream stream = new BufferedFileChannelInputStream(inputFile);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        assertDoesNotThrow(() -> {
            stream.clean(buffer);
        });
        stream.close();
    }

    @Test
    public void testGetDirectBufferClass() {
        assertNotNull(BufferedFileChannelInputStream.getDirectBufferClass());
    }
}