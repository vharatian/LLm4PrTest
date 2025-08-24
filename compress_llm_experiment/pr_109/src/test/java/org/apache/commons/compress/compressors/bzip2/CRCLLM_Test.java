package org.apache.commons.compress.compressors.bzip2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CRCLLM_Test {

    private CRC crc;

    @BeforeEach
    void setUp() {
        crc = new CRC();
    }

    @Test
    void testInitialiseCRC() {
        crc.initialiseCRC();
        assertEquals(0xffffffff, crc.getGlobalCRC());
    }

    @Test
    void testGetFinalCRC() {
        crc.initialiseCRC();
        assertEquals(0xffffffff, crc.getGlobalCRC());
        assertEquals(0x00000000, crc.getFinalCRC());
    }

    @Test
    void testSetGlobalCRC() {
        crc.setGlobalCRC(0x12345678);
        assertEquals(0x12345678, crc.getGlobalCRC());
    }

    @Test
    void testUpdateCRC() {
        crc.initialiseCRC();
        crc.updateCRC(0x01);
        assertNotEquals(0xffffffff, crc.getGlobalCRC());
    }

    @Test
    void testUpdateCRCWithRepeat() {
        crc.initialiseCRC();
        crc.updateCRC(0x01, 5);
        assertNotEquals(0xffffffff, crc.getGlobalCRC());
    }

    @Test
    void testUpdateCRCWithNegativeTemp() {
        crc.initialiseCRC();
        crc.updateCRC(-1);
        assertNotEquals(0xffffffff, crc.getGlobalCRC());
    }

    @Test
    void testUpdateCRCWithRepeatAndNegativeTemp() {
        crc.initialiseCRC();
        crc.updateCRC(-1, 5);
        assertNotEquals(0xffffffff, crc.getGlobalCRC());
    }
}