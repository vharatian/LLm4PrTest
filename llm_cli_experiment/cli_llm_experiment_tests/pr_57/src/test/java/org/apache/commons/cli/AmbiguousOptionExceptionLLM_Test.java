package org.apache.commons.cli;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

public class AmbiguousOptionExceptionLLM_Test {

    @Test
    public void testCreateMessage() {
        Collection<String> options = Arrays.asList("option1", "option2", "option3");
        String message = AmbiguousOptionException.createMessage("opt", options);
        assertEquals("Ambiguous option: 'opt' (could be: 'option1', 'option2', 'option3')", message);
    }

    @Test
    public void testGetMatchingOptions() {
        Collection<String> options = Arrays.asList("option1", "option2", "option3");
        AmbiguousOptionException exception = new AmbiguousOptionException("opt", options);
        assertEquals(options, exception.getMatchingOptions());
    }
}