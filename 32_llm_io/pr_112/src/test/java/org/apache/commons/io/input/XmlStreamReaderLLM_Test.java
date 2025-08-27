package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.Test;

public class XmlStreamReaderLLM_Test {

    @Test
    public void testFileConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> {
            new XmlStreamReader((File) null);
        });
    }

    @Test
    public void testInputStreamConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> {
            new XmlStreamReader((InputStream) null, true, null);
        });
    }

    @Test
    public void testUrlConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> {
            new XmlStreamReader((URL) null);
        });
    }

    @Test
    public void testURLConnectionConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> {
            new XmlStreamReader((URLConnection) null, null);
        });
    }

    @Test
    public void testInputStreamWithHttpContentTypeConstructorWithNull() {
        assertThrows(NullPointerException.class, () -> {
            new XmlStreamReader((InputStream) null, "application/xml", true, null);
        });
    }
}