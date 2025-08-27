package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefinedSoundexLLM_Test extends StringEncoderAbstractTest<RefinedSoundex> {

    @Override
    protected RefinedSoundex createStringEncoder() {
        return new RefinedSoundex();
    }

    // No new functionality was added in the diff, only a typo was corrected in the comment.
    // Therefore, no new tests are required to cover the change.
}