package org.apache.commons.codec.language;

import static org.junit.Assert.assertThrows;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Assert;
import org.junit.Test;

public class SoundexLLM_Test extends StringEncoderAbstractTest<Soundex> {

    @Override
    protected Soundex createStringEncoder() {
        return new Soundex();
    }

    @Test
    public void testSoundexArrayInitialization() {
        // Test to ensure that the array initialization change works correctly
        Soundex soundex = new Soundex();
        Assert.assertEquals("W452", soundex.soundex("Williams"));
    }

    @Test
    public void testSoundexArrayInitializationWithCustomMapping() {
        // Test to ensure that the array initialization change works correctly with custom mapping
        Soundex soundex = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING.toCharArray());
        Assert.assertEquals("W452", soundex.soundex("Williams"));
    }

    @Test
    public void testSoundexArrayInitializationWithStringMapping() {
        // Test to ensure that the array initialization change works correctly with string mapping
        Soundex soundex = new Soundex(Soundex.US_ENGLISH_MAPPING_STRING);
        Assert.assertEquals("W452", soundex.soundex("Williams"));
    }
}