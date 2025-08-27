package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    @Test
    public void testContentEquals_InputStream_InputStream_NullInputs() throws IOException {
        // Test when both inputs are null
        assertTrue(IOUtils.contentEquals(null, null), "Both null inputs should be considered equal");

        // Test when one input is null and the other is not
        final ByteArrayInputStream input1 = new ByteArrayInputStream("data".getBytes(StandardCharsets.UTF_8));
        assertFalse(IOUtils.contentEquals(input1, null), "One null input should not be considered equal to a non-null input");
        assertFalse(IOUtils.contentEquals(null, input1), "One null input should not be considered equal to a non-null input");
    }
}