package org.apache.commons.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

public class HexDumpLLM_Test {

    @Test
    public void testHexCodesAndShifts() throws IOException {
        // Test data
        final byte[] testArray = new byte[16];
        for (int j = 0; j < 16; j++) {
            testArray[j] = (byte) j;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        // Invoke the dump method
        HexDump.dump(testArray, 0, stream, 0);
        
        // Expected output
        byte[] outputArray = new byte[1 * (73 + HexDump.EOL.length())];
        int offset = 0;
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) '0';
        outputArray[offset++] = (byte) ' ';
        for (int k = 0; k < 16; k++) {
            outputArray[offset++] = (byte) toHex(k / 16);
            outputArray[offset++] = (byte) toHex(k);
            outputArray[offset++] = (byte) ' ';
        }
        for (int k = 0; k < 16; k++) {
            outputArray[offset++] = (byte) toAscii(k);
        }
        System.arraycopy(HexDump.EOL.getBytes(), 0, outputArray, offset, HexDump.EOL.getBytes().length);
        
        // Actual output
        byte[] actualOutput = stream.toByteArray();
        
        // Assertions
        assertEquals(outputArray.length, actualOutput.length, "array size mismatch");
        for (int j = 0; j < outputArray.length; j++) {
            assertEquals(outputArray[j], actualOutput[j], "array[ " + j + "] mismatch");
        }
    }

    private char toAscii(final int c) {
        char rval = '.';
        if ((c >= 32) && (c <= 126)) {
            rval = (char) c;
        }
        return rval;
    }

    private char toHex(final int n) {
        final char[] hexChars =
        {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        return hexChars[n % 16];
    }
}