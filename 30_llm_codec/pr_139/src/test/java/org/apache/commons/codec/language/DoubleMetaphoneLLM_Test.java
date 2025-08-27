package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleMetaphoneLLM_Test extends StringEncoderAbstractTest<DoubleMetaphone> {

    @Override
    protected DoubleMetaphone createStringEncoder() {
        return new DoubleMetaphone();
    }

    @Test
    public void testCharAtWithNegativeIndex() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals(Character.MIN_VALUE, doubleMetaphone.charAt("test", -1));
    }

    @Test
    public void testCharAtWithIndexOutOfBounds() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals(Character.MIN_VALUE, doubleMetaphone.charAt("test", 4));
    }

    @Test
    public void testCharAtWithValidIndex() {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals('e', doubleMetaphone.charAt("test", 1));
    }
}