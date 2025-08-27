package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class BoundedInputStreamLLM_Test {

    private void compare(final String msg, final byte[] expected, final byte[] actual) {
        assertEquals(expected.length, actual.length, msg + " length");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i], msg + " byte[" + i + "]");
        }
    }

    @Test
    public void testConstructorCommentChange() throws Exception {
        // This test ensures that the constructor comment change does not affect functionality
        BoundedInputStream bounded;
        final byte[] helloWorld = "Hello World".getBytes();
        bounded = new BoundedInputStream(new ByteArrayInputStream(helloWorld), helloWorld.length);
        compare("limit = length", helloWorld, IOUtils.toByteArray(bounded));
    }
}