package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.junit.Assert;
import org.junit.Test;

public class ColognePhoneticLLM_Test {

    @Test
    public void testCodeNotIgnored() throws EncoderException {
        ColognePhonetic encoder = new ColognePhonetic();
        // Test cases to ensure the new condition in the if statement is covered
        Assert.assertEquals("01", encoder.encode("Aabjoe"));
        Assert.assertEquals("0856", encoder.encode("Aaclan"));
        Assert.assertEquals("04567", encoder.encode("Aychlmajr"));
    }

    @Test
    public void testCodeIgnored() throws EncoderException {
        ColognePhonetic encoder = new ColognePhonetic();
        // Test cases to ensure the code is ignored when it should be
        Assert.assertEquals("0", encoder.encode("a"));
        Assert.assertEquals("0", encoder.encode("e"));
        Assert.assertEquals("0", encoder.encode("i"));
        Assert.assertEquals("0", encoder.encode("o"));
        Assert.assertEquals("0", encoder.encode("u"));
    }

    @Test
    public void testSpecialCharacters() throws EncoderException {
        ColognePhonetic encoder = new ColognePhonetic();
        // Test cases with special characters to ensure they are handled correctly
        Assert.assertEquals("0", encoder.encode("\u00E4"));
        Assert.assertEquals("0", encoder.encode("\u00F6"));
        Assert.assertEquals("0", encoder.encode("\u00FC"));
        Assert.assertEquals("8", encoder.encode("\u00DF"));
    }

    @Test
    public void testComplexCases() throws EncoderException {
        ColognePhonetic encoder = new ColognePhonetic();
        // Complex test cases to ensure the overall functionality is correct
        Assert.assertEquals("657", encoder.encode("m\u00DCller"));
        Assert.assertEquals("862", encoder.encode("schmidt"));
        Assert.assertEquals("8627", encoder.encode("schneider"));
        Assert.assertEquals("387", encoder.encode("fischer"));
        Assert.assertEquals("317", encoder.encode("weber"));
    }
}