package org.apache.commons.codec.binary;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class BaseNCodecOutputStreamLLM_Test {

    @Test
    public void testWriteByteArrayWithValidParameters() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseNCodec baseNCodec = new Base64();
        BaseNCodecOutputStream stream = new BaseNCodecOutputStream(baos, baseNCodec, true);

        byte[] input = "test".getBytes();
        stream.write(input, 0, input.length);
        stream.flush();
        stream.close();

        assertNotNull(baos.toByteArray());
    }

    @Test
    public void testWriteByteArrayWithNullArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseNCodec baseNCodec = new Base64();
        BaseNCodecOutputStream stream = new BaseNCodecOutputStream(baos, baseNCodec, true);

        assertThrows(NullPointerException.class, () -> {
            stream.write(null, 0, 1);
        });
    }

    @Test
    public void testWriteByteArrayWithNegativeOffset() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseNCodec baseNCodec = new Base64();
        BaseNCodecOutputStream stream = new BaseNCodecOutputStream(baos, baseNCodec, true);

        byte[] input = "test".getBytes();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(input, -1, input.length);
        });
    }

    @Test
    public void testWriteByteArrayWithNegativeLength() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseNCodec baseNCodec = new Base64();
        BaseNCodecOutputStream stream = new BaseNCodecOutputStream(baos, baseNCodec, true);

        byte[] input = "test".getBytes();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(input, 0, -1);
        });
    }

    @Test
    public void testWriteByteArrayWithOffsetGreaterThanArrayLength() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseNCodec baseNCodec = new Base64();
        BaseNCodecOutputStream stream = new BaseNCodecOutputStream(baos, baseNCodec, true);

        byte[] input = "test".getBytes();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(input, input.length + 1, 1);
        });
    }

    @Test
    public void testWriteByteArrayWithOffsetPlusLengthGreaterThanArrayLength() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BaseNCodec baseNCodec = new Base64();
        BaseNCodecOutputStream stream = new BaseNCodecOutputStream(baos, baseNCodec, true);

        byte[] input = "test".getBytes();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            stream.write(input, 2, input.length);
        });
    }
}