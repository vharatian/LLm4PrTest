package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class UnixLineEndingInputStreamLLM_Test {

    @Test
    public void testInitialState() throws Exception {
        ByteArrayInputStream input = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        UnixLineEndingInputStream stream = new UnixLineEndingInputStream(input, true);
        assertEquals(-1, stream.read());
        stream.close();
    }

    @Test
    public void testInitialStateWithContent() throws Exception {
        ByteArrayInputStream input = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        UnixLineEndingInputStream stream = new UnixLineEndingInputStream(input, true);
        assertEquals('t', stream.read());
        stream.close();
    }

    private String roundtrip(final String msg) throws IOException {
        return roundtrip(msg, true);
    }

    private String roundtrip(final String msg, final boolean ensure) throws IOException {
        final ByteArrayInputStream baos = new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8));
        final UnixLineEndingInputStream lf = new UnixLineEndingInputStream(baos, ensure);
        final byte[] buf = new byte[100];
        final int read = lf.read(buf);
        lf.close();
        return new String(buf, 0, read, StandardCharsets.UTF_8);
    }
}