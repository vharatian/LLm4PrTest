package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NysiisLLM_Test extends StringEncoderAbstractTest<Nysiis> {

    private final Nysiis fullNysiis = new Nysiis(false);

    private void assertEncodings(final String[]... testValues) throws EncoderException {
        for (final String[] arr : testValues) {
            assertEquals(arr[1], this.fullNysiis.encode(arr[0]), "Problem with " + arr[0]);
        }
    }

    @Override
    protected Nysiis createStringEncoder() {
        return new Nysiis();
    }

    private void encodeAll(final String[] strings, final String expectedEncoding) {
        for (final String string : strings) {
            assertEquals(expectedEncoding, getStringEncoder().encode(string), "Problem with " + string);
        }
    }

    @Test
    public void testNonVowelHandling() throws EncoderException {
        // Test cases to ensure 'H' handling when previous or next is a non-vowel
        this.assertEncodings(
            new String[] { "AHB", "AB" }, // 'H' should be replaced by previous 'A'
            new String[] { "BHC", "BC" }  // 'H' should be replaced by previous 'B'
        );
    }
}