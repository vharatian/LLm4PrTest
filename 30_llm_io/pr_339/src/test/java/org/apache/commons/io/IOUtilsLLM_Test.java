package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.junit.jupiter.api.Test;

public class IOUtilsLLM_Test {

    @Test
    public void testRead_InputStream_NegativeLength() {
        InputStream input = new ByteArrayInputStream(new byte[10]);
        byte[] buffer = new byte[10];
        assertThrows(IllegalArgumentException.class, () -> IOUtils.read(input, buffer, 0, -1));
    }

    @Test
    public void testRead_Reader_NegativeLength() {
        Reader reader = new CharArrayReader(new char[10]);
        char[] buffer = new char[10];
        assertThrows(IllegalArgumentException.class, () -> IOUtils.read(reader, buffer, 0, -1));
    }
}