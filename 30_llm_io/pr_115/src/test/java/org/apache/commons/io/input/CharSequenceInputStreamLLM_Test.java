package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class CharSequenceInputStreamLLM_Test {

    /**
     * Test to ensure that the IllegalStateException is thrown with the correct message
     * when the CharBuffer position does not match the marked position.
     */
    @Test
    public void testResetThrowsIllegalStateException() throws IOException {
        CharSequenceInputStream inputStream = new CharSequenceInputStream("test", "UTF-8");
        inputStream.mark(0);
        inputStream.read(); // Read one byte to change the position
        inputStream.reset(); // Reset to the marked position

        // Modify the CharBuffer position to simulate the unexpected position scenario
        inputStream.cbuf.position(inputStream.cbuf.position() + 1);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, inputStream::reset);
        assertEquals("Unexpected CharBuffer position: actual=" + inputStream.cbuf.position() + " expected=" + inputStream.mark_cbuf, thrown.getMessage());
    }
}