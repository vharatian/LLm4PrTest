package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class MapUtilsLLM_Test {

    @Test
    public void testPrivateConstructor() {
        // Use reflection to test the private constructor
        assertThrows(IllegalAccessException.class, () -> {
            MapUtils.class.getDeclaredConstructor().newInstance();
        });
    }
}