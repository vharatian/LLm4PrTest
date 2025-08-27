package org.apache.commons.lang3.arch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessorLLM_Test {

    @Test
    public void testRISCVType() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.RISCV);
        assertEquals(Processor.Type.RISCV, processor.getType());
        assertTrue(processor.isRISCV());
        assertFalse(processor.isX86());
        assertFalse(processor.isIA64());
        assertFalse(processor.isPPC());
        assertFalse(processor.isAarch64());
    }

    @Test
    public void testToStringForRISCV() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.RISCV);
        assertEquals("RISC-V 64-bit", processor.toString());
    }
}