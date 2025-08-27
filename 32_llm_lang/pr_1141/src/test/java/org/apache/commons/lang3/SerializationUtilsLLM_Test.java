package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.junit.jupiter.api.Test;

public class SerializationUtilsLLM_Test {

    @Test
    public void testDeserializeStreamNegativeArraySizeException() throws Exception {
        // Create a byte array that will cause a NegativeArraySizeException when deserialized
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(streamReal)) {
            oos.writeInt(-1); // Writing an invalid size
            oos.flush();
        }
        final ByteArrayInputStream inTest = new ByteArrayInputStream(streamReal.toByteArray());
        assertThrows(SerializationException.class, () -> SerializationUtils.deserialize(inTest));
    }
}