package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
    public void testGetDirectBufferClassExceptionHandling() {
        assertDoesNotThrow(() -> {
            Class<?> directBufferClass = BufferedFileChannelInputStream.getDirectBufferClass();
        });
    }
}