package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    @Test
    public void testToByteArray_InputStream_ThresholdOutput() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[1024]);
        byte[] result = IOUtils.toByteArray(inputStream);
        assertTrue(result.length == 1024, "Expected byte array length to be 1024");
    }

    @Test
    public void testToByteArray_InputStream_ThresholdOutput_ExceedsLimit() {
        InputStream inputStream = new ByteArrayInputStream(new byte[Integer.MAX_VALUE]);
        assertThrows(IllegalArgumentException.class, () -> IOUtils.toByteArray(inputStream),
                "Expected IllegalArgumentException when reading more than Integer.MAX_VALUE bytes");
    }
}