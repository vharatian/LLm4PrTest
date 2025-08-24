package org.apache.commons.lang3.arch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessorLLM_Test {

    @Test
    public void testIsAarch64() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.AARCH_64);
        assertTrue(processor.isAarch64(), "Processor should be of type AARCH_64");
    }

    @Test
    public void testIsNotAarch64() {
        Processor processor = new Processor(Processor.Arch.BIT_64, Processor.Type.X86);
        assertFalse(processor.isAarch64(), "Processor should not be of type AARCH_64");
    }
}