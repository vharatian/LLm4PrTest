package org.apache.commons.lang3.arch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessorLLM_Test {

    @Test
    public void testIs32Bit() {
        Processor processor32Bit = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processor64Bit = new Processor(Processor.Arch.BIT_64, Processor.Type.X86);
        assertTrue(processor32Bit.is32Bit());
        assertFalse(processor64Bit.is32Bit());
    }

    @Test
    public void testIs64Bit() {
        Processor processor32Bit = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processor64Bit = new Processor(Processor.Arch.BIT_64, Processor.Type.X86);
        assertFalse(processor32Bit.is64Bit());
        assertTrue(processor64Bit.is64Bit());
    }

    @Test
    public void testIsX86() {
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processorIA64 = new Processor(Processor.Arch.BIT_32, Processor.Type.IA_64);
        assertTrue(processorX86.isX86());
        assertFalse(processorIA64.isX86());
    }

    @Test
    public void testIsIA64() {
        Processor processorIA64 = new Processor(Processor.Arch.BIT_32, Processor.Type.IA_64);
        Processor processorPPC = new Processor(Processor.Arch.BIT_32, Processor.Type.PPC);
        assertTrue(processorIA64.isIA64());
        assertFalse(processorPPC.isIA64());
    }

    @Test
    public void testIsPPC() {
        Processor processorPPC = new Processor(Processor.Arch.BIT_32, Processor.Type.PPC);
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        assertTrue(processorPPC.isPPC());
        assertFalse(processorX86.isPPC());
    }
}