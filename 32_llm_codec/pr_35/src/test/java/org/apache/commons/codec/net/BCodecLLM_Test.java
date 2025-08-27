package org.apache.commons.codec.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class BCodecLLM_Test {

    @Test
    public void testSetStrictDecoding() {
        BCodec codec = new BCodec();
        codec.setStrictDecoding(true);
        assertTrue(codec.isStrictDecoding());
    }

    @Test
    public void testStrictDecoding() {
        BCodec codec = new BCodec();
        codec.setStrictDecoding(true);

        try {
            codec.decode("=?ASCII?B?ZE==?=");
            fail("Expected DecoderException for invalid Base64 input");
        } catch (DecoderException e) {
            // Expected exception
        }
    }

    @Test
    public void testLenientDecoding() {
        BCodec codec = new BCodec();
        codec.setStrictDecoding(false);

        try {
            String decoded = codec.decode("=?ASCII?B?ZE==?=");
            // Base64 lenient decoding will produce some output
            assertEquals("d", decoded);
        } catch (DecoderException e) {
            fail("Did not expect DecoderException for lenient decoding");
        }
    }
}