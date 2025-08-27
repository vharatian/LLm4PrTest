package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

public class HexDumpLLM_Test {

    @Test
    public void testDeprecatedEOL() {
        // Ensure the deprecated EOL constant is equal to System.lineSeparator()
        assertEquals(System.lineSeparator(), HexDump.EOL, "Deprecated EOL constant should match System.lineSeparator()");
    }
}