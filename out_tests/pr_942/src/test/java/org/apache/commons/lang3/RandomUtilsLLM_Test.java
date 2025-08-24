package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RandomUtilsLLM_Test extends AbstractLangTest {

    @Test
    public void testClassIsDeprecated() {
        assertTrue(RandomUtils.class.isAnnotationPresent(Deprecated.class), "RandomUtils class should be deprecated");
    }
}