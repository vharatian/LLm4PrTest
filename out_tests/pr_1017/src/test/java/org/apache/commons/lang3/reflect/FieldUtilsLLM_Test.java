package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FieldUtilsLLM_Test {

    @Test
    public void testGetAllFieldsListReturnType() {
        // Test to ensure getAllFieldsList returns a List<Field> and not an array
        List<Field> fields = FieldUtils.getAllFieldsList(PublicChild.class);
        assertNotNull(fields);
        assertEquals(ArrayList.class, fields.getClass());
    }
}