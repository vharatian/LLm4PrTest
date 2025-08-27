package org.apache.commons.codec.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class BaseNCodecOutputStreamLLM_Test {

    private ByteArrayOutputStream byteArrayOutputStream;
    private BaseNCodec baseNCodec;
    private BaseNCodecOutputStream baseNCodecOutputStream;

    @BeforeEach
    public void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        baseNCodec = new Base64(); // Assuming Base64 as an example implementation of BaseNCodec
        baseNCodecOutputStream = new BaseNCodecOutputStream(byteArrayOutputStream, baseNCodec, false);
    }

    @Test
    public void testSetStrictDecoding() {
        baseNCodecOutputStream.setStrictDecoding(true);
        assertTrue(baseNCodecOutputStream.isStrictDecoding());

        baseNCodecOutputStream.setStrictDecoding(false);
        assertFalse(baseNCodecOutputStream.isStrictDecoding());
    }

    @Test
    public void testIsStrictDecoding() {
        // Default should be false
        assertFalse(baseNCodecOutputStream.isStrictDecoding());

        baseNCodecOutputStream.setStrictDecoding(true);
        assertTrue(baseNCodecOutputStream.isStrictDecoding());
    }

    @Test
    public void testStrictDecodingBehavior() {
        baseNCodecOutputStream.setStrictDecoding(true);
        byte[] invalidEncodedData = { 'a', 'b', 'c', 'd', 'e' }; // Invalid Base64 data

        assertThrows(IllegalArgumentException.class, () -> {
            baseNCodecOutputStream.write(invalidEncodedData, 0, invalidEncodedData.length);
        });
    }

    @Test
    public void testLenientDecodingBehavior() throws IOException {
        baseNCodecOutputStream.setStrictDecoding(false);
        byte[] invalidEncodedData = { 'a', 'b', 'c', 'd', 'e' }; // Invalid Base64 data

        baseNCodecOutputStream.write(invalidEncodedData, 0, invalidEncodedData.length);
        baseNCodecOutputStream.flush();

        // Check if some output is produced without exception
        assertTrue(byteArrayOutputStream.size() > 0);
    }
}