package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class BoundedInputStreamLLM_Test {

    @Test
    public void testInitialPosition() throws Exception {
        // Test the initial position of the BoundedInputStream
        BoundedInputStream bounded;
        final byte[] helloWorld = "Hello World".getBytes();
        
        // When max is specified
        bounded = new BoundedInputStream(new ByteArrayInputStream(helloWorld), helloWorld.length);
        assertEquals(0, bounded.pos, "Initial position should be 0 when max is specified");

        // When max is not specified
        bounded = new BoundedInputStream(new ByteArrayInputStream(helloWorld));
        assertEquals(0, bounded.pos, "Initial position should be 0 when max is not specified");
    }
}