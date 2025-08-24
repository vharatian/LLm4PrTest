package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testParameterizeNullRawClass() {
        assertThrows(NullPointerException.class, () -> {
            TypeUtils.parameterize(null, Collections.emptyMap());
        });
    }

    @Test
    public void testParameterizeNullTypeVariableMap() {
        assertThrows(NullPointerException.class, () -> {
            TypeUtils.parameterize(String.class, (Map<TypeVariable<?>, Type>) null);
        });
    }

    @Test
    public void testParameterizeWithOwnerNullRawClass() {
        assertThrows(NullPointerException.class, () -> {
            TypeUtils.parameterizeWithOwner(null, null, Collections.emptyMap());
        });
    }

    @Test
    public void testParameterizeWithOwnerNullTypeVariableMap() {
        assertThrows(NullPointerException.class, () -> {
            TypeUtils.parameterizeWithOwner(null, String.class, (Map<TypeVariable<?>, Type>) null);
        });
    }

    @Test
    public void testParameterizeWithOwnerNullRawClassTypeArguments() {
        assertThrows(NullPointerException.class, () -> {
            TypeUtils.parameterizeWithOwner(null, null, new Type[0]);
        });
    }

    @Test
    public void testParameterizeNullRawClassTypeArguments() {
        assertThrows(NullPointerException.class, () -> {
            TypeUtils.parameterize(null, new Type[0]);
        });
    }
}