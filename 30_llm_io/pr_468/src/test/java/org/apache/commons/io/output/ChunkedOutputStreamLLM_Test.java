package org.apache.commons.io.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

public class ChunkedOutputStreamLLM_Test {

    private ByteArrayOutputStream newByteArrayOutputStream(final AtomicInteger numWrites) {
        return new ByteArrayOutputStream() {
            @Override
            public void write(final byte[] b, final int off, final int len) {
                numWrites.incrementAndGet();
                super.write(b, off, len);
            }
        };
    }

    @Test
    public void testBuilderUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            ChunkedOutputStream.builder().setOutputStream(null).get();
        });
    }
}