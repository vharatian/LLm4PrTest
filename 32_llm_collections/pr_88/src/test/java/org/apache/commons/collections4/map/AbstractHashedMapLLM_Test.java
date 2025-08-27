package org.apache.commons.collections4.map;

import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractHashedMapLLM_Test {

    @Test
    public void testDoWriteObject() throws IOException, ClassNotFoundException {
        AbstractHashedMap<String, String> map = new AbstractHashedMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        map.doWriteObject(objectOutputStream);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        float loadFactor = objectInputStream.readFloat();
        int capacity = objectInputStream.readInt();
        int size = objectInputStream.readInt();

        assertEquals(0.75f, loadFactor);
        assertEquals(16, capacity);
        assertEquals(2, size);

        String key1 = (String) objectInputStream.readObject();
        String value1 = (String) objectInputStream.readObject();
        String key2 = (String) objectInputStream.readObject();
        String value2 = (String) objectInputStream.readObject();

        assertEquals("key1", key1);
        assertEquals("value1", value1);
        assertEquals("key2", key2);
        assertEquals("value2", value2);
    }
}