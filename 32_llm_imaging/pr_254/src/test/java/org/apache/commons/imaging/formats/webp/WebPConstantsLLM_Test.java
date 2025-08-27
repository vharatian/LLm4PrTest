package org.apache.commons.imaging.formats.webp;

import org.apache.commons.imaging.common.BinaryConstant;
import org.junit.Test;
import static org.junit.Assert.*;

public class WebPConstantsLLM_Test {

    @Test
    public void testRiffSignature() {
        BinaryConstant expected = new BinaryConstant(new byte[]{'R', 'I', 'F', 'F'});
        assertArrayEquals(expected.getBytes(), WebPConstants.RIFF_SIGNATURE.getBytes());
    }

    @Test
    public void testWebpSignature() {
        BinaryConstant expected = new BinaryConstant(new byte[]{'W', 'E', 'B', 'P'});
        assertArrayEquals(expected.getBytes(), WebPConstants.WEBP_SIGNATURE.getBytes());
    }
}