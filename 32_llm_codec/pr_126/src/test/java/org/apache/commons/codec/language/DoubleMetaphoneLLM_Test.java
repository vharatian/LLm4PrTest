package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleMetaphoneLLM_Test {

    @Test
    public void testAppendPrimaryWithSubstring() {
        DoubleMetaphone.DoubleMetaphoneResult result = new DoubleMetaphone().new DoubleMetaphoneResult(4);
        result.appendPrimary("testing");
        assertEquals("test", result.getPrimary());

        result = new DoubleMetaphone().new DoubleMetaphoneResult(2);
        result.appendPrimary("testing");
        assertEquals("te", result.getPrimary());
    }

    @Test
    public void testAppendAlternateWithSubstring() {
        DoubleMetaphone.DoubleMetaphoneResult result = new DoubleMetaphone().new DoubleMetaphoneResult(4);
        result.appendAlternate("testing");
        assertEquals("test", result.getAlternate());

        result = new DoubleMetaphone().new DoubleMetaphoneResult(2);
        result.appendAlternate("testing");
        assertEquals("te", result.getAlternate());
    }
}