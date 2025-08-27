package org.apache.commons.codec.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.Test;

public class MatchRatingApproachEncoderLLM_Test extends StringEncoderAbstractTest<MatchRatingApproachEncoder> {

    @Test
    public final void testGetFirst3Last3_LongName_ReturnsFirst3Last3() {
        assertEquals("Aleder", this.getStringEncoder().getFirst3Last3("Alexzander"));
    }

    @Test
    public final void testGetFirst3Last3_ShortName_ReturnsName() {
        assertEquals("PETE", this.getStringEncoder().getFirst3Last3("PETE"));
    }

    @Test
    public final void testGetMinRating_SumLength4_Returns5() {
        assertEquals(5, this.getStringEncoder().getMinRating(4));
    }

    @Test
    public final void testGetMinRating_SumLength7_Returns4() {
        assertEquals(4, this.getStringEncoder().getMinRating(7));
    }

    @Test
    public final void testGetMinRating_SumLength11_Returns3() {
        assertEquals(3, this.getStringEncoder().getMinRating(11));
    }

    @Test
    public final void testGetMinRating_SumLength12_Returns2() {
        assertEquals(2, this.getStringEncoder().getMinRating(12));
    }

    @Test
    public final void testGetMinRating_SumLength13_Returns1() {
        assertEquals(1, this.getStringEncoder().getMinRating(13));
    }

    @Test
    public final void testIsEncodeEquals_LengthDifference3OrMore_ReturnsFalse() {
        assertFalse(this.getStringEncoder().isEncodeEquals("Alex", "Alexander"));
    }

    @Test
    public final void testLeftToRightThenRightToLeftProcessing_LongestStringLength6_ReturnsCorrectValue() {
        assertEquals(2, this.getStringEncoder().leftToRightThenRightToLeftProcessing("ALEXANDER", "ALEXAND"));
    }

    @Override
    protected MatchRatingApproachEncoder createStringEncoder() {
        return new MatchRatingApproachEncoder();
    }
}