package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.Reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CloseShieldReaderLLM_Test {

    private String data;
    private Reader original;
    private Reader shielded;

    @BeforeEach
    public void setUp() {
        data = "xyz";
        original = spy(new CharSequenceReader(data));
        shielded = new CloseShieldReader(original);
    }

    /**
     * Test for the deprecated constructor.
     */
    @Test
    public void testDeprecatedConstructor() throws IOException {
        shielded.close();
        verify(original, never()).close();
        final char[] cbuf = new char[10];
        assertEquals(-1, shielded.read(cbuf, 0, 10), "read(cbuf, off, len)");
        assertEquals(data.length(), original.read(cbuf, 0, 10), "read(cbuf, off, len)");
        assertEquals(data, new String(cbuf, 0, data.length()));
    }

    /**
     * Test for the new wrap method.
     */
    @Test
    public void testWrapMethod() throws IOException {
        Reader wrappedReader = CloseShieldReader.wrap(original);
        wrappedReader.close();
        verify(original, never()).close();
        final char[] cbuf = new char[10];
        assertEquals(-1, wrappedReader.read(cbuf, 0, 10), "read(cbuf, off, len)");
        assertEquals(data.length(), original.read(cbuf, 0, 10), "read(cbuf, off, len)");
        assertEquals(data, new String(cbuf, 0, data.length()));
    }
}