package org.apache.commons.compress.archivers.tar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TarArchiveOutputStreamLLM_Test {

    @Test
    public void testEncodeExtendedPaxHeadersContents() throws Exception {
        // Create a map with a key-value pair that will require multiple passes to determine the correct length
        final Map<String, String> headers = new HashMap<>();
        headers.put("key", "value");

        // Create an instance of TarArchiveOutputStream using a ByteArrayOutputStream
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (TarArchiveOutputStream tos = new TarArchiveOutputStream(bos)) {
            // Use reflection to access the private method encodeExtendedPaxHeadersContents
            java.lang.reflect.Method method = TarArchiveOutputStream.class.getDeclaredMethod("encodeExtendedPaxHeadersContents", Map.class);
            method.setAccessible(true);

            // Invoke the method and get the result
            byte[] result = (byte[]) method.invoke(tos, headers);

            // Verify the result
            String expected = "11 key=value\n";
            assertEquals(expected, new String(result, UTF_8));
        }
    }
}