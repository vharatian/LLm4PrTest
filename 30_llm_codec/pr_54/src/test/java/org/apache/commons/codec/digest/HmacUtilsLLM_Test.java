package org.apache.commons.codec.digest;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class HmacUtilsLLM_Test {

    @Test
    public void testCorrectedJavadoc() {
        // Test to ensure the corrected Javadoc comments do not affect functionality
        final Mac md5Mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_MD5, HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        final Mac sha1Mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_1, HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        final Mac sha256Mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        final Mac sha384Mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_384, HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        final Mac sha512Mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_512, HmacAlgorithmsTest.STANDARD_KEY_BYTES);

        Assert.assertArrayEquals(HmacAlgorithmsTest.STANDARD_MD5_RESULT_BYTES, HmacUtils.updateHmac(md5Mac, HmacAlgorithmsTest.STANDARD_PHRASE_STRING).doFinal());
        Assert.assertArrayEquals(HmacAlgorithmsTest.STANDARD_SHA1_RESULT_BYTES, HmacUtils.updateHmac(sha1Mac, HmacAlgorithmsTest.STANDARD_PHRASE_STRING).doFinal());
        Assert.assertArrayEquals(HmacAlgorithmsTest.STANDARD_SHA256_RESULT_BYTES, HmacUtils.updateHmac(sha256Mac, HmacAlgorithmsTest.STANDARD_PHRASE_STRING).doFinal());
        Assert.assertArrayEquals(HmacAlgorithmsTest.STANDARD_SHA384_RESULT_BYTES, HmacUtils.updateHmac(sha384Mac, HmacAlgorithmsTest.STANDARD_PHRASE_STRING).doFinal());
        Assert.assertArrayEquals(HmacAlgorithmsTest.STANDARD_SHA512_RESULT_BYTES, HmacUtils.updateHmac(sha512Mac, HmacAlgorithmsTest.STANDARD_PHRASE_STRING).doFinal());
    }

    @Test
    public void testHmacMethodsWithCorrectedJavadoc() throws IOException {
        // Test to ensure the corrected Javadoc comments do not affect functionality
        assertEquals(HmacAlgorithmsTest.STANDARD_MD5_RESULT_STRING, HmacUtils.hmacMd5Hex(HmacAlgorithmsTest.STANDARD_KEY_STRING, "The quick brown fox jumps over the lazy dog"));
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA1_RESULT_STRING, HmacUtils.hmacSha1Hex(HmacAlgorithmsTest.STANDARD_KEY_STRING, HmacAlgorithmsTest.STANDARD_PHRASE_STRING));
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA256_RESULT_STRING, HmacUtils.hmacSha256Hex(HmacAlgorithmsTest.STANDARD_KEY_STRING, HmacAlgorithmsTest.STANDARD_PHRASE_STRING));
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA384_RESULT_STRING, HmacUtils.hmacSha384Hex(HmacAlgorithmsTest.STANDARD_KEY_STRING, HmacAlgorithmsTest.STANDARD_PHRASE_STRING));
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA512_RESULT_STRING, HmacUtils.hmacSha512Hex(HmacAlgorithmsTest.STANDARD_KEY_STRING, HmacAlgorithmsTest.STANDARD_PHRASE_STRING));
    }

    @Test
    public void testHmacWithByteArrayAndCorrectedJavadoc() {
        // Test to ensure the corrected Javadoc comments do not affect functionality
        final Mac mac = HmacUtils.getHmacSha1(HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        HmacUtils.updateHmac(mac, HmacAlgorithmsTest.STANDARD_PHRASE_BYTES);
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA1_RESULT_STRING, Hex.encodeHexString(mac.doFinal()));
    }

    @Test
    public void testHmacWithInputStreamAndCorrectedJavadoc() throws IOException {
        // Test to ensure the corrected Javadoc comments do not affect functionality
        final Mac mac = HmacUtils.getHmacSha1(HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        HmacUtils.updateHmac(mac, new ByteArrayInputStream(HmacAlgorithmsTest.STANDARD_PHRASE_BYTES));
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA1_RESULT_STRING, Hex.encodeHexString(mac.doFinal()));
    }

    @Test
    public void testHmacWithStringAndCorrectedJavadoc() {
        // Test to ensure the corrected Javadoc comments do not affect functionality
        final Mac mac = HmacUtils.getHmacSha1(HmacAlgorithmsTest.STANDARD_KEY_BYTES);
        HmacUtils.updateHmac(mac, HmacAlgorithmsTest.STANDARD_PHRASE_STRING);
        assertEquals(HmacAlgorithmsTest.STANDARD_SHA1_RESULT_STRING, Hex.encodeHexString(mac.doFinal()));
    }
}