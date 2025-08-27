package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.apache.commons.codec.language.SoundexUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Caverphone2LLM_Test extends StringEncoderAbstractTest<Caverphone2> {
    @Override
    protected Caverphone2 createStringEncoder() {
        return new Caverphone2();
    }

    @Test
    public void testEncodeWithNullInput() throws EncoderException {
        Caverphone2 caverphone = new Caverphone2();
        assertEquals("1111111111", caverphone.encode(null), "Encoding null should return TEN_1");
    }

    @Test
    public void testEncodeWithEmptyInput() throws EncoderException {
        Caverphone2 caverphone = new Caverphone2();
        assertEquals("1111111111", caverphone.encode(""), "Encoding empty string should return TEN_1");
    }

    @Test
    public void testEncodeWithNonEmptyInput() throws EncoderException {
        Caverphone2 caverphone = new Caverphone2();
        assertEquals("PTA1111111", caverphone.encode("Peter"), "Encoding 'Peter' should return 'PTA1111111'");
    }
}