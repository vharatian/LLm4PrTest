package org.apache.commons.lang3.arch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessorLLM_Test {

    @Test
    public void testGetArch() {
        Processor processor32 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processor64 = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);
        Processor processorUnknown = new Processor(Processor.Arch.UNKNOWN, Processor.Type.UNKNOWN);

        assertEquals(Processor.Arch.BIT_32, processor32.getArch());
        assertEquals(Processor.Arch.BIT_64, processor64.getArch());
        assertEquals(Processor.Arch.UNKNOWN, processorUnknown.getArch());
    }

    @Test
    public void testGetType() {
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processorAarch64 = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);
        Processor processorIA64 = new Processor(Processor.Arch.BIT_64, Processor.Type.IA_64);
        Processor processorPPC = new Processor(Processor.Arch.BIT_32, Processor.Type.PPC);
        Processor processorUnknown = new Processor(Processor.Arch.UNKNOWN, Processor.Type.UNKNOWN);

        assertEquals(Processor.Type.X86, processorX86.getType());
        assertEquals(Processor.Type.AARCH_64, processorAarch64.getType());
        assertEquals(Processor.Type.IA_64, processorIA64.getType());
        assertEquals(Processor.Type.PPC, processorPPC.getType());
        assertEquals(Processor.Type.UNKNOWN, processorUnknown.getType());
    }

    @Test
    public void testIs32Bit() {
        Processor processor32 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processor64 = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);

        assertTrue(processor32.is32Bit());
        assertFalse(processor64.is32Bit());
    }

    @Test
    public void testIs64Bit() {
        Processor processor32 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processor64 = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);

        assertFalse(processor32.is64Bit());
        assertTrue(processor64.is64Bit());
    }

    @Test
    public void testIsAarch64() {
        Processor processorAarch64 = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);

        assertTrue(processorAarch64.isAarch64());
        assertFalse(processorX86.isAarch64());
    }

    @Test
    public void testIsIA64() {
        Processor processorIA64 = new Processor(Processor.Arch.BIT_64, Processor.Type.IA_64);
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);

        assertTrue(processorIA64.isIA64());
        assertFalse(processorX86.isIA64());
    }

    @Test
    public void testIsPPC() {
        Processor processorPPC = new Processor(Processor.Arch.BIT_32, Processor.Type.PPC);
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);

        assertTrue(processorPPC.isPPC());
        assertFalse(processorX86.isPPC());
    }

    @Test
    public void testIsX86() {
        Processor processorX86 = new Processor(Processor.Arch.BIT_32, Processor.Type.X86);
        Processor processorAarch64 = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);

        assertTrue(processorX86.isX86());
        assertFalse(processorAarch64.isX86());
    }
}