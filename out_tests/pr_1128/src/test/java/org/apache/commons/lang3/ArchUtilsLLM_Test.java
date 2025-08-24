package org.apache.commons.lang3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.arch.Processor;
import org.apache.commons.lang3.arch.Processor.Arch;
import org.apache.commons.lang3.arch.Processor.Type;
import org.junit.jupiter.api.Test;

public class ArchUtilsLLM_Test extends AbstractLangTest {
    private static final String RISCV32 = "riscv32";
    private static final String RISCV64 = "riscv64";

    private void assertEqualsArchNotNull(final Processor.Arch arch, final Processor processor) {
        assertNotNull(arch);
        assertNotNull(processor);
        assertEquals(arch, processor.getArch());
    }

    private void assertEqualsTypeNotNull(final Processor.Type type, final Processor processor) {
        assertNotNull(type);
        assertNotNull(processor);
        assertEquals(type, processor.getType());
    }

    private void assertNotEqualsArchNotNull(final Processor.Arch arch, final Processor processor) {
        assertNotNull(arch);
        assertNotNull(processor);
        assertNotEquals(arch, processor.getArch());
    }

    private void assertNotEqualsTypeNotNull(final Processor.Type type, final Processor processor) {
        assertNotNull(type);
        assertNotNull(processor);
        assertNotEquals(type, processor.getType());
    }

    @Test
    public void testRISCVProcessors() {
        Processor processor = ArchUtils.getProcessor(RISCV32);
        assertEqualsTypeNotNull(Processor.Type.RISCV, processor);
        assertTrue(processor.isRISCV());
        assertEqualsArchNotNull(Processor.Arch.BIT_32, processor);
        assertTrue(processor.is32Bit());
        assertFalse(processor.is64Bit());

        processor = ArchUtils.getProcessor(RISCV64);
        assertEqualsTypeNotNull(Processor.Type.RISCV, processor);
        assertTrue(processor.isRISCV());
        assertEqualsArchNotNull(Processor.Arch.BIT_64, processor);
        assertTrue(processor.is64Bit());
        assertFalse(processor.is32Bit());
    }

    @Test
    public void testGetProcessorRISCV() {
        assertNotNull(ArchUtils.getProcessor(RISCV32));
        assertNotNull(ArchUtils.getProcessor(RISCV64));
    }
}