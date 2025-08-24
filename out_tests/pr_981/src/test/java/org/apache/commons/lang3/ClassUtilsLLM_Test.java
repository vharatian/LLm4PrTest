package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClassUtilsLLM_Test {

    @Test
    @DisplayName("When the className is null then NullPointerException is thrown")
    public void test_toCanonicalName_NullClassName() {
        assertThrows(NullPointerException.class, () -> {
            ClassUtils.toCanonicalName(null);
        });
    }
}