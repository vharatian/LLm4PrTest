package org.apache.commons.lang3.text.translate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.Collections;

@Deprecated
public class NumericEntityUnescaperLLM_Test {

    @Test
    public void testDefaultOptionInitialization() {
        // Test to ensure the default option initialization uses Collections.singletonList
        NumericEntityUnescaper neu = new NumericEntityUnescaper();
        assertEquals(Collections.singletonList(NumericEntityUnescaper.OPTION.semiColonRequired), 
                     new ArrayList<>(neu.options), 
                     "Default option should be semiColonRequired");
    }

    @Test
    public void testCustomOptionInitialization() {
        // Test to ensure custom options are correctly initialized
        NumericEntityUnescaper neu = new NumericEntityUnescaper(NumericEntityUnescaper.OPTION.errorIfNoSemiColon);
        assertEquals(Collections.singletonList(NumericEntityUnescaper.OPTION.errorIfNoSemiColon), 
                     new ArrayList<>(neu.options), 
                     "Custom option should be errorIfNoSemiColon");
    }
}