package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testUnrollVariableAssignments() {
        // Create a TypeVariable and a map for type variable assignments
        TypeVariable<Class<TypeUtilsTest2>> typeVariable = TypeUtilsTest2.class.getTypeParameters()[0];
        Map<TypeVariable<?>, Type> typeVarAssigns = new HashMap<>();
        typeVarAssigns.put(typeVariable, String.class);

        // Call the method and assert the result
        Type result = TypeUtils.unrollVariableAssignments(typeVariable, typeVarAssigns);
        assertEquals(String.class, result);
    }
}