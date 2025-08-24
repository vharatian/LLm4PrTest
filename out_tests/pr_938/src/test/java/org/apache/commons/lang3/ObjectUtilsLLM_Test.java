package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import java.io.Serializable;

public class ObjectUtilsLLM_Test {

    @Test
    public void testNullSingletonAfterSerialization() throws Exception {
        // Ensure that the singleton instance is maintained after serialization
        ObjectUtils.Null original = ObjectUtils.NULL;
        assertNotNull(original);

        // Serialize the singleton instance
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
        oos.writeObject(original);
        oos.close();

        // Deserialize the singleton instance
        java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
        java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
        ObjectUtils.Null deserialized = (ObjectUtils.Null) ois.readObject();
        ois.close();

        // Verify that the deserialized instance is the same as the original singleton instance
        assertSame(original, deserialized);
    }
}