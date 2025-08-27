package org.apache.commons.codec.language;

import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchRatingApproachEncoderLLM_Test extends StringEncoderAbstractTest<MatchRatingApproachEncoder> {

    @Test
    public final void testGetMinRating_CommentTypoFix() {
        assertEquals(4, this.getStringEncoder().getMinRating(7));
        assertEquals(3, this.getStringEncoder().getMinRating(11));
    }

    @Test
    public final void testIsEncodeEquals_LengthDifferenceCheck() {
        assertFalse(this.getStringEncoder().isEncodeEquals("Alexander", "Alex"));
        assertTrue(this.getStringEncoder().isEncodeEquals("Alexander", "Alexandr"));
    }

    @Test
    public final void testIsVowel_CommentTypoFix() {
        assertTrue(this.getStringEncoder().isVowel("A"));
        assertFalse(this.getStringEncoder().isVowel("B"));
    }

    @Test
    public final void testLeftToRightThenRightToLeftProcessing_CommentTypoFix() {
        assertEquals(4, this.getStringEncoder().leftToRightThenRightToLeftProcessing("ALEXANDER", "ALEXANDRA"));
        assertEquals(0, this.getStringEncoder().leftToRightThenRightToLeftProcessing("EINSTEIN", "MICHAELA"));
    }

    @Override
    protected MatchRatingApproachEncoder createStringEncoder() {
        return new MatchRatingApproachEncoder();
    }
}