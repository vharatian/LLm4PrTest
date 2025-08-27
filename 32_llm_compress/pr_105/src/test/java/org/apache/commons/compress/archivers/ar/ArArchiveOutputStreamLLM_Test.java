package org.apache.commons.compress.archivers.ar;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.AbstractTestCase;
import org.junit.Test;

public class ArArchiveOutputStreamLLM_Test extends AbstractTestCase {

    @Test
    public void testWriteWithStandardCharsetsUSASCII() throws IOException {
        try (ArArchiveOutputStream os = new ArArchiveOutputStream(new ByteArrayOutputStream())) {
            String testString = "test";
            byte[] expectedBytes = testString.getBytes(StandardCharsets.US_ASCII);
            long bytesWritten = os.write(testString);
            assertEquals(expectedBytes.length, bytesWritten);
        }
    }
}