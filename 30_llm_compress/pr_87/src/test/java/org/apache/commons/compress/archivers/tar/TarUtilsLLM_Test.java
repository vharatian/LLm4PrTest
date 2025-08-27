package org.apache.commons.compress.archivers.tar;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TarUtilsLLM_Test {

    @Test
    public void testParseSparse() {
        byte[] buffer = new byte[24];
        buffer[0] = '1';
        buffer[1] = '2';
        buffer[2] = '3';
        buffer[3] = '4';
        buffer[4] = '5';
        buffer[5] = '6';
        buffer[6] = '7';
        buffer[7] = '8';
        buffer[8] = '9';
        buffer[9] = '0';
        buffer[10] = '1';
        buffer[11] = '2';
        buffer[12] = '3';
        buffer[13] = '4';
        buffer[14] = '5';
        buffer[15] = '6';
        buffer[16] = '7';
        buffer[17] = '8';
        buffer[18] = '9';
        buffer[19] = '0';
        buffer[20] = '1';
        buffer[21] = '2';
        buffer[22] = '3';
        buffer[23] = '4';

        TarArchiveStructSparse sparse = TarUtils.parseSparse(buffer, 0);
        assertEquals(1234567890L, sparse.getOffset());
        assertEquals(12345678901234L, sparse.getNumbytes());
    }
}