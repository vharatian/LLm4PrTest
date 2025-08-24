package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;

public class MissingOptionExceptionLLM_Test {

    @Test
    public void testSingleMissingOption() {
        MissingOptionException exception = new MissingOptionException(Collections.singletonList("option1"));
        assertEquals("Missing required option: option1", exception.getMessage());
        assertEquals(Collections.singletonList("option1"), exception.getMissingOptions());
    }

    @Test
    public void testMultipleMissingOptions() {
        MissingOptionException exception = new MissingOptionException(Arrays.asList("option1", "option2"));
        assertEquals("Missing required options: option1, option2", exception.getMessage());
        assertEquals(Arrays.asList("option1", "option2"), exception.getMissingOptions());
    }

    @Test
    public void testNoMissingOptions() {
        MissingOptionException exception = new MissingOptionException(Collections.emptyList());
        assertEquals("Missing required options: ", exception.getMessage());
        assertEquals(Collections.emptyList(), exception.getMissingOptions());
    }
}