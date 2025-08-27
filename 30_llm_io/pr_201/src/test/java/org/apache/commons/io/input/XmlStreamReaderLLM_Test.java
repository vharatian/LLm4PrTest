package org.apache.commons.io.input;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.junit.jupiter.api.Test;

public class XmlStreamReaderLLM_Test {

    @Test
    public void testNullURLConnectionInput() {
        // Test to ensure NullPointerException is thrown when URLConnection is null
        assertThrows(NullPointerException.class, () -> new XmlStreamReader((URLConnection) null, "US-ASCII"));
    }

    @Test
    public void testValidURLConnectionInput() throws IOException {
        // Test to ensure XmlStreamReader works correctly with a valid URLConnection
        URL url = new URL("http://www.example.com");
        URLConnection conn = url.openConnection();
        XmlStreamReader xmlStreamReader = new XmlStreamReader(conn, "UTF-8");
        xmlStreamReader.close();
    }
}