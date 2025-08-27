package org.apache.commons.codec.binary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.codec.binary.BaseNCodecInputStream;
import org.apache.commons.codec.binary.BaseNCodec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class BaseNCodecInputStreamLLM_Test {

    private BaseNCodec baseNCodec;
    private BaseNCodecInputStream baseNCodecInputStream;

    @BeforeEach
    public void setUp() {
        baseNCodec = Mockito.mock(BaseNCodec.class);
        baseNCodecInputStream = new BaseNCodecInputStream(new ByteArrayInputStream(new byte[0]), baseNCodec, false);
    }

    @Test
    public void testSetStrictDecoding() {
        baseNCodecInputStream.setStrictDecoding(true);
        Mockito.verify(baseNCodec).setStrictDecoding(true);

        baseNCodecInputStream.setStrictDecoding(false);
        Mockito.verify(baseNCodec).setStrictDecoding(false);
    }

    @Test
    public void testIsStrictDecoding() {
        Mockito.when(baseNCodec.isStrictDecoding()).thenReturn(true);
        assertTrue(baseNCodecInputStream.isStrictDecoding());

        Mockito.when(baseNCodec.isStrictDecoding()).thenReturn(false);
        assertFalse(baseNCodecInputStream.isStrictDecoding());
    }
}