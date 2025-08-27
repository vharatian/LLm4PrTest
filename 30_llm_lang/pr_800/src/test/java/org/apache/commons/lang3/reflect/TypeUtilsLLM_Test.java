package org.apache.commons.lang3.reflect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

public class TypeUtilsLLM_Test {

    @Test
    public void testGetClosestParentType() throws Exception {
        // Test case to ensure the change in getClosestParentType method works correctly
        final ParameterizedType iterableType = (ParameterizedType) TypeUtilsTest.class.getField("iterable").getGenericType();
        final Map<TypeVariable<?>, Type> typeVarAssigns = TypeUtils.determineTypeArguments(TreeSet.class, iterableType);
        final TypeVariable<?> treeSetTypeVar = TreeSet.class.getTypeParameters()[0];
        assertTrue(typeVarAssigns.containsKey(treeSetTypeVar));
        assertEquals(iterableType.getActualTypeArguments()[0], typeVarAssigns.get(treeSetTypeVar));
    }
}