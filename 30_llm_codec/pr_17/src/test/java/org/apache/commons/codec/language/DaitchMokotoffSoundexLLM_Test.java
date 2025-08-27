package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Assert;
import org.junit.Test;

public class DaitchMokotoffSoundexLLM_Test extends StringEncoderAbstractTest<DaitchMokotoffSoundex> {

    @Override
    protected DaitchMokotoffSoundex createStringEncoder() {
        return new DaitchMokotoffSoundex();
    }

    private String soundex(final String source) {
        return getStringEncoder().soundex(source);
    }

    private String encode(final String source) {
        return getStringEncoder().encode(source);
    }

    /**
     * Test to ensure that the change from Collections.EMPTY_LIST to Collections.<Branch>emptyList()
     * does not affect the functionality of the soundex method.
     */
    @Test
    public void testSoundexWithBranching() {
        // Test cases where branching is true
        Assert.assertEquals("097400|097500", soundex("AUERBACH"));
        Assert.assertEquals("097400|097500", soundex("OHRBACH"));
        Assert.assertEquals("874400|874500", soundex("LIPPSZYC"));
        Assert.assertEquals("467000|567000", soundex("Ceniow"));
        Assert.assertEquals("587400|587500", soundex("Holubica"));
        Assert.assertEquals("746480|794648", soundex("Przemysl"));
        Assert.assertEquals("944744|944745|944754|944755|945744|945745|945754|945755", soundex("Rosochowaciec"));
    }

    /**
     * Test to ensure that the change from Collections.EMPTY_LIST to Collections.<Branch>emptyList()
     * does not affect the functionality of the encode method.
     */
    @Test
    public void testEncodeWithBranching() {
        // Test cases where branching is false
        Assert.assertEquals("097400", encode("AUERBACH"));
        Assert.assertEquals("097400", encode("OHRBACH"));
        Assert.assertEquals("874400", encode("LIPSHITZ"));
        Assert.assertEquals("876450", encode("LEWINSKY"));
        Assert.assertEquals("486740", encode("SZLAMAWICZ"));
    }
}