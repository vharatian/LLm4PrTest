package org.apache.commons.text.lookup;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScriptStringLookupLLM_Test {

    @Test
    public void testExtraColonsInScript() {
        Assertions.assertEquals("7", ScriptStringLookup.INSTANCE.lookup("javascript:3 + 4:extra:colons"));
    }

    @Test
    public void testBadScriptKeyFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            ScriptStringLookup.INSTANCE.lookup("javascript");
        });
    }

    @Test
    public void testValidScriptExecution() {
        Assertions.assertEquals("7", ScriptStringLookup.INSTANCE.lookup("javascript:3 + 4"));
    }
}