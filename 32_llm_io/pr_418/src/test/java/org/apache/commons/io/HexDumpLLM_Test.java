package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

public class HexDumpLLM_Test {

    @Test
    public void testDumpToAppendable() throws IOException {
        final byte[] testArray = new byte[256];
        for (int j = 0; j < 256; j++) {
            testArray[j] = (byte) j;
        }
        StringWriter writer = new StringWriter();
        HexDump.dump(testArray, writer);
        String output = writer.toString();
        StringBuilder expectedOutput = new StringBuilder();
        for (int j = 0; j < 16; j++) {
            expectedOutput.append(String.format("%08X ", j * 16));
            for (int k = 0; k < 16; k++) {
                expectedOutput.append(String.format("%02X ", j * 16 + k));
            }
            for (int k = 0; k < 16; k++) {
                expectedOutput.append(toAscii(j * 16 + k));
            }
            expectedOutput.append(System.lineSeparator());
        }
        assertEquals(expectedOutput.toString(), output, "Output mismatch");
    }

    @Test
    public void testDumpToAppendableWithOffsetAndLength() throws IOException {
        final byte[] testArray = new byte[256];
        for (int j = 0; j < 256; j++) {
            testArray[j] = (byte) j;
        }
        StringWriter writer = new StringWriter();
        HexDump.dump(testArray, 0x10000000, writer, 0x80, 0x80);
        String output = writer.toString();
        StringBuilder expectedOutput = new StringBuilder();
        for (int j = 0; j < 8; j++) {
            expectedOutput.append(String.format("%08X ", 0x10000000 + j * 16 + 0x80));
            for (int k = 0; k < 16; k++) {
                expectedOutput.append(String.format("%02X ", 0x80 + j * 16 + k));
            }
            for (int k = 0; k < 16; k++) {
                expectedOutput.append(toAscii(0x80 + j * 16 + k));
            }
            expectedOutput.append(System.lineSeparator());
        }
        assertEquals(expectedOutput.toString(), output, "Output mismatch");
    }

    @Test
    public void testDumpToAppendableWithInvalidIndex() {
        final byte[] testArray = new byte[256];
        StringWriter writer = new StringWriter();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> HexDump.dump(testArray, 0, writer, -1, 10));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> HexDump.dump(testArray, 0, writer, 256, 10));
    }

    @Test
    public void testDumpToAppendableWithInvalidLength() {
        final byte[] testArray = new byte[256];
        StringWriter writer = new StringWriter();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> HexDump.dump(testArray, 0, writer, 0, -1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> HexDump.dump(testArray, 0, writer, 0, 257));
    }

    @Test
    public void testDumpToAppendableWithNullAppendable() {
        final byte[] testArray = new byte[256];
        assertThrows(NullPointerException.class, () -> HexDump.dump(testArray, 0, null, 0, 10));
    }

    private char toAscii(final int c) {
        char rval = '.';
        if (c >= 32 && c <= 126) {
            rval = (char) c;
        }
        return rval;
    }
}