package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class BeiderMorseEncoderLLM_Test extends StringEncoderAbstractTest<StringEncoder> {

    @Override
    protected StringEncoder createStringEncoder() {
        return new BeiderMorseEncoder();
    }

    @Test
    public void testEncodeWithEllipses() throws EncoderException {
        final BeiderMorseEncoder bmpm = new BeiderMorseEncoder();
        final String input = "d'ortley";
        final String expectedOutput = "(ortlaj|ortlej)-(dortlaj|dortlej)";
        assertEquals(expectedOutput, bmpm.encode(input));
    }

    @Test
    public void testEncodeWithoutEllipses() throws EncoderException {
        final BeiderMorseEncoder bmpm = new BeiderMorseEncoder();
        final String input = "ortley";
        final String expectedOutput = "ortlaj|ortlej";
        assertEquals(expectedOutput, bmpm.encode(input));
    }
}