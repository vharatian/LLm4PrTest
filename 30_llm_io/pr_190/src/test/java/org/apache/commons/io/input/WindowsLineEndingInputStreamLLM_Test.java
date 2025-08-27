package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class WindowsLineEndingInputStreamLLM_Test {

    @Test
    public void testInitialValues() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        WindowsLineEndingInputStream stream = new WindowsLineEndingInputStream(input, true);

        // Accessing private fields via reflection to check initial values
        assertEquals(false, getPrivateField(stream, "slashRSeen"));
        assertEquals(false, getPrivateField(stream, "slashNSeen"));
        assertEquals(false, getPrivateField(stream, "injectSlashN"));
        assertEquals(false, getPrivateField(stream, "eofSeen"));

        stream.close();
    }

    private Object getPrivateField(Object object, String fieldName) {
        try {
            java.lang.reflect.Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}