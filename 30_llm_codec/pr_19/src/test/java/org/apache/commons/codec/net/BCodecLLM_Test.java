package org.apache.commons.codec.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.apache.commons.codec.DecoderException;
import org.junit.Test;

public class BCodecLLM_Test {

    @Test
    public void testDecodeWithIllegalArgumentException() {
        final BCodec bcodec = new BCodec();
        final String invalidBase64 = "=?UTF-8?B?InvalidBase64===";
        try {
            bcodec.decode(invalidBase64);
            fail("Decoding an invalid Base64 string should cause an exception.");
        } catch (final DecoderException e) {
            assertEquals("Illegal base64 character 3d", e.getMessage());
        }
    }
}