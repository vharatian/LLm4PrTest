package org.apache.commons.compress.harmony.unpack200;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BcBandsLLM_Test {

    private BcBands bcBands;
    private Segment segment;

    @BeforeEach
    public void setUp() {
        segment = new Segment();
        bcBands = new BcBands(segment);
    }

    @Test
    public void testSwitchIsTableSwitchTrue() throws IOException, Pack200Exception {
        byte[] inputBytes = new byte[]{(byte) 170, -1}; // tableswitch opcode followed by end of stream
        InputStream in = new ByteArrayInputStream(inputBytes);
        bcBands.read(in);

        List<Boolean> switchIsTableSwitch = bcBands.getSwitchIsTableSwitch();
        assertEquals(1, switchIsTableSwitch.size());
        assertTrue(switchIsTableSwitch.get(0));
    }

    @Test
    public void testSwitchIsTableSwitchFalse() throws IOException, Pack200Exception {
        byte[] inputBytes = new byte[]{(byte) 171, -1}; // lookupswitch opcode followed by end of stream
        InputStream in = new ByteArrayInputStream(inputBytes);
        bcBands.read(in);

        List<Boolean> switchIsTableSwitch = bcBands.getSwitchIsTableSwitch();
        assertEquals(1, switchIsTableSwitch.size());
        assertTrue(!switchIsTableSwitch.get(0));
    }
}