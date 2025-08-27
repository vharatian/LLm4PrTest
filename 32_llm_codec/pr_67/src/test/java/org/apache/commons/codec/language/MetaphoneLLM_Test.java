package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Test;

public class MetaphoneLLM_Test extends StringEncoderAbstractTest<Metaphone> {

    @Override
    protected Metaphone createStringEncoder() {
        return new Metaphone();
    }

    @Test
    public void testMetaphoneWithNullInput() {
        assertEquals("", this.getStringEncoder().metaphone(null));
    }

    @Test
    public void testMetaphoneWithEmptyString() {
        assertEquals("", this.getStringEncoder().metaphone(""));
    }

    @Test
    public void testMetaphoneWithSingleCharacter() {
        assertEquals("A", this.getStringEncoder().metaphone("a"));
        assertEquals("B", this.getStringEncoder().metaphone("b"));
    }

    @Test
    public void testMetaphoneWithTwoCharacters() {
        assertEquals("AE", this.getStringEncoder().metaphone("ae"));
        assertEquals("KN", this.getStringEncoder().metaphone("kn"));
    }

    @Test
    public void testMetaphoneWithSpecialCharacters() {
        assertEquals("K", this.getStringEncoder().metaphone("k#"));
        assertEquals("S", this.getStringEncoder().metaphone("s@"));
    }
}