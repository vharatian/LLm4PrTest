package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class SerializationUtilsLLM_Test {

    @Test
    public void testSerializeStreamNullPointerException() {
        assertThrows(NullPointerException.class, () -> SerializationUtils.serialize(null, null));
        assertThrows(NullPointerException.class, () -> SerializationUtils.serialize(null, new ByteArrayOutputStream()));
        assertThrows(NullPointerException.class, () -> SerializationUtils.serialize(new HashMap<>(), null));
    }

    @Test
    public void testDeserializeStreamNullPointerException() {
        assertThrows(NullPointerException.class, () -> SerializationUtils.deserialize((InputStream) null));
        assertThrows(NullPointerException.class, () -> SerializationUtils.deserialize((byte[]) null));
    }
}