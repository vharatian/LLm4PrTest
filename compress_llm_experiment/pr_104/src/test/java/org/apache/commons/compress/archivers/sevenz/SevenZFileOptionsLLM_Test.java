package org.apache.commons.compress.archivers.sevenz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SevenZFileOptionsLLM_Test {

    @Test
    public void testDefaultOptions() {
        SevenZFileOptions defaultOptions = SevenZFileOptions.DEFAULT;
        assertEquals(Integer.MAX_VALUE, defaultOptions.getMaxMemoryLimitInKb());
        assertFalse(defaultOptions.getUseDefaultNameForUnnamedEntries());
    }

    @Test
    public void testBuilderWithMaxMemoryLimitInKb() {
        SevenZFileOptions options = SevenZFileOptions.builder()
                .withMaxMemoryLimitInKb(1024)
                .build();
        assertEquals(1024, options.getMaxMemoryLimitInKb());
    }

    @Test
    public void testBuilderWithUseDefaultNameForUnnamedEntries() {
        SevenZFileOptions options = SevenZFileOptions.builder()
                .withUseDefaultNameForUnnamedEntries(true)
                .build();
        assertTrue(options.getUseDefaultNameForUnnamedEntries());
    }

    @Test
    public void testBuilderWithAllOptions() {
        SevenZFileOptions options = SevenZFileOptions.builder()
                .withMaxMemoryLimitInKb(2048)
                .withUseDefaultNameForUnnamedEntries(true)
                .build();
        assertEquals(2048, options.getMaxMemoryLimitInKb());
        assertTrue(options.getUseDefaultNameForUnnamedEntries());
    }
}