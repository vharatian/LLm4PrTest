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
    public void testHardGHandling() {
        // Test case where 'G' is followed by a front vowel and is not hard
        assertEquals("J", this.getStringEncoder().metaphone("GIA"));
        assertEquals("J", this.getStringEncoder().metaphone("GIE"));
        assertEquals("J", this.getStringEncoder().metaphone("GIY"));

        // Test case where 'G' is followed by a front vowel and is hard
        assertEquals("K", this.getStringEncoder().metaphone("GA"));
        assertEquals("K", this.getStringEncoder().metaphone("GO"));
        assertEquals("K", this.getStringEncoder().metaphone("GU"));

        // Test case where 'G' is followed by 'H' and should be silent
        assertEquals("KNT", this.getStringEncoder().metaphone("GHENT"));
        assertEquals("B", this.getStringEncoder().metaphone("BAUGH"));

        // Test case where 'G' is followed by 'N' and should be silent
        assertEquals("N", this.getStringEncoder().metaphone("GNU"));
        assertEquals("SNT", this.getStringEncoder().metaphone("SIGNED"));
    }

    @Test
    public void testIsMetaphoneEqualWithHardG() {
        // Test cases to check metaphone equality with hard 'G'
        assertTrue(this.getStringEncoder().isMetaphoneEqual("GIA", "JIA"));
        assertTrue(this.getStringEncoder().isMetaphoneEqual("GIE", "JIE"));
        assertTrue(this.getStringEncoder().isMetaphoneEqual("GIY", "JIY"));
        assertTrue(this.getStringEncoder().isMetaphoneEqual("GA", "KA"));
        assertTrue(this.getStringEncoder().isMetaphoneEqual("GO", "KO"));
        assertTrue(this.getStringEncoder().isMetaphoneEqual("GU", "KU"));
    }
}