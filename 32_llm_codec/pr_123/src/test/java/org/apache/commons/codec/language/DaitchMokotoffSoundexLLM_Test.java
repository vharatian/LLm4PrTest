package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void testSoundexWithSpecialCharacters() {
        // Test cases to ensure special characters are handled correctly
        assertEquals("097400", soundex("AUERBACH"));
        assertEquals("097400", soundex("OHRBACH"));
        assertEquals("874400", soundex("LIPSHITZ"));
        assertEquals("874400", soundex("LIPPSZYC"));
        assertEquals("876450", soundex("LEWINSKY"));
        assertEquals("876450", soundex("LEVINSKI"));
        assertEquals("486740", soundex("SZLAMAWICZ"));
        assertEquals("486740", soundex("SHLAMOVITZ"));
    }

    @Test
    public void testSoundexWithWhitespace() {
        // Test cases to ensure whitespace is ignored
        assertEquals("746536", soundex(" \t\n\r Washington \t\n\r "));
        assertEquals("746536", soundex("Washington"));
    }

    @Test
    public void testSoundexWithHyphens() throws EncoderException {
        // Test cases to ensure hyphens are ignored
        this.checkEncodingVariations("565463", new String[] { "KINGSMITH", "-KINGSMITH", "K-INGSMITH", "KI-NGSMITH",
                "KIN-GSMITH", "KING-SMITH", "KINGS-MITH", "KINGSM-ITH", "KINGSMI-TH", "KINGSMIT-H", "KINGSMITH-" });
    }

    @Test
    public void testSoundexWithApostrophes() throws EncoderException {
        // Test cases to ensure apostrophes are ignored
        this.checkEncodingVariations("079600", new String[] { "OBrien", "'OBrien", "O'Brien", "OB'rien", "OBr'ien",
                "OBri'en", "OBrie'n", "OBrien'" });
    }

    @Test
    public void testSoundexWithAccentedCharacters() {
        // Test cases to ensure accented characters are folded correctly
        assertEquals("294795", soundex("Straßburg"));
        assertEquals("294795", soundex("Strasburg"));
        assertEquals("095600", soundex("Éregon"));
        assertEquals("095600", soundex("Eregon"));
    }
}