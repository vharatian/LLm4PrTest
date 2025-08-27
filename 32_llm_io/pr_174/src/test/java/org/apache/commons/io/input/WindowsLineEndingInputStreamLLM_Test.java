package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class WindowsLineEndingInputStreamLLM_Test {

    @Test
    public void testEOFBehaviorWithoutEnsureLineFeed() throws Exception {
        assertEquals("abc", roundtrip("abc", false));
    }

    @Test
    public void testEOFBehaviorWithEnsureLineFeed() throws Exception {
        assertEquals("abc\r\n", roundtrip("abc", true));
    }

    @Test
    public void testEOFBehaviorWithCRWithoutEnsureLineFeed() throws Exception {
        assertEquals("a\r", roundtrip("a\r", false));
    }

    @Test
    public void testEOFBehaviorWithCRWithEnsureLineFeed() throws Exception {
        assertEquals("a\r\n", roundtrip("a\r", true));
    }

    @Test
    public void testEOFBehaviorWithLFWithoutEnsureLineFeed() throws Exception {
        assertEquals("a\n", roundtrip("a\n", false));
    }

    @Test
    public void testEOFBehaviorWithLFWithEnsureLineFeed() throws Exception {
        assertEquals("a\n", roundtrip("a\n", true));
    }

    private String roundtrip(final String msg, final boolean ensure) throws IOException {
        final ByteArrayInputStream baos = new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8));
        final WindowsLineEndingInputStream lf = new WindowsLineEndingInputStream(baos, ensure);
        final byte[] buf = new byte[100];
        final int read = lf.read(buf);
        lf.close();
        return new String(buf, 0, read, StandardCharsets.UTF_8);
    }
}